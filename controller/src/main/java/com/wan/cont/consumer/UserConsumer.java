package com.wan.cont.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wan.ent.iserver.IUserServer;
import com.wan.ent.util.Msg;
import com.wan.ent.util.ResultUtil;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    @Reference(check = false)
    private IUserServer iUserServer;


    public Msg pushNoticeToWX(String userId) {

        iUserServer.pushNoticeToWX(userId);

        return ResultUtil.success("200","操作成功",null);
    }
}
