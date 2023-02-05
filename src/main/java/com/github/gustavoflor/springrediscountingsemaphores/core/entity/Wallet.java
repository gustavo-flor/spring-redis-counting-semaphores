package com.github.gustavoflor.springrediscountingsemaphores.core.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "wallets")
@Getter
@Setter
@Builder
public class Wallet {

    @Id
    private String id;

    private BigDecimal balance;

    public void credit(final BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void debit(final BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    public static Wallet with(final BigDecimal balance) {
        return Wallet.builder()
            .balance(balance)
            .build();
    }

}
