package com.gupao.shotlink.common.enums;

public enum StatusCodeEnum {

    BOOK_ERROR(1),

    DEVICEID_ERROR(2),

    NUMBER_ERROR(3),

    EXPORT_EXCEL_ERROR(4),
    APPLY_ERROR(5),

    CANCEL_ERROR(6),
    BACK_ERROR(7),
    UNBIND_ERROR(8),
    BACK_ERROR2(16),
    BACK_ERROR3(17),
    BACK_ERROR4(18),
    BACK_ERROR5(19),
    BACK_ERROR6(20),


    SUCCESS(200),
    ERROR(500),
    LOGIN_ERROR(11),
    TOKEN_ERROR(12),

    PARAMETER_ERROR(14),
    SMS_ERROR(15),

    ACCOUNT_EXIST(1601),
    ACCOUNT_NOT_EXIST(1602),
    WECHAT_PAY_APPLY_ERROR(2001)

    ;

    private final int code;

    StatusCodeEnum(int code){
        this.code = code;
    }

    public int getValue(){
        return this.code;
    }


}
