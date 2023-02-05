package com.github.gustavoflor.springrediscountingsemaphores.core.repository;

import com.github.gustavoflor.springrediscountingsemaphores.core.entity.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends MongoRepository<Wallet, String> {

}
