package com.example.definitelynotvulnerableapp;

import com.example.definitelynotvulnerableapp.domain.model.PaymentData;
import com.example.definitelynotvulnerableapp.domain.model.UserData;
import com.example.definitelynotvulnerableapp.domain.repository.PaymentDataRepository;
import com.example.definitelynotvulnerableapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.session.MapSession;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

import java.time.Duration;
import java.util.UUID;

@SpringBootApplication
@EnableSpringHttpSession
@EnableRedisRepositories
public class DefinitelyNotVulnerableAppApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DefinitelyNotVulnerableAppApplication.class, args);
    }

    /* hackz start */
    @Autowired
    private UserService userService;

    @Autowired
    private PaymentDataRepository paymentDataRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public void run(String... args) throws Exception {
        addAdminUser();
        simulateAdminSession();
    }

    private void addAdminUser() {
        PaymentData paymentData = PaymentData.builder()
                .cardNumber("1234123412341234")
                .cvc("123")
                .expiredIn("11/22")
                .build();
        paymentData = paymentDataRepository.save(paymentData);
        userService.createUser(
                UserData.builder().id(UUID.randomUUID())
                        .name("admin")
                        .password("imp0s51ble70gu355p455W0Rd")
                        .role("admin")
                        .email("admin@dnv.app")
                        .paymentData(paymentData)
                        .build()
        );
    }

    private void simulateAdminSession() {
        Session adminSession = new MapSession("1569572998339");
        adminSession.setMaxInactiveInterval(Duration.ofDays(30));
        UserDetails principal = User.builder().username("admin").roles("admin").password("nonull").build();
        adminSession.setAttribute("SPRING_SECURITY_CONTEXT",  new SecurityContextImpl(new UsernamePasswordAuthenticationToken(principal, "nonnull", principal.getAuthorities())));
        sessionRepository.save(adminSession);
    }
    /* hackz end */
}
