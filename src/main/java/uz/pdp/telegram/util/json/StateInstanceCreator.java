package uz.pdp.telegram.util.json;

import com.google.gson.InstanceCreator;
import uz.pdp.telegram.state.user.CarSalonMenuState;
import uz.pdp.telegram.state.user.DefaultState;
import uz.pdp.telegram.state.user.GenerateUserPassportState;
import uz.pdp.telegram.state.State;

import java.lang.reflect.Type;

public class StateInstanceCreator implements InstanceCreator<State> {
    @Override
    public State createInstance(Type type) {
        if (type.equals(CarSalonMenuState.class)) {
            return CarSalonMenuState.CAR_MODEL;
        } else if (type.equals(DefaultState.class)) {
            return DefaultState.MAIN_STATE;
        } else if (type.equals(GenerateUserPassportState.class)) {
            return GenerateUserPassportState.FIRST_NAME;
        }
        return null;
    }
}
