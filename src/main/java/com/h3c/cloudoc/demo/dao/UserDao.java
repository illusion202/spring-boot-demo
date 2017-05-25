package com.h3c.cloudoc.demo.dao;

import com.h3c.cloudoc.demo.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/5/19.
 */
public interface UserDao extends CrudRepository<UserEntity, String>, PagingAndSortingRepository<UserEntity, String> {
}
