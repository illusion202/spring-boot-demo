package com.h3c.cloudoc.demo.service;

import com.google.common.collect.Lists;
import com.h3c.cloudoc.demo.dao.UserDao;
import com.h3c.cloudoc.demo.model.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * Created by lisijie on 2017/5/19.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public CompletionStage<Optional<UserEntity>> saveUser(UserEntity entity) {
        return CompletableFuture.supplyAsync(() -> Optional.ofNullable(userDao.save(entity))).exceptionally(throwable -> {
            logger.error("save user error:", throwable);
            return Optional.empty();
        });
    }

    @Override
    public CompletionStage<Optional<ArrayList<UserEntity>>> listAllUsers() {
        return CompletableFuture.supplyAsync(() -> Optional.ofNullable(Lists.newArrayList(userDao.findAll()))).exceptionally(throwable -> {
            logger.error("list all users error:", throwable);
            return Optional.empty();
        });
    }

    @Override
    public CompletionStage<Optional<UserEntity>> queryUserByUuid(String uuid) {
        return CompletableFuture.supplyAsync(() -> userDao.findById(uuid)).exceptionally(throwable -> {
            logger.error("query user by uuid error", throwable);
            return Optional.empty();
        });
    }

    @Override
    public CompletionStage<Void> deleteUser(UserEntity entity) {
        return CompletableFuture.runAsync(() -> userDao.delete(entity)).exceptionally(throwable -> {
            logger.error("delete user error:", throwable);
            return null;
        });
    }

    @Override
    public CompletionStage<Void> deleteUserByUuid(String uuid) {
        return CompletableFuture.runAsync(() -> userDao.deleteById(uuid)).exceptionally(throwable -> {
            logger.error("delete user error:", throwable);
            return null;
        });
    }

}
