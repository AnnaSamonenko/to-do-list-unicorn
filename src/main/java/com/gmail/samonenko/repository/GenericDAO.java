package com.gmail.samonenko.repository;

import com.gmail.samonenko.model.Task;

import java.util.List;

public interface GenericDAO<T, U> {

    T read(U id);

    void delete(U id);

    List<T> getAll();

    T update(T t);

    void create(T t);

}
