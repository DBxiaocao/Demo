package me.xiaocao.demo.pay.pay;


import com.google.gson.annotations.SerializedName;

/**
 * 调起微信需要的参数
 * <p>
 */

public class WXPayReq {
    /**
     * appid : wx2d7eff11767d2f5c
     * noncestr : aECnnbM7dptH3sYb
     * package : Sign=WXPay
     * partnerid : 1507090881
     * prepayid : wx112154475062761743e1f7403105049782
     * sign : 9ECEE6D7D65592D421F75B4C36D4A94A
     * timestamp : 1531317287
     */

    @SerializedName("appid")
    public String appid;
    @SerializedName("noncestr")
    public String noncestr;
    @SerializedName("package")
    public String packageX;
    @SerializedName("partnerid")
    public String partnerid;
    @SerializedName("prepayid")
    public String prepayid;
    @SerializedName("sign")
    public String sign;
    @SerializedName("timestamp")
    public String timestamp;

}
