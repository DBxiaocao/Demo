package me.xiaocao.demo.pay

import android.os.Bundle
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import kotlinx.android.synthetic.main.activity_pay.*
import me.xiaocao.demo.R
import me.xiaocao.demo.base.BaseActivity
import me.xiaocao.demo.base.dialog.BottomListDialog
import me.xiaocao.demo.pay.login.IWXLoginResultListener
import me.xiaocao.demo.pay.login.WxLoginHelper
import me.xiaocao.demo.pay.pay.IAlPayResultListener
import me.xiaocao.demo.pay.pay.IWXPayResultListener
import me.xiaocao.demo.pay.pay.PayHelper
import me.xiaocao.demo.pay.pay.WXPayReq

class PayActivity : BaseActivity() {

    private var mIwxAi:IWXAPI?=null
    private val APPKYE=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)
        aliPay.setOnClickListener {
            goAliPay()
        }
        wxPay.setOnClickListener {
            goWxPay()
        }
        wxLogin.setOnClickListener {
            goWxLogin()
        }
        wxShare.setOnClickListener {
            shareDialog()
        }
    }

    private fun shareDialog() {
        BottomListDialog()
                .setItems(arrayOf("微信好友","微信朋友圈"))
                .setOnItemClickListener { _, position ->
                    goWxShare(position)
                }
                .show(supportFragmentManager)
    }

    private fun goWxShare(position: Int) {
        mIwxAi = WXAPIFactory.createWXAPI(mActivity, APPKYE)
        mIwxAi?.registerApp(APPKYE)
        val webpage = WXWebpageObject()
        webpage.webpageUrl = "图片url"
        val msg = WXMediaMessage(webpage)
        msg.title = "分享title"
        msg.description = "分享内容"
        val req = SendMessageToWX.Req()
        req.transaction = buildTransaction("webpage")
        req.message = msg
        req.scene = when (position==0) {
            true -> SendMessageToWX.Req.WXSceneSession//微信好友
            false -> SendMessageToWX.Req.WXSceneTimeline//朋友圈
        }
        mIwxAi?.sendReq(req)
    }

    private fun goWxLogin() {
        WxLoginHelper.getInstance()
                .create(this)
                .setKey(APPKYE)
                .setWxLoginResultListener(object : IWXLoginResultListener {
                    override fun onLoginSuccess(code: String) {

                    }

                    override fun onLoginCancel() {

                    }

                    override fun onLoginError() {

                    }
                })
    }

    private fun goWxPay() {
        //微信支付订单消息
        val wxPayReq = WXPayReq()
        PayHelper.getInstance()
                .create(this)
                .setWXPayReultListener(object : IWXPayResultListener {
                    override fun onPaySuccess(message: String?, code: Int) {
                        showMessage("支付成功")
                    }

                    override fun onPayFail(message: String?, code: Int) {
                        showMessage("支付失败code=$code")
                    }

                    override fun onPayCancel() {
                        showMessage("支付取消")
                    }
                })
                .weChatPay(wxPayReq)
    }

    private fun goAliPay() {
        PayHelper.getInstance()
                .create(this)
                .setPayReultListener(object : IAlPayResultListener {
                    override fun onPaySuccess() {
                        showMessage("支付成功")
                    }

                    override fun onPaying() {
                        showMessage("支付中")
                    }

                    override fun onPayFail() {
                        showMessage("支付失败")
                    }

                    override fun onPayCancel() {
                        showMessage("支付取消")
                    }

                    override fun onPayConnectError() {
                        showMessage("支付错误")
                    }
                })
                .alPay("支付信息")
    }
     fun buildTransaction(type: String?): String {
        return if (type == null) System.currentTimeMillis().toString() else type + System.currentTimeMillis()
    }
    override fun onDestroy() {
        super.onDestroy()
        mIwxAi?.detach()
        WxLoginHelper.getInstance().onDestory()
    }
}
