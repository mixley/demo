package com.mixley.cloud.test;

import com.mixley.cloud.common.beans.BeanContextHolder;
import com.mixley.cloud.common.beans.entity.Content;
import com.mixley.cloud.gateway.GateWayApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest(classes = GateWayApplication.class)
public class AppTests {
    @Autowired
    BeanContextHolder beanContextHolder;
    @Test
    public void testEnumOut() {
        Map<String, Map<String,String>> collect =
                beanContextHolder.getContextList().stream()
                        .collect(Collectors.toMap(context -> context.metadata().tagName()
                                ,context -> context.contents().stream().collect(Collectors.toMap(Content::code, Content::describe))));
        System.out.println(collect);
    }

}
