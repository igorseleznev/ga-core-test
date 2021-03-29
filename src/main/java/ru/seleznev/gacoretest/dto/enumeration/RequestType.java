package ru.seleznev.gacoretest.dto.enumeration;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RequestType {
    @JsonProperty("CREATE-AGT")
    CREATE_AGT,
    @JsonProperty("GET-BALANCE")
    GET_BALANCE,
    ;
}
