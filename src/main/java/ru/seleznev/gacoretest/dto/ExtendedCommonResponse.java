package ru.seleznev.gacoretest.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class ExtendedCommonResponse extends CommonResponse {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "extra")
    private List<ExtraElement> extraElements;

    /*
    Не удалять конструктор по умолчанию, т.к. может потребоваться при сериализации / десериализации объекта
     */
    ExtendedCommonResponse() {
        super();
    }

    ExtendedCommonResponse(int resultCode) {
        super(resultCode);
    }

    ExtendedCommonResponse(int resultCode,
                           List<ExtraElement> extraElements) {
        this(resultCode);
        this.extraElements = extraElements;
    }

    public List<ExtraElement> getExtraElements() {
        return extraElements;
    }

    public void setExtraElements(List<ExtraElement> extraElements) {
        this.extraElements = extraElements;
    }

    @Override
    public String toString() {
        return "ExtendedCommonResponse{" +
                "extraElements=" + extraElements +
                '}' + super.toString();
    }
}
