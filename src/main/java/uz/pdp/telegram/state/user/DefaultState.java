package uz.pdp.telegram.state.user;

import uz.pdp.telegram.state.State;

public enum DefaultState implements State {
    DELETE,
    MAIN_STATE,
    SEND_PHONE_NUMBER,
    BASE_USER_MENU
}