package ru.seleznev.gacoretest.domain;

public class ClientUser {
    private Long id;
    private String login;
    private String password;

    public ClientUser(Long id,
                      String login,
                      String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ClientUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
