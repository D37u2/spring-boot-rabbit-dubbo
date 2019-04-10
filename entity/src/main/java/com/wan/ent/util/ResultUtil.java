package com.wan.ent.util;

import java.io.Serializable;

/**
 * @author: zhoujiong
 * @description: 返回结果集
 * @className: ResultUtil
 * @date: 2019/4/3 14:48
 */
public class ResultUtil implements Serializable {

    /**
     * @author: zhoujiong
     * @description: 返回成功
     * @date: 2019/4/3 14:48
     * @param: [code, message, data]
     * @return: Msg
     */
    public static Msg success(String code ,String message, Object data ){
        Msg msg = new Msg();

        msg.setCode(code);
        msg.setMsg(message);
        msg.setData(data);
        return msg;
    }

    /**
     * @author: zhoujiong
     * @description: 返回失败
     * @date: 2019/4/3 14:48
     * @param: [code, message]
     * @return: Msg
     */
    public static Msg fail(String code ,String message){
        Msg msg = new Msg();

        msg.setCode(code);
        msg.setMsg(message);
        msg.setData(null);
        return msg;
    }
}
