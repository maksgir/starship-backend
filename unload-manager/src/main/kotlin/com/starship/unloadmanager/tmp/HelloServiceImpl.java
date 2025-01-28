package com.starship.unloadmanager.tmp;


import com.starship.unloadmanager.dto.TestResponse;
import jakarta.jws.WebService;


@WebService(serviceName = "HelloService", endpointInterface = "com.starship.unloadmanager.tmp.HelloService")
public class HelloServiceImpl implements HelloService {

    @Override
    public TestResponse hello(String name) {
        TestResponse response = new TestResponse();
        response.setLength(name.length());
        return response;
    }
}
