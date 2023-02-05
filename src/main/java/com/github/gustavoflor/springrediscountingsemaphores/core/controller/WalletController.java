package com.github.gustavoflor.springrediscountingsemaphores.core.controller;

import com.github.gustavoflor.springrediscountingsemaphores.core.entity.Wallet;
import com.github.gustavoflor.springrediscountingsemaphores.core.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletRepository walletRepository;

    @PostMapping
    public Wallet create() {
        return walletRepository.save(Wallet.with(new BigDecimal("500")));
    }

    @GetMapping
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }

}
