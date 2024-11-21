package com.cg.ecommerceCandyshop.backend.domain.port;

import com.cg.ecommerceCandyshop.backend.domain.model.User;

public interface IUserRepository {
    User save(User user);
    User findByEmail(String email);
    User findById(Integer id);
}
