package com.shangyi.supplier.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.share.sdk.openapi.APAPIFactory;
import com.alipay.share.sdk.openapi.APMediaMessage;
import com.alipay.share.sdk.openapi.APTextObject;
import com.alipay.share.sdk.openapi.IAPApi;
import com.alipay.share.sdk.openapi.SendMessageToZFB;
import com.shangyi.supplier.auth.BaseUIListener;
import com.shangyi.supplier.R;
import com.shangyi.supplier.config.Constants;
import com.shangyi.supplier.config.MyApplication;
import com.shangyi.supplier.util.ToastUtil;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 分享Activity
 */
public class ShareActivity extends AppCompatActivity implements IWeiboHandler.Response {

    @BindView(R.id.rl_share_qq_friends)//发送到QQ好友
            RelativeLayout rlShareQQ;
    @BindView(R.id.rl_share_qq_qzone)//发送到QQ空间
            RelativeLayout rlShareQzone;
    @BindView(R.id.rl_share_weixin_friend)//发送给微信好友
            RelativeLayout rlShareWeixin;
    @BindView(R.id.rl_share_weixin_circle_of_friends)//分享到微信朋友圈
            RelativeLayout ivShareWeixinFriend;
    @BindView(R.id.tv_cancel_share)//取消
            TextView tvCancelShare;
    @BindView(R.id.tv_other)//点击屏幕空白
            TextView tvOther;
    @BindView(R.id.rl_share_weixin_collect)
    RelativeLayout rlWXCollect;//微信收藏
    @BindView(R.id.rl_share_sina)
    RelativeLayout rlShareSina;
    @BindView(R.id.rl_share_ali_pay)
    RelativeLayout rlShareAliPay;

    private IWXAPI api;
    private Bitmap thumb;
    private Tencent mTencent;
    private IAPApi apiAliPay;
    /**sina微博*/
    private IWeiboShareAPI mWeiboShareAPI=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.act_umeng_share_layout, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MyApplication.getScreenParameterWidth(), LinearLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        setContentView(view);
        ButterKnife.bind(this);

        //微信
        api = WXAPIFactory.createWXAPI(this, Constants.WX_APP_ID);
        api.registerApp(Constants.WX_APP_ID);//将应用的appid注册到微信
        thumb = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);
        //QQ
        // Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
        // 其中APP_ID是分配给第三方应用的appid，类型为String。
        mTencent = Tencent.createInstance(Constants.QQ_APP_ID, this.getApplicationContext());
        /**sina微博*/
        mWeiboShareAPI= WeiboShareSDK.createWeiboAPI(this,Constants.SINA_APP_KEY);
        // 注册第三方应用到微博客户端中，注册成功后该应用将显示在微博的应用列表中。
        // 但该附件栏集成分享权限需要合作申请，详情请查看 Demo 提示
        // NOTE：请务必提前注册，即界面初始化的时候或是应用程序初始化时，进行注册
        mWeiboShareAPI.registerApp();

        // 当 Activity 被重新初始化时（该 Activity 处于后台时，可能会由于内存不足被杀掉了），
        // 需要调用 {@link IWeiboShareAPI#handleWeiboResponse} 来接收微博客户端返回的数据。
        // 执行成功，返回 true，并调用 {@link IWeiboHandler.Response#onResponse}；
        // 失败返回 false，不调用上述回调
        if (savedInstanceState != null) {
            mWeiboShareAPI.handleWeiboResponse(getIntent(), this);
        }
        /**分享到支付宝 由于我的app审核未通过，所以无法分享*/
        //创建工具对象实例，此处的APPID为上文提到的，申请应用生效后，在应用详情页中可以查到的支付宝应用唯一标识
        apiAliPay = APAPIFactory.createZFBApi(getApplicationContext(),Constants.ALIPAY_APP_KEY,false);
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // 从当前应用唤起微博并进行分享后，返回到当前应用时，需要在此处调用该函数
        // 来接收微博客户端返回的数据；执行成功，返回 true，并调用
        // {@link IWeiboHandler.Response#onResponse}；失败返回 false，不调用上述回调
        mWeiboShareAPI.handleWeiboResponse(intent, this);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != mTencent)
            mTencent.onActivityResult(requestCode, resultCode, data);

    }

    @OnClick({R.id.rl_share_qq_friends, R.id.rl_share_qq_qzone, R.id.rl_share_weixin_friend, R.id.rl_share_sina,R.id.rl_share_ali_pay,
            R.id.rl_share_weixin_collect, R.id.tv_cancel_share, R.id.rl_share_weixin_circle_of_friends, R.id.tv_other})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_share_qq_friends:
                shareToQQ(getString(R.string.app_name), Constants.APP_SHARED_CONTENT, Constants.APP_SHARED_ADDRESS, "http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif", 2);//QQ好友
                break;
            case R.id.rl_share_qq_qzone:
                shareToQQ(getString(R.string.app_name), Constants.APP_SHARED_CONTENT, Constants.APP_SHARED_ADDRESS, "http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif", 1);//QQ空间
                break;
            case R.id.rl_share_weixin_friend://分享给微信好友
                sendToWeiXin(getString(R.string.app_name), Constants.APP_SHARED_ADDRESS, Constants.APP_SHARED_CONTENT, thumb, 0);
                break;
            case R.id.rl_share_weixin_circle_of_friends://分享到微信朋友圈
                sendToWeiXin(getString(R.string.app_name), Constants.APP_SHARED_ADDRESS, Constants.APP_SHARED_CONTENT, thumb, 1);
                break;
            case R.id.rl_share_weixin_collect://发送到微信收藏页面
                sendToWeiXin(getString(R.string.app_name), Constants.APP_SHARED_ADDRESS, Constants.APP_SHARED_CONTENT, thumb, 2);
                break;
            case R.id.rl_share_sina://分享到新浪微博
//                ToastUtil.defaultToast(MyApplication.getInstance(),"Click");
                sendToSina(getString(R.string.app_name),Constants.APP_SHARED_ADDRESS,Constants.APP_SHARED_CONTENT,"defaultText");
                break;
            case R.id.rl_share_ali_pay:
                sendToAliPay(Constants.APP_SHARED_CONTENT+Constants.APP_SHARED_ADDRESS);
                break;
            case R.id.tv_cancel_share:
                finish();
                break;
            case R.id.tv_other:
                finish();
                break;
            default:
                break;
        }
    }

    private void sendToAliPay(String content) {
        //组装文本消息内容对象
        APTextObject textObject = new APTextObject();
        textObject.text = content;
        //组装分享消息对象
        APMediaMessage mediaMessage = new APMediaMessage();
        mediaMessage.mediaObject = textObject;
        //将分享消息对象包装成请求对象
        SendMessageToZFB.Req req = new SendMessageToZFB.Req();
        req.message = mediaMessage;
        //发送请求
        apiAliPay.sendReq(req);
    }

    /**
     * 分享到微博
     * @param title 网页title
     * @param openUrl 点击打开的URL
     * @param defaultText 默认文字
     * @param description 网页描述
     */
    private void sendToSina(String title,String openUrl,String defaultText,String description) {
        WeiboMessage weiboMessage = new WeiboMessage();
        ImageObject imageObject = new ImageObject();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        imageObject.setImageObject(bitmap);
        weiboMessage.mediaObject = imageObject;
       /* WebpageObject webpageObject=new WebpageObject();
        webpageObject.title=title;//网页的title
        webpageObject.actionUrl=openUrl;//点击分享打开的url
        webpageObject.defaultText=defaultText;
        webpageObject.description=description;//网页描述信息
        weiboMessage.mediaObject=webpageObject;*/

       //发送请求
        SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.message = weiboMessage;
        mWeiboShareAPI.sendRequest(ShareActivity.this, request);
    }

    /**
     * 接收微客户端博请求的数据。
     * 当微博客户端唤起当前应用并进行分享时，该方法被调用。
     *
     * @param baseResp 微博请求数据对象
     * @see {@link IWeiboShareAPI#handleWeiboRequest}
     */
    @Override
    public void onResponse(BaseResponse baseResp) {
        if (baseResp != null) {
            switch (baseResp.errCode) {
                case WBConstants.ErrorCode.ERR_OK:
                    ToastUtil.defaultToast(MyApplication.getInstance(), "分享成功");
                    finish();
                    break;
                case WBConstants.ErrorCode.ERR_CANCEL:
                    ToastUtil.defaultToast(MyApplication.getInstance(), "取消分享");
                    finish();
                    break;
                case WBConstants.ErrorCode.ERR_FAIL:
                    ToastUtil.defaultToast(MyApplication.getInstance(), "分享失败: " + baseResp.errMsg);
                    finish();
                    break;
            }
        }
    }

    /**
     * 官方参考文档地址：http://wiki.open.qq.com/index.php?title=Android_API%E8%B0%83%E7%94%A8%E8%AF%B4%E6%98%8E&=45038#1.13_.E5.88.86.E4.BA.AB.E6.B6.88.E6.81.AF.E5.88.B0QQ.EF.BC.88.E6.97.A0.E9.9C.80QQ.E7.99.BB.E5.BD.95.EF.BC.89
     *
     * @param title       分享的内容title
     * @param openUrl     点击分享内容打开的地址
     * @param description 分享item的描述信息 分享的消息摘要，最长40个字。
     * @param imgUrl      分享item的图片地址
     * @param shareType   1分享到QQ空间 2分享到QQ好友
     */
    public void shareToQQ(String title, String description, String openUrl, String imgUrl, int shareType) {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);//消息类型 图文用默认的
        params.putString(QQShare.SHARE_TO_QQ_TITLE, title);//标题
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, description);//描述信息
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, openUrl);//这条分享消息被好友点击后的跳转URL。
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imgUrl);//分享图片的URL或者本地路径
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, getString(R.string.app_name));
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, shareType);//分享额外选项，两种类型可选（默认是不隐藏分享到QZone按钮且不自动打开分享到QZone的对话框）：QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN，分享时自动打开分享到QZone的对话框。QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE，分享时隐藏分享到QZone按钮
        mTencent.shareToQQ(ShareActivity.this, params, new BaseUIListener(ShareActivity.this));
    }

    /**
     * @param title       分享的标题
     * @param openUrl     点击分享item打开的网页地址url
     * @param description 网页的描述
     * @param icon        分享item的图片
     * @param requestCode 0表示为分享到微信好友  1表示为分享到朋友圈 2表示微信收藏
     */
    public void sendToWeiXin(String title, String openUrl, String description, Bitmap icon, int requestCode) {
        //初始化一个WXWebpageObject对象，填写url
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = openUrl;
        //Y用WXWebpageObject对象初始化一个WXMediaMessage对象，填写标题、描述
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;//网页标题
        msg.description = description;//网页描述
        msg.setThumbImage(icon);
        //构建一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "supplier";
        req.message = msg;
        req.scene = requestCode;
        api.sendReq(req);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }
}
