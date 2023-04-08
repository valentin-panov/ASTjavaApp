package com.example.definitelynotvulnerableapp.domain.repository;

import com.example.definitelynotvulnerableapp.domain.model.PaymentData;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PaymentDataRepository extends CrudRepository<PaymentData, UUID> {
}
