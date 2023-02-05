package com.github.gustavoflor.springrediscountingsemaphores.core.controller;

import com.github.gustavoflor.springrediscountingsemaphores.core.usecase.TransferUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferUseCase transferUseCase;

    @PostMapping
    public TransferUseCase.Output create(@RequestBody TransferUseCase.Input input) {
        return transferUseCase.execute(input);
    }

}
