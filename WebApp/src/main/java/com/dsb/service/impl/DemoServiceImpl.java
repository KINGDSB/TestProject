package com.dsb.service.impl;

import com.dsb.service.IDemoService;

public class DemoServiceImpl implements IDemoService {

    /*
     * (non-Javadoc)
     * 
     * @see com.test.dubbo.service.IDemoService#sayHello(java.lang.String)
     */
    public String sayHello(String name) {
        return "Hello Dubbo,Hello " + name;
    }

}
