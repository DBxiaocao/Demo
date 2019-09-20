package me.xiaocao.demo.pay;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.alipay.sdk.app.PayTask;

/**
 * 支付宝异步支付异步接口
 */

public class PayAsyncTask extends AsyncTask<String, Void, String> {
    private final static String TAG="PayAsyncTask";
    private final Activity activity;
    private final IAlPayResultListener listener;

    //订单支付成功
    private static final String AL_PAY_STATUS_SUCCESS = "9000";
    //订单处理中
    private static final String AL_PAY_STATUS_PAYING = "8000";
    //订单支付失败
    private static final String AL_PAY_STATUS_FAIL = "4000";
    //用户取消
    private static final String AL_PAY_STATUS_CANCEL = "6001";
    //支付网络错误
    private static final String AL_PAY_STATUS_CONNECT_ERROR = "6002";

    public PayAsyncTask(Activity activity, IAlPayResultListener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        final String alPaySign = params[0];
        final PayTask payTask = new PayTask(activity);
        return payTask.pay(alPaySign, true);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.e(TAG,"支付宝返回结果数据：" + result);
        final PayResult payResult = new PayResult(result);
        // 支付宝返回此次支付结构及加签，建议对支付宝签名信息拿签约是支付宝提供的公钥做验签
        final String resultInfo = payResult.getResult();
        final String resultStatus = payResult.getResultStatus();
        Log.e(TAG,"resultInfo：" + resultInfo);
        Log.e(TAG,"resultStatus：" + resultStatus);
        switch (resultStatus) {
            case AL_PAY_STATUS_SUCCESS:
                if (listener != null) {
                    listener.onPaySuccess();
                }
                break;
            case AL_PAY_STATUS_FAIL:
                if (listener != null) {
                    listener.onPayFail();
                }
                break;
            case AL_PAY_STATUS_PAYING:
                if (listener != null) {
                    listener.onPaying();
                }
                break;
            case AL_PAY_STATUS_CANCEL:
                if (listener != null) {
                    listener.onPayCancel();
                }
                break;
            case AL_PAY_STATUS_CONNECT_ERROR:
                if (listener != null) {
                    listener.onPayConnectError();
                }
                break;
            default:
                break;
        }
    }
}
