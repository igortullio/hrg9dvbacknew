package com.igortullio.hering.adapter.database.repository;

import com.igortullio.hering.adapter.database.entity.UserEntity;
import com.igortullio.hering.adapter.mapper.UserMapper;
import com.igortullio.hering.core.domain.User;
import com.igortullio.hering.core.exception.HeringException;
import com.igortullio.hering.core.exception.in_use.UserInUseException;
import com.igortullio.hering.core.exception.not_found.AbstractNotFoundException;
import com.igortullio.hering.core.exception.not_found.UserNotFoundException;
import com.igortullio.hering.core.port.RepositoryPort;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryPortImpl implements RepositoryPort<User> {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    public UserRepositoryPortImpl(UserJpaRepository userJpaRepository, UserMapper userMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User find(Long id) {
        UserEntity userEntity = userJpaRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return userMapper.userEntityToUser(userEntity);
    }

    @Override
    public User save(User user) {
        try {
            UserEntity userEntity = userMapper.userToUserEntity(user);
            userEntity = userJpaRepository.save(userEntity);

            return userMapper.userEntityToUser(userEntity);
        } catch (AbstractNotFoundException exception) {
            throw new HeringException(exception.getMessage(), exception);
        }
    }

    @Override
    public User update(Long id, User user) {
        User userInDB = find(id);
        userInDB.setName(user.getName());
        userInDB.setAge(user.getAge());
        userInDB.setPhone(user.getPhone());
        userInDB.setEmail(user.getEmail());

        UserEntity userEntity = userMapper.userToUserEntity(userInDB);
        userEntity = userJpaRepository.save(userEntity);

        return userMapper.userEntityToUser(userEntity);
    }

    @Override
    public void delete(Long id) {
        try {
            userJpaRepository.deleteById(id);
            userJpaRepository.flush();
        } catch (EmptyResultDataAccessException exception) {
            throw new UserNotFoundException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new UserInUseException(id);
        }
    }

}
