package uz.pdp.back.service.address;

import com.google.gson.reflect.TypeToken;
import uz.pdp.back.databases.Database;
import uz.pdp.back.model.address.Address;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 11/40/16
 */
public class AddressServiceimp implements AddressService{
    private final String fileURL = ResourceBundle.getBundle("files").getString("files.address");
    Database<Address> database = new Database<>();

    @Override
    public void create(Address car) {
        Type type = new TypeToken<List<Address>>(){}.getType();
        List<Address> carList = new ArrayList<>();
        if(database.getAll(type, fileURL) != null){
            carList = database.getAll(type, fileURL);
        }
        carList.add(car);
        database.save(carList, fileURL);
    }

    @Override
    public Address read(String id) {
        return readAll().stream()
                .filter(model -> model.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public void update(Address car) {
    }

    @Override
    public void delete(String id) {
        Type type = new TypeToken<List<Address>>(){}.getType();
        database.delete(id,type,fileURL);
    }

    @Override
    public List<Address> readAll() {
        Type type = new TypeToken<List<Address>>(){}.getType();
        return database.getAll(type, fileURL);
    }
}
