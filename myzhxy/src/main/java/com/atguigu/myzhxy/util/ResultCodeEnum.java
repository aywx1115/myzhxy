package com.atguigu.myzhxy.util;

import lombok.Getter;

/**
 * Return Info
 *
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"Success"),
    FAIL(201, "Fail"),
    SERVICE_ERROR(2012, "Service Abnormal"),
    ILLEGAL_REQUEST( 204, "Illegal Request"),
    PAY_RUN(205, "Payment Running"),
    ARGUMENT_VALID_ERROR(206, "Parameter Validation Error"),

    LOGIN_ERROR(207, "Wrong username or password"),
    LOGIN_AUTH(208, "Not logged in"),
    PERMISSION(209, "Permission Denied"),
    SECKILL_NO_START(210, "The spike hasn't started yet"),
    SECKILL_RUN(211, "In Line"),
    SECKILL_NO_PAY_ORDER(212, "You have unpaid orders"),
    SECKILL_FINISH(213, "Sold out"),
    SECKILL_END(214, "The spike has end"),
    SECKILL_SUCCESS(215, "Place Order Successfully"),
    SECKILL_FAIL(216, "Place Order Failed"),
    SECKILL_ILLEGAL(217, "Illegal Request"),
    SECKILL_ORDER_SUCCESS(218, "Successfully ordered"),
    COUPON_GET(220, "Coupon has been received"),
    COUPON_LIMIT_GET(221, "Coupons have been issued"),
    //2022-02-22
    LOGIN_CODE(222,"Session time out, please login again!"),
    CODE_ERROR(223,"Verification code error!"),
    TOKEN_ERROR(224,"Token Invalid!")
    ;

    private Integer code;

    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}