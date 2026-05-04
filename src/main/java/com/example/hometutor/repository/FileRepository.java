package com.example.hometutor.repository;

import com.example.hometutor.model.FileEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FileRepository<T extends FileEntity> {
    private final Path filePath;
    private final EntityMapper<T> mapper;

    public FileRepository(Path filePath, EntityMapper<T> mapper) {
        this.filePath = filePath;
        this.mapper = mapper;
    }

    public synchronized List<T> findAll() {
        ensureFileExists();
        try {
            List<T> records = new ArrayList<>();
            for (String line : Files.readAllLines(filePath, StandardCharsets.UTF_8)) {
                if (!line.isBlank()) {
                    records.add(mapper.fromFileLine(line));
                }
            }
            return records;
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read " + filePath, e);
        }
    }

    public synchronized Optional<T> findById(String id) {
        return findAll().stream()
                .filter(item -> Objects.equals(item.getId(), id))
                .findFirst();
    }

    public synchronized void save(T entity) {
        if (findById(entity.getId()).isPresent()) {
            throw new IllegalArgumentException("Record already exists with ID " + entity.getId());
        }
        List<T> records = findAll();
        records.add(entity);
        writeAll(records);
    }

    public synchronized boolean update(T entity) {
        List<T> records = findAll();
        boolean updated = false;
        for (int i = 0; i < records.size(); i++) {
            if (Objects.equals(records.get(i).getId(), entity.getId())) {
                records.set(i, entity);
                updated = true;
                break;
            }
        }
        if (updated) {
            writeAll(records);
        }
        return updated;
    }

    public synchronized boolean delete(String id) {
        List<T> records = findAll();
        boolean removed = records.removeIf(item -> Objects.equals(item.getId(), id));
        if (removed) {
            writeAll(records);
        }
        return removed;
    }

    private void writeAll(List<T> records) {
        ensureFileExists();
        List<String> lines = records.stream()
                .map(FileEntity::toFileString)
                .toList();
        try {
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to write " + filePath, e);
        }
    }

    private void ensureFileExists() {
        try {
            if (filePath.getParent() != null) {
                Files.createDirectories(filePath.getParent());
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Unable to create " + filePath, e);
        }
    }
}
