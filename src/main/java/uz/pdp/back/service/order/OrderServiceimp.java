package uz.pdp.back.service.order;

import com.google.gson.reflect.TypeToken;
import uz.pdp.back.databases.Database;
import uz.pdp.back.model.order.Order;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderServiceimp implements OrderService{
    private final String fileURL = ResourceBundle.getBundle("files").getString("files.order");
    Database<Order> database = new Database<>();

    @Override
    public void create(Order model) {
        Type type = new TypeToken<List<Order>>(){}.getType();
        List<Order> list = new ArrayList<>();
        if(database.getAll(type, fileURL) != null){
            list = database.getAll(type, fileURL);
        }
        list.add(model);
        database.save(list, fileURL);
    }

    @Override
    public Order read(String id) {
        return readAll().stream()
                .filter(model -> model.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public void update(Order order) {
    }

    @Override
    public void delete(String id) {
        Type type = new TypeToken<List<Order>>(){}.getType();
        database.delete(id,type,fileURL);
    }

    @Override
    public List<Order> readAll() {
        Type type = new TypeToken<List<Order>>(){}.getType();
        return database.getAll(type, fileURL);
    }
}
