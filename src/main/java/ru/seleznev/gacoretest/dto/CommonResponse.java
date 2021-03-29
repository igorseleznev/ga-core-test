package ru.seleznev.gacoretest.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "response")
public class CommonResponse {
    static final int ERROR_CODE_OK = 0;
    static final int ERROR_CODE_LOGIN_ALREADY_EXISTS = 1;
    static final int ERROR_CODE_INTERNAL_ERROR = 2;
    static final int ERROR_CODE_CLIENT_USER_NOT_FOUND = 3;
    static final int ERROR_CODE_WRONG_CLIENT_PASSWORD = 4;

    @JacksonXmlProperty(localName = "result-code")
    private int resultCode;

    /*
    Не удалять конструктор по умолчанию, т.к. может потребоваться при сериализации / десериализации объекта
     */
    CommonResponse() {
        this(ERROR_CODE_OK);
    }

    CommonResponse(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return "ClientRegistrationResponse{" +
                "resultCode=" + resultCode +
                '}';
    }
}
