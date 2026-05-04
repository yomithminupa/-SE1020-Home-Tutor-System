package com.example.hometutor.service;

import com.example.hometutor.model.FileEntity;
import com.example.hometutor.repository.FileRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudService<T extends FileEntity> {
    private final FileRepository<T> repository;

    protected AbstractCrudService(FileRepository<T> repository) {
        this.repository = repository;
    }

    public void create(T entity) {
        repository.save(entity);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public Optional<T> findById(String id) {
        return repository.findById(id);
    }

    public boolean update(T entity) {
        return repository.update(entity);
    }

    public boolean delete(String id) {
        return repository.delete(id);
    }

    public abstract List<T> search(String keyword);
}
