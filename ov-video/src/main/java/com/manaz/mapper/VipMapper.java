package com.manaz.mapper;

import com.manaz.pojo.xxx;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zxw
 * @date 2019/11/25 20:42
 */
@Repository
public interface VipMapper extends MongoRepository<xxx, String> {
}
