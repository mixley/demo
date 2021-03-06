package com.mixley.cloud.gateway.controller;

import com.mixley.cloud.common.beans.entity.BeanContext;
import com.mixley.cloud.common.core.entity.R;
import com.mixley.cloud.common.beans.BeanContextHolder;
import com.mixley.cloud.common.beans.entity.Content;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: 李志锐
 * @CreateTime: 2019-12-28 10:43
 * @Description:
 **/
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class TestControl {
//    private final BeanContextHolder beanContextHolder;
    @GetMapping
    public R test() {
        List<BeanContext> contextList = BeanContextHolder.getInstance().getContextList();
//        Map<String, Map<String,String>> collect =
//         beanContextHolder.getContextList().stream()
//                .collect(Collectors.toMap(context -> context.metadata().tagName()
//                        ,context -> context.metadata().expandMap().entrySet().stream().collect(Collectors.toMap(Content::code, Content::describe))));
//        return R.ok(collect);
        return R.ok();
    }

}
