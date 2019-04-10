package com.wan.cont.controller;

import com.wan.cont.consumer.UserConsumer;
import com.wan.ent.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhoujiong
 * @description: 用户信息控制器
 * @className: UserController
 * @date: 2019/4/3 14:15
 */
@RestController
@RequestMapping(value = "/user/")
public class UserController {

    @Autowired
    private UserConsumer userConsumer;

    @RequestMapping(value = "pushNoticeToWX")
    public Msg pushNoticeToWX(@RequestParam(value = "userId") String userId){

        return userConsumer.pushNoticeToWX(userId);
    }

}
