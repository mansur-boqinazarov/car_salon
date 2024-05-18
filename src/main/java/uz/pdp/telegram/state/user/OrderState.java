package uz.pdp.telegram.state.user;

import uz.pdp.telegram.state.State;

public enum OrderState implements State {
    GETTING_CONTRACT,
    CANCELLATION
}