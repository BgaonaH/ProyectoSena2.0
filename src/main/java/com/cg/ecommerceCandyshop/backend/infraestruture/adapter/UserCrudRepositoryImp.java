package com.cg.ecommerceCandyshop.backend.infraestruture.adapter;

import com.cg.ecommerceCandyshop.backend.domain.model.User;
import com.cg.ecommerceCandyshop.backend.domain.port.IUserRepository;
import com.cg.ecommerceCandyshop.backend.infraestruture.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserCrudRepositoryImp implements IUserRepository {
    private final IUserCrudRepository iUserCrudRepository;
    private final UserMapper userMapper;

    public UserCrudRepositoryImp(IUserCrudRepository iUserCrudRepository,  UserMapper userMapper) {
        this.userMapper = userMapper;
        this.iUserCrudRepository = iUserCrudRepository;
    }

    @Override
    public User save(User user) {
        return userMapper.toUser(iUserCrudRepository.save( userMapper.toUserEntity(user) ));
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.toUser(iUserCrudRepository.findByEmail(email).orElseThrow(
                ()-> new RuntimeException ("User with email: "+email+ " not found")
        )   );
    }

    @Override
    public User findById(Integer id) {
        return  userMapper.toUser(iUserCrudRepository.findById(id).get());
    }
}
