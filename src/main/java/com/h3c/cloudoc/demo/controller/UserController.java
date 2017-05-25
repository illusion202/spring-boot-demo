package com.h3c.cloudoc.demo.controller;

import com.h3c.cloudoc.demo.model.UserEntity;
import com.h3c.cloudoc.demo.service.UserService;
import com.h3c.cloudoc.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * Created by lisijie on 2017/5/19.
 */
@Api(value = "用户类Controller",tags = {"User Controller"},description = "描述信息")
@RestController
@RequestMapping("cloudoc/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @GetMapping(value = "/all")
    CompletionStage getAllUsers () {
        return userService.listAllUsers().thenApplyAsync(opt -> {
            logger.info("get all users");
            return ResponseEntity.ok(opt.isPresent() ? opt.get() : Collections.emptyList());
        }).exceptionally(throwable -> {
            logger.error("get all users error:", throwable);
            return ResponseEntity.status(500).build();
        });
    }

    @ApiOperation(value = "根据ID查询用户", notes = "根据ID查询用户")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String")
    @GetMapping(value = "/{userId}/single")
    CompletionStage getUser (@PathVariable(value = "userId") @NotNull String uuid) {
        return userService.queryUserByUuid(uuid).thenApplyAsync(opt -> {
            logger.info("get user" + uuid);
            return ResponseEntity.ok(opt.isPresent() ? opt.get() : null);
        }).exceptionally(throwable -> {
            logger.error("get user by uuid error:", throwable);
            return ResponseEntity.status(500).build();
        });
    }

    @ApiOperation(value = "创建用户", notes = "创建用户")
    @ApiImplicitParam(name = "userEntity", value = "用户实体类", required = true, dataType = "UserEntity")
    @PostMapping
    CompletionStage createUser (@RequestBody UserEntity userEntity) {
        if (null == userEntity) {
            logger.warn("userEntity is null");
            return CompletableFuture.supplyAsync(() -> ResponseEntity.badRequest());
        }
        userEntity.setUuid(UUID.randomUUID().toString());
        return userService.saveUser(userEntity).thenApplyAsync(opt -> {
            logger.info("save user:" + JsonUtils.toJSon(userEntity));
            return ResponseEntity.ok(opt.get());
        }).exceptionally(throwable -> {
            logger.error("create user error:", throwable);
            return ResponseEntity.status(500).build();
        });
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{userId}/single")
    CompletionStage deleteUser (@PathVariable(value = "userId") String userUuid) {
        return userService.deleteUserByUuid(userUuid).thenApplyAsync(ret -> {
            logger.info("delete user uuid [" + userUuid + "]");
            return ResponseEntity.ok().build();
        }).exceptionally(throwable -> {
            logger.error("delete user error:", throwable);
            return ResponseEntity.status(500).build();
        });
    }

    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    @ApiImplicitParam(name = "userEntity", value = "用户实体类", required = true, dataType = "UserEntity")
    @PutMapping
    CompletionStage updateUser (@RequestBody UserEntity userEntity) {
        if (null == userEntity) {
            logger.warn("userEntity is null");
            return CompletableFuture.supplyAsync(() -> ResponseEntity.status(400).build());
        }
        return userService.saveUser(userEntity).thenApplyAsync(opt -> {
            logger.info("update user:" + JsonUtils.toJSon(userEntity));
            return ResponseEntity.ok(opt.get());
        }).exceptionally(throwable -> {
            logger.error("update user error:", throwable);
            return ResponseEntity.status(500).build();
        });
    }
}
