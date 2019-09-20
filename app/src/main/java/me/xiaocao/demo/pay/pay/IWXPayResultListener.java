package me.xiaocao.demo.pay.pay;


public interface IWXPayResultListener {
    void onPaySuccess(String message, int code);

    void onPayFail(String message, int code);

    void onPayCancel();
}
