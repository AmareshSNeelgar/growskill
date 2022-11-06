package com.platform.growskill.util;

public class  CommonResponse<T> {
    public int status;
    public   T data;
    public String msg;

    public CommonResponse(int status, T data, String msg){
        this.status = status;
        this.data= data;
        this.msg = msg;
    }


}
