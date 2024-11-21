package com.cg.ecommerceCandyshop.backend.application;

import com.cg.ecommerceCandyshop.backend.domain.model.User;
import com.cg.ecommerceCandyshop.backend.domain.port.IUserRepository;

public class RegistrationService {
    private final IUserRepository iUserRepository;

    public RegistrationService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public User register (User user){
        return iUserRepository.save(user);
    }
}
