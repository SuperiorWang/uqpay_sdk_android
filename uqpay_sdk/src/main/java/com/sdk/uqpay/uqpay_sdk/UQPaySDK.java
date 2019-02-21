package com.sdk.uqpay.uqpay_sdk;

import android.app.Activity;
import android.content.Context;

import com.unionpay.UPPayAssistEx;
import com.unionpay.UPQuerySEPayInfoCallback;
import com.unionpay.uppay.PayActivity;

public class UQPaySDK {

    private UQPaySDK(){}
    /**
     * SEName与seType对应表格
     *       SEName             seType
     *     Samsung Pay            02
     *     Huawei Pay             04
     *     Meizu Pay              27
     *     Le Pay                 30
     *     ZTE Pay                21
     *     Mi Pay                 25
     *     vivo Pay               33
     *     Smartisan Pay          32
     */
    public enum PhoneType{
        Samsung("02"),
        Huawei("04"),
        Meizu("27"),
        Le("30"),
        ZTE("21"),
        Mi("25"),
        vivo("33"),
        Smartisan("32");
        private final String phone;

        private PhoneType(String phone){
            this.phone = phone;
        }

        public String getPhone() {
            return phone;
        }
    }

    /**
     * 启动支付控件的接口
     * @param activity       用于启动支付控件的活动对象
     * @param orderInfo      订单信息为交易流水号，即TN,为商户后台从银联后台获取
     * @param mode           银联后台环境标识，“00”将在银联正式环境发起交易,“01”将在银联测试环境发起 交易
     * @return               UPPayAssistEx.PLUGIN_VALID —— 该终端已经安装控件，并启动控件 UPPayAssistEx.PLUGIN_NOT_FOUND — 手机终端尚未安装支付控件，需要先安装支付控件
     */


    public static int startPay(Activity activity, String orderInfo, String mode) {
        return UPPayAssistEx.startPay(activity, null, null, orderInfo, mode);
    }

    public static void startPayByJAR(Activity activity, String orderInfo, String mode) {
        UPPayAssistEx.startPayByJAR(activity, PayActivity.class, null,null,orderInfo,mode);
    }

    /**
     * 检查手机 pay 状态的接口
     * @param context  用于获取启动支付控件的活动对象的context
     * @param callback 该接口定义如下:
    public interface UPQuerySEPayInfoCallback {
    public void onResult(String SEName, String seType, int cardNumbers,
    Bundle reserved);
    public void onError(String SEName, String seType, String errorCode, String errorDesc);
    }

    需要实现两个方法，分别是
    public void onResult(String SEName, String seType, int cardNumbers,Bundle reserved);
    该方法在正常情况下调用，参数如下:
    SEName —— 手机 pay 名称，如表 1
    seType —— 与手机 pay 名称对应的类别，如表 1 cardNumbers —— 卡数量
    4
    手机支付控件接入指南
    reserved —— 保留字段
    public void onError(String SEName, String seType, String errorCode,String errorDesc);
    该方法会在检测到异常时调用，参数如下 SEName ——手机 pay 名称，如表 1
    SeType ——与手机 pay 名称对应的类别，如表 1 errorCode —— 错误情况及对应的错误码:
    1.检测未安装 TSM 控件 ERROR_NOT_SUPPORT = "01" 2.XXpay 未安装 ERROR_NOT_SUPPORT = "01" 3.硬件不支持 XXpay ERROR_NOT_SUPPORT = "01" 4.未开通 XXpay ERROR_NOT_READY = "02" 5.可用卡数为 0 ERROR_NOT_READY = "02"
    返回值:
    errorDesc —— 根据错误码提示相应的字符串
     * @return   .SUCCESS—— 成功获取 SEPay 状态
    UPSEInfoResp. — 传入参数有一个以上为空，获取失败
     */
    public static int getSEPayinfo(Context context, UPQuerySEPayInfoCallback callback){
        return UPPayAssistEx.getSEPayInfo(context, callback);

    }

    /**
     * 指定手机 pay 支付接口
     * @param context     用于获取启动支付控件的活动对象的context
     * @param orderInfo   订单信息为交易流水号，即TN，为商户后台从银联后台获取
     * @param mode        银联后台环境标识，“00”将在银联正式环境发起交易,“01”将在银联测试环境发起 交易
     * @param phoneType   手机pay支付类别 见枚举
     * @return
     */

    public static int startSEPay(Context context, String orderInfo, String mode, PhoneType phoneType){
        return UPPayAssistEx.startSEPay(context,null,null, orderInfo,mode, phoneType.getPhone());
    }

    /**
     * 检查是否安装银联 Apk 的接口
     * @param context  用于启动支付控件的context环境
     * @return  true: 该终端安装控件apk
     */
    public static boolean checkInstalled (Context context){
        return UPPayAssistEx.checkWalletInstalled(context);
    }


    public static boolean installUPPayPlugin(Context context){
        return UPPayAssistEx.installUPPayPlugin(context);
    }

}
