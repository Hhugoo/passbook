package com.imooc.merchants.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用的响应对象
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    /** 错误码， 正确返回0 */
    private Integer errorCode;

    /** 错误消息， 正确返回空字符串 */
    private String errorMsg = "";

    private Object data;

    public Response(Object data) {
        this.data = data;
    }

}
