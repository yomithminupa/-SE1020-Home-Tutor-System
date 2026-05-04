package com.example.hometutor.repository;

@FunctionalInterface
public interface EntityMapper<T> {
    T fromFileLine(String line);
}
