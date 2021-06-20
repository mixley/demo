package com.mixley.cloud.common.core.function;

import lombok.SneakyThrows;
import org.springframework.core.PriorityOrdered;

@FunctionalInterface
public interface MyHandel extends PriorityOrdered {

     /**
      * 排序
      *
      * @return int
      */
     default int getOrder() {
          return 0;
     }
     @SneakyThrows
     void handel();
}
