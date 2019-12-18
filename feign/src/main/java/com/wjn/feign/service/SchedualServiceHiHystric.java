package com.wjn.feign.service;

import com.wjn.feign.service.fallback.SchedualServiceHi;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: jnWang
 * @create: 2019-12-16 17:54
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
