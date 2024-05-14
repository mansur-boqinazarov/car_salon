package uz.pdp.back.service.base;

import java.util.List;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 11/28/26
 */
public interface BaseService<T> {
    void create(T t);
    T read(String id);
    void update(T t);
    void delete(String id);
    List<T> readAll();
}