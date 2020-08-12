package com.falcon.clientbank.dao;

import java.util.List;

public interface Dao<T> {

    T save(T obj);

    void delete(T obj);

    void deleteAll(List<T> entities);

    void saveAll(List<T> entities);

    List<T> findAll();

    boolean deleteById(long id);

    T getOne(long id);

}

