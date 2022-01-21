package com.bank.employees.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping("api/v1/")
public class TestController {

    @GetMapping(value = "test", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map testApi(){
        HashMap<String, Map> info = new HashMap<>();
        info.put("employee_api_info",
                    new HashMap<String, String>(){{
                        put("status", "ok");
                        put("version", "v1");
                        put("created_by", "Edi :)");
                }});

        return info;
    }
}
