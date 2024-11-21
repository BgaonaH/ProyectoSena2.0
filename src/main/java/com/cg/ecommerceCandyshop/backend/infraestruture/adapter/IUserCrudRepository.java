package com.cg.ecommerceCandyshop.backend.infraestruture.adapter;

import com.cg.ecommerceCandyshop.backend.infraestruture.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserCrudRepository extends CrudRepository<UserEntity,Integer> {
    Optional<UserEntity> findByEmail(String email);
}
