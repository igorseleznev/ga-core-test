package ru.seleznev.gacoretest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.seleznev.gacoretest.domain.ClientUser;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientUserRepository {
    private static final String CREATE_CLIENT_USER_SQL =
            "INSERT INTO client_user (id, login, password)" +
                    " VALUES (nextval('general_sequence'), :login, :password)" +
                    " RETURNING id";
    private static final String EXISTS_BY_LOGIN_SQL =
            "SELECT COUNT(*) FROM client_user WHERE login=:login;";
    private static final String GET_CLIENT_USER_BY_LOGIN_SQL =
            "SELECT id, login, password FROM client_user WHERE login=:login;";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * @return client_user.id
     */
    public Long createClientUser(String login,
                                 String password) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(
                CREATE_CLIENT_USER_SQL,
                new MapSqlParameterSource()
                        .addValue("login", login)
                        .addValue("password", password),
                keyHolder
        );
        return keyHolder.getKey().longValue();
    }

    public boolean existsByLogin(String login) {
        return namedParameterJdbcTemplate.queryForObject(
                EXISTS_BY_LOGIN_SQL,
                new MapSqlParameterSource().addValue("login", login),
                Integer.class) > 0;
    }

    public Optional<ClientUser> getClientUserBy(String login) {
        List<ClientUser> clientUserList = namedParameterJdbcTemplate.query(
                GET_CLIENT_USER_BY_LOGIN_SQL,
                new MapSqlParameterSource().addValue("login", login),
                (rs, rowNum) -> new ClientUser(
                        rs.getLong("id"),
                        rs.getString("login"),
                        rs.getString("password")
                ));
        if (clientUserList.size() > 1) {
            throw new IllegalStateException(
                    String.format("Client with login '%s' contains multiple client users", login));
        }
        return clientUserList.isEmpty() ? Optional.empty() : Optional.of(clientUserList.get(0));
    }
}
