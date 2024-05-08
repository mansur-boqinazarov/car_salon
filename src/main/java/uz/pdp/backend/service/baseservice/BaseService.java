package uz.pdp.backend.service.baseservice;

import java.util.List;
import java.util.UUID;

/**
 * @author To'lqin Ruzimbayev
 * @since 08/May/2024 17/02/31
 */
public interface BaseService<T>{
    void create(T t);
    T read(UUID id);
    void update(T t);
    void delete(UUID id);
    List<T> readAll();
}