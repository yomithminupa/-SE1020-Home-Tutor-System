package com.example.hometutor.service;

import com.example.hometutor.model.AdminUser;
import com.example.hometutor.model.ParentUser;
import com.example.hometutor.model.StudentUser;
import com.example.hometutor.model.User;
import com.example.hometutor.repository.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

@Service
public class UserService extends AbstractCrudService<User> {

    public UserService(@Value("${app.data-dir:data}") String dataDir) {
        super(new FileRepository<>(Paths.get(dataDir).resolve("users.txt"), UserService::fromFileLine));
    }

    public User buildUser(String type, String id, String name, String email, String phone, String password,
                          String grade, String address, String childName, String permissionLevel) {
        if ("PARENT".equalsIgnoreCase(type)) {
            return new ParentUser(id, name, email, phone, password, childName, address);
        }
        if ("ADMIN".equalsIgnoreCase(type)) {
            return new AdminUser(id, name, email, phone, password, permissionLevel);
        }
        return new StudentUser(id, name, email, phone, password, grade, address);
    }

    @Override
    public List<User> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return findAll();
        }
        String query = keyword.toLowerCase(Locale.ROOT);
        return findAll().stream()
                .filter(user -> contains(user.getId(), query)
                        || contains(user.getName(), query)
                        || contains(user.getEmail(), query)
                        || contains(user.getPhone(), query)
                        || contains(user.getUserType(), query))
                .toList();
    }

    private static User fromFileLine(String line) {
        String[] parts = line.split("\\|", -1);
        String type = value(parts, 0);
        String id = value(parts, 1);
        String name = value(parts, 2);
        String email = value(parts, 3);
        String phone = value(parts, 4);
        String password = value(parts, 5);
        if ("PARENT".equalsIgnoreCase(type)) {
            return new ParentUser(id, name, email, phone, password, value(parts, 6), value(parts, 7));
        }
        if ("ADMIN".equalsIgnoreCase(type)) {
            return new AdminUser(id, name, email, phone, password, value(parts, 6));
        }
        return new StudentUser(id, name, email, phone, password, value(parts, 6), value(parts, 7));
    }

    private static boolean contains(String value, String query) {
        return value != null && value.toLowerCase(Locale.ROOT).contains(query);
    }

    private static String value(String[] parts, int index) {
        return index < parts.length ? parts[index] : "";
    }
}
