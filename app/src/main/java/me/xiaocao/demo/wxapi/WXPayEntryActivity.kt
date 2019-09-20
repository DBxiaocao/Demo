package me.xiaocao.demo.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import me.xiaocao.demo.pay.pay.PayHelper

/**
 * @author 　xiaocao
 * Description　　
 * Date:2019/9/19 0019 10:25
 */
class WXPayEntryActivity: Activity(), IWXAPIEventHandler {
    override fun onResp(baseResp: BaseResp?) {
        if (baseResp!=null){//支付
            if (baseResp.type == ConstantsAPI.COMMAND_PAY_BY_WX) {
                val resultCode = baseResp.errCode
                if (resultCode == BaseResp.ErrCode.ERR_OK) {
                    PayHelper.getInstance().create(this).iwxPayResultListener.onPaySuccess(baseResp.errStr,resultCode)
                } else if (resultCode==BaseResp.ErrCode.ERR_USER_CANCEL){
                    PayHelper.getInstance().create(this).iwxPayResultListener.onPayCancel()
                }else{
                    PayHelper.getInstance().create(this).iwxPayResultListener.onPayFail(baseResp.errStr + "" + resultCode,resultCode)
                }
            }
        }
    }

    override fun onReq(p0: BaseReq?) {

    }

    lateinit var iwxapi: IWXAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iwxapi = WXAPIFactory.createWXAPI(this, "key")
        iwxapi.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        iwxapi.handleIntent(intent, this)
    }
}