package com.rain.cckfs.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: LightRain
 * @Description: 统一返回信息
 * @DateTime: 2023-03-29 12:55
 * @Version：1.0
 **/
@Getter
@Setter
@Builder
@ToString
public class Return {
    private int code;
    private String message;
    private String status;

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
}

