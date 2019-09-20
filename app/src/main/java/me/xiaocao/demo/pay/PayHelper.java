package me.xiaocao.demo.pay;

import android.app.Activity;
import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;




/**
 * @author 　xiaocao
 * Description　　快速支付，集成了支付宝和微信支付
 * Date:　2019/9/20 002011:27　
 */
public class PayHelper {
    private Activity mActivity;
    private IAlPayResultListener mIAlPayResultListener = null;
    private IWXPayResultListener mIwxPayResultListener = null;

    public static class Holder {
        private static final PayHelper INTANCE = new PayHelper();
    }

    public static PayHelper getInstance() {
        return Holder.INTANCE;
    }

    /**
     * 生成实例
     *
     * @param fragment
     * @return
     */
    public PayHelper create(Fragment fragment) {
        this.mActivity = fragment.getActivity();
        return this;
    }

    /**
     * 生成实例
     *
     * @param activity
     * @return
     */
    public PayHelper create(Activity activity) {
        this.mActivity = activity;
        return this;
    }


    /**
     * 设置支付宝回调监听
     *
     * @param listener
     * @return
     */
    public PayHelper setPayReultListener(IAlPayResultListener listener) {
        this.mIAlPayResultListener = listener;
        return this;
    }

    /**
     * 设置微信回调
     *
     * @param listener
     * @return
     */
    public PayHelper setWXPayReultListener(IWXPayResultListener listener) {
        this.mIwxPayResultListener = listener;
        return this;
    }

    /**
     * 调起支付宝支付
     *
     * @param paySign 由服务器生成了订单的参数
     */
    public void alPay(String paySign) {
        final PayAsyncTask payAsyncTask = new PayAsyncTask(mActivity, mIAlPayResultListener);
        payAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paySign);
    }

    /**
     * 调起微信支付
     *
     * @param model  服务器生成参数
     */
    public void weChatPay(WXPayReq model) {
        final IWXAPI iwxapi = WXAPIFactory.createWXAPI(mActivity,"key", false);
        iwxapi.registerApp("key");
        final PayReq payReq = new PayReq();
        payReq.appId = model.appid;
        payReq.prepayId = model.prepayid;
        payReq.partnerId = model.partnerid;
        payReq.packageValue = model.packageX;
        payReq.timeStamp = model.timestamp;
        payReq.nonceStr = model.noncestr;
        payReq.sign = model.sign;
        payReq.extData = "app data"; // optional
        iwxapi.sendReq(payReq);
    }


    public IAlPayResultListener getIAlPayResultListener() {
        return mIAlPayResultListener;
    }

    public IWXPayResultListener getIwxPayResultListener() {
        return mIwxPayResultListener;
    }
}
