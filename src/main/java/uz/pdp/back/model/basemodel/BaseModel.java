package uz.pdp.back.model.basemodel;

import lombok.Getter;

import java.util.UUID;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 10/12/14
 */
@Getter
public abstract class BaseModel {
    private final String id;
    public BaseModel() {
        this.id = UUID.randomUUID().toString();
    }
}
