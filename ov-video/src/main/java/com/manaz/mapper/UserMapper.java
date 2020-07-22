package com.manaz.mapper;

import com.manaz.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zxw
 * @date 2020/7/19 22:57
 */
@Repository
public interface UserMapper extends MongoRepository<User, Long> {
}
