package uz.pdp.back.service.carModel;

import uz.pdp.back.model.carmodel.CarModel;
import uz.pdp.back.service.base.BaseService;

import java.util.List;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 11/32/15
 */
public interface CarModelService extends BaseService<CarModel> {
     List<CarModel> listBySalonID(String id);


    List<CarModel> findModelByCarID(String carSalonID);
}