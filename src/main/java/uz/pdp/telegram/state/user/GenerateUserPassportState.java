package uz.pdp.telegram.state.user;

public enum GenerateUserPassportState implements State {
    FIRST_NAME,
    LAST_NAME,
    FATHERS_NAME,
    BIRTH_DATE,
    GENDER,
    PASSPORT_NUMBER,
    PASSPORT_SERIES,
    GENERATE_PASSPORT;
}