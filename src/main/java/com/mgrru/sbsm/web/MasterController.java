package com.mgrru.sbsm.web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mgrru.sbsm.anno.LoginValidate;
import com.mgrru.sbsm.entity.JwtUtil;
import com.mgrru.sbsm.entity.Master;
import com.mgrru.sbsm.service.MasterService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@LoginValidate
public class MasterController {
    @Autowired
    private MasterService masterService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("hello")
    public String hello(@RequestBody String json) {
        return JSON.parse(json).toString();
    }

    @PostMapping("login")
    @LoginValidate(validate = false)
    public String login(@RequestBody String json) {
        JSONObject obj = JSON.parseObject(json);
        Integer id = obj.getInteger("id");
        String password = obj.getString("password");
        String result = masterService.login(id, password);
        return result;
    }

    @PostMapping("register")
    @LoginValidate(validate = false)
    public String register(@RequestBody String json) {
        Master master = JSON.parseObject(json, Master.class);
        if (masterService.registerMaster(master)) {
            return "注册成功!";
        } else {
            return "用户已存在!";
        }

    }

    @PostMapping("recharge")
    public String recharge(@RequestBody String json) {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String token = req.getHeader("Authorization");
        Integer id = jwtUtil.getLoginMasterId(token);
        Master master = masterService.getMaster(id);

        Integer rmb = JSON.parseObject(json).getInteger("rmb");
        log.info("rmb:"+rmb);
        String res = masterService.recharge(master, rmb);

        return res;
    }

}