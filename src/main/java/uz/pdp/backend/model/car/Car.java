package uz.pdp.backend.model.car;

import uz.pdp.backend.model.BaseModel;

import java.util.Date;
import java.util.UUID;

public class Car extends BaseModel {
    private UUID autoSalonID;
    private String model;
    private String name;
    private String color;
    private Date madeDate;
    private double price;
//    private Modfication modfication;
    private String urlPhoto;
    private String divigatel;
    private int carCount;
    private String mashinaTuri;
}
