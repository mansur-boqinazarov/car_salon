package uz.pdp.telegram.state.user;

public enum OrderState implements State {
    GETTING_CONTRACT,
    CANCELLATION,
    ORDER_OR_NOT_ORDER;
}