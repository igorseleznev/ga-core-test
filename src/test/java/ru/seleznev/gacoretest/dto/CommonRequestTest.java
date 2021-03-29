package ru.seleznev.gacoretest.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;
import ru.seleznev.gacoretest.dto.enumeration.RequestType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommonRequestTest {

    private XmlMapper xmlMapper = new XmlMapper();

    @Test
    public void testMappingFromXmlToJavaObject() throws JsonProcessingException {
        CommonRequest commonRequest =
                xmlMapper.readValue(getCommonRequestXml(), CommonRequest.class);
        assertEquals(RequestType.CREATE_AGT, commonRequest.getRequestType());
        assertNotNull(commonRequest.getExtraElements());
        assertEquals(2, commonRequest.getExtraElements().size());
        assertEquals("login", commonRequest.getExtraElements().get(0).getName());
        assertEquals("123456", commonRequest.getExtraElements().get(0).getValue());
        assertEquals("password", commonRequest.getExtraElements().get(1).getName());
        assertEquals("pwd", commonRequest.getExtraElements().get(1).getValue());
    }

    private String getCommonRequestXml() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<request>\n" +
                "\t<request-type>CREATE-AGT</request-type>\n" +
                "\t<extra name=\"login\">123456</extra>\n" +
                "\t<extra name=\"password\">pwd</extra>\n" +
                "</request>";
    }
}
