package com.mgrru.sbsm.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mgrru.sbsm.anno.LoginValidate;
import com.mgrru.sbsm.service.MasterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("master")
public class MasterController {
    @Autowired
    private MasterService masterService;

    @PostMapping("login")
    @LoginValidate(validate = false)
    public String login(@RequestBody String json) {
        JSONObject obj = JSON.parseObject(json);
        Integer id = obj.getInteger("id");
        String password = obj.getString("password");
        String result = masterService.login(id, password);
        return result;
    }

    @PostMapping("hello")
    public String hello(@RequestBody String json) {
        return JSON.parse(json).toString();
    }
}