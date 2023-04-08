package com.example.definitelynotvulnerableapp.domain.repository;

import com.example.definitelynotvulnerableapp.domain.model.UserData;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserData, UUID> {
    UserData findUserByName(String name);
}
