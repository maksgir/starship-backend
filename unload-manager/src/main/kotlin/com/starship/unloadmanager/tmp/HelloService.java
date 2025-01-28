package com.starship.unloadmanager.tmp;


import com.starship.unloadmanager.dto.TestResponse;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface HelloService {

    @WebMethod
    TestResponse hello(@WebParam(name = "name") String name);
}
