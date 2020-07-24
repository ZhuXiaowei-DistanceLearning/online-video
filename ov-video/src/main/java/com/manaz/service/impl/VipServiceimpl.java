package com.manaz.service.impl;

import com.manaz.VO.xQueryParam;
import com.manaz.mapper.VipMapper;
import com.manaz.pojo.xxx;
import com.manaz.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxw
 * @date 2020/7/24 20:44
 */
@Service
public class VipServiceimpl implements VipService {
    @Autowired
    private VipMapper vipMapper;
    @Override
    public Page cacaIndex(xQueryParam baseQueryParam) {
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching().withNullHandler(ExampleMatcher.NullHandler.IGNORE) //构建对象
//                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
//                .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
                .withMatcher("title", match -> match.contains())
                .withMatcher("category", match -> match.contains())
                .withIgnorePaths("_class");//采用“包含匹配”的方式查询
//                .withIgnorePaths("biaoqian", "category");  //忽略属性，不参与查询
        xxx caca = new xxx();
        Sort by = null;
        if (baseQueryParam.getKeyword() != null && !"".equals(baseQueryParam.getKeyword())) {
            caca.setTitle(baseQueryParam.getKeyword());
        }
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "pubTime"));
        if (baseQueryParam.getSort() != null) {
            if (baseQueryParam.getDESC()) {
                orders.add(new Sort.Order(Sort.Direction.DESC, baseQueryParam.getSort()));
            } else {
                orders.add(new Sort.Order(Sort.Direction.ASC, baseQueryParam.getSort()));
            }
        }
        Example<xxx> sExample = Example.of(caca, matcher);
        Pageable pageable = null;
        by = Sort.by(orders);
        if (by == null) {
            pageable = PageRequest.of(baseQueryParam.getOffset(), baseQueryParam.getLimit());
        } else {
            pageable = PageRequest.of(baseQueryParam.getOffset(), baseQueryParam.getLimit(), by);
        }
        Page<xxx> page = vipMapper.findAll(sExample, pageable);
        return page;
    }
}
