package me.xiaocao.demo.pay

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pay.*
import me.xiaocao.demo.R
import me.xiaocao.demo.base.BaseActivity

class PayActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)
        aliPay.setOnClickListener {
            goAliPay()
        }
        wxPay.setOnClickListener {
            goWxPay()
        }
    }

    private fun goWxPay() {
        //微信支付订单消息
        val wxPayReq = WXPayReq()
        PayHelper.getInstance()
                .create(this)
                .setWXPayReultListener(object :IWXPayResultListener{
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
                .setPayReultListener(object :IAlPayResultListener{
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
}
