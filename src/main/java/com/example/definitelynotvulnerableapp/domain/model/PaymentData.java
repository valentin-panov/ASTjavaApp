package com.example.definitelynotvulnerableapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentData {

    @Id
    @GeneratedValue
    private UUID id;
    private String cardNumber;
    private String expiredIn;
    private String cvc;

    @OneToOne(mappedBy = "paymentData")
    private UserData owner;
}
