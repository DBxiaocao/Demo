package me.xiaocao.demo.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.tencent.mm.opensdk.openapi.WXAPIFactory
/**
 * @author 　xiaocao
 * Description　　
 * Date:2019/5/23 0023 18:18
 */
class WXEntryActivity : Activity(), IWXAPIEventHandler {
    lateinit var iwxapi: IWXAPI
    override fun onResp(baseResp: BaseResp?) {
        if (baseResp != null) {//分享
            when (baseResp.type) {
                ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX -> finish()
                ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX -> finish()
                else -> {
                    finish()
                }
            }
        }
        if (baseResp != null) {//登录
            when (baseResp.errCode) {
                BaseResp.ErrCode.ERR_OK -> {//用户同意
                    val newsResp = baseResp as SendAuth.Resp
                    finish()
                }
                BaseResp.ErrCode.ERR_AUTH_DENIED -> {//用户拒绝授权
                    finish()
                }
                BaseResp.ErrCode.ERR_USER_CANCEL -> {//用户取消
                    finish()
                }
            }
        }
        finish()
    }

    override fun onReq(p0: BaseReq?) {
        finish()
    }

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