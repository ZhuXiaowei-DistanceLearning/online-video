package com.manaz.mapper;

import com.manaz.pojo.Caca;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zxw
 * @date 2019/11/25 20:42
 */
@Repository
public interface IndexMapper extends MongoRepository<Caca, String> {
}
