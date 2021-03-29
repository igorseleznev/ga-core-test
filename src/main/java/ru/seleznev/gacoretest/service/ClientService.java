package ru.seleznev.gacoretest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.seleznev.gacoretest.domain.ClientBalance;
import ru.seleznev.gacoretest.domain.ClientUser;
import ru.seleznev.gacoretest.exception.ClientUserNotFoundException;
import ru.seleznev.gacoretest.exception.LoginAlreadyExistsException;
import ru.seleznev.gacoretest.exception.WrongClientPasswordException;
import ru.seleznev.gacoretest.repository.ClientBalanceRepository;
import ru.seleznev.gacoretest.repository.ClientUserRepository;

import java.math.BigDecimal;

@Service
public class ClientService {

    @Autowired
    private ClientUserRepository clientUserRepository;
    @Autowired
    private ClientBalanceRepository clientBalanceRepository;

    @Transactional
    public void registerClient(String login,
                               String password) {
        if (clientUserRepository.existsByLogin(login)) {
            throw new LoginAlreadyExistsException(String.format("Login '%s' already exists", login));
        }
        Long clientUserId = clientUserRepository.createClientUser(login, password);
        clientBalanceRepository.createClientBalance(clientUserId, BigDecimal.ZERO);
    }

    @Transactional(readOnly = true)
    public BigDecimal getBalance(String login,
                                 String password) {
        ClientUser clientUser = clientUserRepository.getClientUserBy(login)
                .orElseThrow(() -> new ClientUserNotFoundException(
                        String.format("Client user was not found for login '%s'", login)));
        if (!clientUser.getPassword().equals(password)) {
            throw new WrongClientPasswordException("Incorrect login or password specified");
        }
        return clientBalanceRepository.getClientBalanceBy(clientUser.getId())
                .map(ClientBalance::getBalance)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("Can't find balance for clientUserId=%d", clientUser.getId())));
    }
}
