package ru.seleznev.gacoretest.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import ru.seleznev.gacoretest.dto.enumeration.RequestType;

import java.util.List;

@JacksonXmlRootElement(localName = "request")
public class CommonRequest {
    @JacksonXmlProperty(localName = "request-type")
    private RequestType requestType;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "extra")
    private List<ExtraElement> extraElements;

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public List<ExtraElement> getExtraElements() {
        return extraElements;
    }

    public void setExtraElements(List<ExtraElement> extraElements) {
        this.extraElements = extraElements;
    }

    @Override
    public String toString() {
        return "ClientRegistrationRequest{" +
                "requestType='" + requestType + '\'' +
                ", extraElements=" + extraElements +
                '}';
    }
}
