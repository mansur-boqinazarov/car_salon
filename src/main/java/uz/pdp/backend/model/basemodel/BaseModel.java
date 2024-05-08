package uz.pdp.backend.model.basemodel;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BaseModel {
    private final UUID id;

    public BaseModel() {
        this.id = UUID.randomUUID();
    }
}