package ru.seleznev.gacoretest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.seleznev.gacoretest.domain.ClientBalance;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientBalanceRepository {
    private static final String CREATE_CLIENT_BALANCE_SQL =
            "INSERT INTO client_balance (id, client_user_id, balance)" +
                    " VALUES (nextval('general_sequence'), :clientUserId, :balance)" +
                    " RETURNING id";
    private static final String GET_CLIENT_BALANCE_SQL =
            "SELECT id, client_user_id, balance FROM client_balance WHERE client_user_id=:clientUserId";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * @return client_balance.id
     */
    public Long createClientBalance(Long clientUserId,
                                    BigDecimal balance) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(
                CREATE_CLIENT_BALANCE_SQL,
                new MapSqlParameterSource()
                        .addValue("clientUserId", clientUserId)
                        .addValue("balance", balance),
                keyHolder
        );
        return keyHolder.getKey().longValue();
    }

    public Optional<ClientBalance> getClientBalanceBy(Long clientUserId) {
        List<ClientBalance> clientBalanceList = namedParameterJdbcTemplate.query(
                GET_CLIENT_BALANCE_SQL,
                new MapSqlParameterSource().addValue("clientUserId", clientUserId),
                (rs, rowNum) -> new ClientBalance(
                        rs.getLong("id"),
                        rs.getLong("client_user_id"),
                        rs.getBigDecimal("balance")
                ));
        if (clientBalanceList.size() > 1) {
            throw new IllegalStateException(
                    String.format("Client with id %s contains multiple balances", clientUserId));
        }
        return clientBalanceList.isEmpty() ? Optional.empty() : Optional.of(clientBalanceList.get(0));
    }
}
