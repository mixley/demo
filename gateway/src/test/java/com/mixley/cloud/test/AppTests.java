package com.mixley.cloud.test;

import com.mixley.cloud.common.beans.BeanContextHolder;
import com.mixley.cloud.gateway.GateWayApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest(classes = GateWayApplication.class)
public class AppTests {
    @Test
    public void testEnumOut() {
        Map<String, String> collect = BeanContextHolder.getInstance().getContextList().stream()
                .collect(Collectors.toMap(context -> context.content().code()
                        , context -> context.content().describe()));
        System.out.println(collect);
    }

}
