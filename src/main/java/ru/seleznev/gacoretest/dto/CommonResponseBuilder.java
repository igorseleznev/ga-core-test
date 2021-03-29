package ru.seleznev.gacoretest.dto;

import java.util.List;

import static ru.seleznev.gacoretest.dto.CommonResponse.*;

public class CommonResponseBuilder {
    public static CommonResponse ok() {
        return new CommonResponse(ERROR_CODE_OK);
    }

    public static CommonResponse ok(List<ExtraElement> extraElements) {
        return new ExtendedCommonResponse(ERROR_CODE_OK, extraElements);
    }

    public static CommonResponse loginAlreadyExistsError() {
        return new CommonResponse(ERROR_CODE_LOGIN_ALREADY_EXISTS);
    }

    public static CommonResponse clientUserNotFoundError() {
        return new CommonResponse(ERROR_CODE_CLIENT_USER_NOT_FOUND);
    }

    public static CommonResponse wrongClientPasswordError() {
        return new CommonResponse(ERROR_CODE_WRONG_CLIENT_PASSWORD);
    }

    public static CommonResponse internalError() {
        return new CommonResponse(ERROR_CODE_INTERNAL_ERROR);
    }

}
