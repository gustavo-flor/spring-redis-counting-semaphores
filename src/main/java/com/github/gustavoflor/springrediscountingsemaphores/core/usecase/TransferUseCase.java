package com.github.gustavoflor.springrediscountingsemaphores.core.usecase;

import java.math.BigDecimal;

public interface TransferUseCase {

    Output execute(Input input);

    record Input(String originWalletId,
                 String targetWalletId,
                 BigDecimal amount) {

    }

    record Output(Status status) {

        enum Status {
            FINISHED,
            FAILED
        }

        public static Output finished() {
            return new Output(Status.FINISHED);
        }

        public static Output failed() {
            return new Output(Status.FAILED);
        }

    }

}
