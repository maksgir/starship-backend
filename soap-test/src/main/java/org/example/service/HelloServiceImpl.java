package org.example.service;


import com.chomik.service.UserService;
import jakarta.ejb.EJB;
import jakarta.jws.WebService;


@WebService(serviceName = "HelloService", endpointInterface = "org.example.service.HelloService")
public class HelloServiceImpl implements HelloService {

    @EJB
    private UserService userService;


    @Override
    public String hello(String name) {
        return userService.ping() + name;
    }
}
