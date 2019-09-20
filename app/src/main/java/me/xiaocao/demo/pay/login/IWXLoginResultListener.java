package me.xiaocao.demo.pay.login;

/**
 * @author 　xiaocao
 * Description
 * Date:2019/9/20 0020 13:37
 */
public interface IWXLoginResultListener {
    void onLoginSuccess(String code);
    void onLoginCancel();
    void onLoginError();
}
