package com.imooc.sell.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 基类接口
 */
public interface BaseService<T> {
    T saveOrUpdate(T t);
    T delete(String id);
    T findOne(String id);
    List<T> findAll();
    Page<T> findByPage(Pageable page);
}
