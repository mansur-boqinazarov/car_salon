package uz.pdp.backend.model.carsalon;

import uz.pdp.backend.model.basemodel.BaseModel;

import java.util.Date;
import java.util.UUID;

public class CarSalon extends BaseModel{
    private UUID adminID;
    private UUID consumerID;
    private String name;
    private String location;
    private Date openTime;
    private Date closeTime;
    private String phoneNumber;
    }

