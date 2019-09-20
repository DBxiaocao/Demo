package me.xiaocao.demo.pay.pay;

/**
 * 支付宝支付监听接口
 */

public interface IAlPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
