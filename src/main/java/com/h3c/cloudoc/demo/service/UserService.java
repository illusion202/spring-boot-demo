package com.h3c.cloudoc.demo.service;


import com.h3c.cloudoc.demo.model.UserEntity;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

/**
 * Created by lisijie on 2017/5/19.
 */
public interface UserService {

    CompletionStage<Optional<UserEntity>> saveUser (UserEntity entity);

    CompletionStage<Optional<ArrayList<UserEntity>>> listAllUsers();

    CompletionStage<Optional<UserEntity>> queryUserByUuid (String uuid);

    CompletionStage<Void> deleteUser (UserEntity entity);

    CompletionStage<Void> deleteUserByUuid (String uuid);
}
