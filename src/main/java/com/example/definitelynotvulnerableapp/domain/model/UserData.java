package com.example.definitelynotvulnerableapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    @Id
    private UUID id;
    private String name;
    private String role;
    private String password;
    private String email;

    @OneToOne
    private PaymentData paymentData;
}
