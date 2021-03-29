package ru.seleznev.gacoretest.domain;

import java.math.BigDecimal;

public class ClientBalance {
    private Long id;
    private Long clientUserId;
    private BigDecimal balance;

    public ClientBalance(Long id,
                         Long clientUserId,
                         BigDecimal balance) {
        this.id = id;
        this.clientUserId = clientUserId;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientUserId() {
        return clientUserId;
    }

    public void setClientUserId(Long clientUserId) {
        this.clientUserId = clientUserId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "ClientBalance{" +
                "id=" + id +
                ", clientUserId=" + clientUserId +
                ", balance=" + balance +
                '}';
    }
}
