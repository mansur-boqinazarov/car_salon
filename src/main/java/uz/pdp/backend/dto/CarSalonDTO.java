package uz.pdp.backend.dto;

import java.util.Date;
import java.util.UUID;

/**
 * @author To'lqin Ruzimbayev
 * @since 08/May/2024 17/16/52
 */
public record CarSalonDTO(UUID adminID, UUID consumerID,
                          String name, String location,
                          Date openTime, Date closeTime,
                          String phoneNumber) implements DTO {
}