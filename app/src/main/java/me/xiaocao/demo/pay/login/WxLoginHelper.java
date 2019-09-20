package me.xiaocao.demo.pay.login;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @author 　xiaocao
 * Description
 * Date:2019/9/20 0020 13:38
 */
public class WxLoginHelper {

    private IWXLoginResultListener iwxLoginResultListener;
    private Activity mActivity;
    private String APP_KEY;
    private IWXAPI iwxapi;

    public static class Holder {
        private static final WxLoginHelper INSTANCE = new WxLoginHelper();
    }

    public static WxLoginHelper getInstance() {
        return Holder.INSTANCE;
    }

    public WxLoginHelper create(Activity activity) {
        this.mActivity = activity;
        return this;
    }

    public WxLoginHelper setKey(String key) {
        this.APP_KEY = key;
        return this;
    }

    public WxLoginHelper wxLogin() {
        iwxapi = WXAPIFactory.createWXAPI(mActivity, APP_KEY, false);
        iwxapi.registerApp(APP_KEY);
        SendAuth.Req req= new  SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "xxxxxx";
        iwxapi.sendReq(req);
        return this;
    }

    public IWXLoginResultListener getIwxLoginResultListener() {
        return iwxLoginResultListener;
    }

    public WxLoginHelper setWxLoginResultListener(IWXLoginResultListener iwxLoginResultListener) {
        this.iwxLoginResultListener = iwxLoginResultListener;
        return this;
    }

    public void onDestory() {
        if (iwxapi != null) {
            iwxapi.detach();
        }
    }
}
