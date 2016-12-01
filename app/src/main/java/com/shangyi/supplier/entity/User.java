package com.shangyi.supplier.entity;

/**
 * Created by Vincent on 2016/12/1.
 */

public class User {
/**
 *  error
 * {"status":"0","errorCode":"800002"}
 * success
 *{"status":"1","data":{"providerInfo":[{"id":140,"status":1,"providerName":"好好过","deviceToken":"AjD0BlcUEQMMJeN32G24VFRwmnRn3guinoN1XeBMgVdR","providerAccout":"18696855784","enterpriseInfoId":142,"type":"1,2"}]}}
 *
 */
    private String status;//结果状态码 0 错误 1 成功
    private String data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    public String toString(){
        return data;
    }
}
