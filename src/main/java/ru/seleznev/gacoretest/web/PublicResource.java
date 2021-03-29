package ru.seleznev.gacoretest.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.seleznev.gacoretest.dto.CommonRequest;
import ru.seleznev.gacoretest.dto.CommonResponse;
import ru.seleznev.gacoretest.dto.CommonResponseBuilder;
import ru.seleznev.gacoretest.dto.ExtraElement;
import ru.seleznev.gacoretest.service.ClientService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/api/public")
public class PublicResource {

    private static final Logger logger = LoggerFactory.getLogger(PublicResource.class);

    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/common/request/process",
            produces = MediaType.APPLICATION_XML_VALUE,
            consumes = MediaType.APPLICATION_XML_VALUE)
    public CommonResponse processCommonRequest(
            @Valid @RequestBody CommonRequest request) {
        logger.debug("Run processCommonRequest with request: {}", request);
        switch (request.getRequestType()) {
            case CREATE_AGT -> {
                String login = getLogin(request)
                        .orElseThrow(() -> new IllegalArgumentException("Login was not found in request"));
                String password = getPassword(request)
                        .orElseThrow(() -> new IllegalArgumentException("Password was not found in request"));
                clientService.registerClient(login, password);
                return CommonResponseBuilder.ok();
            }
            case GET_BALANCE -> {
                String login = getLogin(request)
                        .orElseThrow(() -> new IllegalArgumentException("Login was not found in request"));
                String password = getPassword(request)
                        .orElseThrow(() -> new IllegalArgumentException("Password was not found in request"));
                BigDecimal balance = clientService.getBalance(login, password);
                return CommonResponseBuilder.ok(Arrays.asList(
                        Optional.of(balance)
                                .map(balanceValue -> {
                                    ExtraElement extraElement = new ExtraElement();
                                    extraElement.setName("balance");
                                    extraElement.setValue(balanceValue.toString());
                                    return extraElement;
                                }).get()
                ));
            }
            default -> throw new UnsupportedOperationException(
                    String.format("RequestType %s is not supported", request.getRequestType()));
        }
    }

    private Optional<String> getLogin(CommonRequest request) {
        for (ExtraElement extraElement : request.getExtraElements()) {
            if ("login".equals(extraElement.getName())) {
                return Optional.of(extraElement.getValue());
            }
        }
        return Optional.empty();
    }

    private Optional<String> getPassword(CommonRequest request) {
        for (ExtraElement extraElement : request.getExtraElements()) {
            if ("password".equals(extraElement.getName())) {
                return Optional.of(extraElement.getValue());
            }
        }
        return Optional.empty();
    }
}
