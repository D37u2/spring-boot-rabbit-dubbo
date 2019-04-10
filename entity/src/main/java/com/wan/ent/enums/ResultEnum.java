package com.wan.ent.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: zhoujiong
 * @description: 结果集枚举
 * @className: ResultEnum
 * @date: 2019/4/3 14:33
 */
public interface ResultEnum {

    @AllArgsConstructor
    enum result implements ResultEnum{
        RESULT_SUCCESS("200","成功"),
        RESULT_FAIL("400","失败");

        @Getter
        private String key;
        @Getter
        private String value;
    }

}
