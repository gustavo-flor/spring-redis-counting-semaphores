package com.github.gustavoflor.springrediscountingsemaphores.core.usecase.impl;

import com.github.gustavoflor.springrediscountingsemaphores.common.mapping.UseCase;
import com.github.gustavoflor.springrediscountingsemaphores.core.repository.WalletRepository;
import com.github.gustavoflor.springrediscountingsemaphores.core.usecase.TransferUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class TransferUseCaseImpl implements TransferUseCase {

    private final WalletRepository walletRepository;
    private final RedissonClient redissonClient;

    @Override
    public Output execute(final Input input) {
        final var semaphore = redissonClient.getSemaphore("mySemaphore");
        semaphore.trySetPermits(1);
        final var semaphoreAcquired = semaphore.tryAcquire();
        try {
            if (semaphoreAcquired) {
                Thread.sleep(5000);
                final var amount = input.amount();
                final var originWallet = walletRepository.findById(input.originWalletId()).orElseThrow();
                final var targetWallet = walletRepository.findById(input.originWalletId()).orElseThrow();
                originWallet.debit(amount);
                targetWallet.credit(amount);
                walletRepository.save(originWallet);
                walletRepository.save(targetWallet);
            } else {
                return Output.failed();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (semaphoreAcquired) {
                semaphore.release();
            }
        }
        return Output.finished();
    }

}
