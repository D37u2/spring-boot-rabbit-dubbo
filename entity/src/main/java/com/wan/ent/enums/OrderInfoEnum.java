package com.wan.ent.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: zhoujiong
 * @description: 订单信息枚举
 * @className: OrderInfoEnum
 * @date: 2018/12/6 17:26
 */
public interface OrderInfoEnum {

    /**
     * @author: zhoujiong
     * @description: 是否评价
     * @date: 2018/12/6 17:28
     * @param:
     * @return:
     */
    @AllArgsConstructor
    enum OrderInfoBuyerRate implements OrderInfoEnum{
        ORDER_INFO_BUYER_RATE_0(0,"否"),
        ORDER_INFO_BUYER_RATE_1(1,"是");

        @Getter
        private Integer key;

        @Getter
        private String value;
    }

    /**
     * @author: zhoujiong
     * @description: 活动类型
     * @date: 2018/12/7 11:42
     * @param:
     * @return:
     */
    @AllArgsConstructor
    enum OrderInfoActivityType implements OrderInfoEnum{
        ORDER_INFO_ACTIVITY_TYPE_0(0,"店铺活动"),
        ORDER_INFO_ACTIVITY_TYPE_1(1,"商品活动");

        @Getter
        private Integer key;

        @Getter
        private String value;
    }

    @AllArgsConstructor
    enum OrderInfoCouponType implements OrderInfoEnum{
        ORDER_INFO_COUPON_TYPE_0(0,"店铺券"),
        ORDER_INFO_COUPON_TYPE_1(1,"点蛛券");

        @Getter
        private Integer key;

        @Getter
        private String value;
    }


    /**
     * @author: zhoujiong
     * @description: 订单状态
     * @date: 2018/12/11 17:42
     * @param:
     * @return:
     */
    @AllArgsConstructor
    enum OrderInfoStatus implements OrderInfoEnum{
        ORDER_INFO_STATUS_1(1,"待付款"),
        ORDER_INFO_STATUS_2(2,"待发货"),
        ORDER_INFO_STATUS_3(3,"已发货"),
        ORDER_INFO_STATUS_4(4,"待收货"),
        ORDER_INFO_STATUS_5(5,"已完成"),
        //ORDER_INFO_STATUS_6(6,"售后中"),
        ORDER_INFO_STATUS_7(7,"待自提"),
        ORDER_INFO_STATUS_8(8,"处理中"),
        ORDER_INFO_STATUS_9(9,"已失效"),
        ORDER_INFO_STATUS_10(10,"操作异常"),
        ORDER_INFO_STATUS_11(11,"客户取消"),
        ORDER_INFO_STATUS_12(12,"商家取消");

        @Getter
        private Integer key;

        @Getter
        private String value;
    }

    /**
     * @author: zhoujiong
     * @description: 是否显示售后状态
     * @date: 2018/12/19 15:08
     * @param:
     * @return:
     */
    @AllArgsConstructor
    enum ShowAfterSale implements OrderInfoEnum{
        SHOW_AFTER_SALE_0(0,"否"),
        SHOW_AFTER_SALE_1(1,"是");

        @Getter
        private Integer key;

        @Getter
        private String value;
    }

    @AllArgsConstructor
    enum StoreType implements OrderInfoEnum{
        STORE_TYPE_1(1,"普通店铺"),
        STORE_TYPE_2(2,"蛛网小店");

        @Getter
        private Integer key;

        @Getter
        private String value;
    }
}
