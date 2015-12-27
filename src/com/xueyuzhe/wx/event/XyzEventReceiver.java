/**
 * 
 */
package com.xueyuzhe.wx.event;

import java.util.Date;

import com.ideamoment.wx.config.IdeaWxConfig;
import com.ideamoment.wx.receiver.EventReceiver;
import com.ideamoment.wx.receiver.model.WxImageMessage;
import com.ideamoment.wx.receiver.model.WxLinkMessage;
import com.ideamoment.wx.receiver.model.WxLocationEvent;
import com.ideamoment.wx.receiver.model.WxLocationMessage;
import com.ideamoment.wx.receiver.model.WxMenuEvent;
import com.ideamoment.wx.receiver.model.WxScanEvent;
import com.ideamoment.wx.receiver.model.WxShortVideoMessage;
import com.ideamoment.wx.receiver.model.WxSubscribeEvent;
import com.ideamoment.wx.receiver.model.WxTextMessage;
import com.ideamoment.wx.receiver.model.WxUnsubscribeEvent;
import com.ideamoment.wx.receiver.model.WxVideoMessage;
import com.ideamoment.wx.receiver.model.WxVoiceMessage;
import com.ideamoment.wx.sender.model.WxNullMessage;
import com.ideamoment.wx.sender.model.WxSendMessage;
import com.ideamoment.wx.sender.model.WxSendTextMessage;


/**
 * @author Chinakite
 *
 */
public class XyzEventReceiver implements EventReceiver {

//    String welcomeStr = "终于等到你，还好我没放弃！欢迎关注大易招聘圈。";
    String welcomeStr = "你好，欢迎关注招聘宝。招聘宝是一款全免费使用的招聘管理系统，它能够实现移动传播、HR互动、海量简历以及一站式招聘管理。在这里，我们会定期推送有用的资讯、趣味活动、HR工作技巧等，助力HR更快更准找人才！感谢您的关注！";
    
    /* (non-Javadoc)
     * @see com.ideamoment.wx.event.EventReceiver#subscribeEvent(com.ideamoment.wx.event.bean.WxSubscribeEvent)
     */
    @Override
    public WxSendMessage subscribeEvent(WxSubscribeEvent event) {
        System.out.println("Receive subscribe event.");
        System.out.println("Subscribe openid [" + event.getFromUserName() + "]");
        
        WxSendTextMessage returnMsg = new WxSendTextMessage();
        returnMsg.setToUserName(event.getFromUserName());
        returnMsg.setFromUserName(IdeaWxConfig.get("ideawx.originalid", null));
        returnMsg.setCreateTime((new Date()).getTime());
        returnMsg.setContent(welcomeStr);
        
        return returnMsg;
    }

    /* (non-Javadoc)
     * @see com.ideamoment.wx.event.EventReceiver#unsubscribeEvent(com.ideamoment.wx.event.bean.WxUnsubscribeEvent)
     */
    @Override
    public WxSendMessage unsubscribeEvent(WxUnsubscribeEvent event) {
        System.out.println("Receive Unsubscribe event.");
        System.out.println("Unsubscribe openid [" + event.getFromUserName() + "]");
        
        return new WxNullMessage();
    }

    @Override
    public WxSendMessage imageMessage(WxImageMessage arg0) {
        return new WxNullMessage();
    }

    @Override
    public WxSendMessage linkMessage(WxLinkMessage arg0) {
        return new WxNullMessage();
    }

    @Override
    public WxSendMessage locationEvent(WxLocationEvent arg0) {
        return new WxNullMessage();
    }

    @Override
    public WxSendMessage locationMessage(WxLocationMessage arg0) {
        return new WxNullMessage();
    }

    @Override
    public WxSendMessage menuEvent(WxMenuEvent arg0) {
        return new WxNullMessage();
    }

    @Override
    public WxSendMessage scanEvent(WxScanEvent arg0) {
        return new WxNullMessage();
    }

    @Override
    public WxSendMessage shortVideoMessage(WxShortVideoMessage arg0) {
        return new WxNullMessage();
    }

    @Override
    public WxSendMessage textMessage(WxTextMessage message) {
        WxSendTextMessage returnMsg = new WxSendTextMessage();
        returnMsg.setToUserName(message.getFromUserName());
        returnMsg.setFromUserName(IdeaWxConfig.get("ideawx.originalid", null));
        returnMsg.setCreateTime((new Date()).getTime());
        
//        if(message.getContent().startsWith("日企")) {
//            returnMsg.setContent("恭喜！您已成功参与招聘宝举办的“太君，八路托我给您带个话儿……”有奖回复活动！中奖结果择日公布，敬请期待！");
//        }else 
        if(message.getContent().indexOf("猜角色") > -1) {
            returnMsg.setContent("人人都想当D—猜角色（HR版），答案如下：\nA: HR薪酬经理\nB：HR绩效经理\nC：HR员工关系经理\nD：HR总监\nE：HR招聘经理\n欣赏完整版HR故事：http://w.url.cn/s/Ajf5Wul");
        }else if(message.getContent().indexOf("猜成语") > -1) {
            returnMsg.setContent("“朋友圈猜成语”答案揭晓\n据说全猜对才是移动端达人哦！看你能猜对几个？\n\n1、喜上眉梢\n2、妖魔鬼怪\n……\n查看全部：http://w.url.cn/s/A3sDs72");
        }
        else{
            returnMsg.setContent(welcomeStr);
        }
        
        return returnMsg;
    }

    @Override
    public WxSendMessage videoMessage(WxVideoMessage arg0) {
        return new WxNullMessage();
    }

    @Override
    public WxSendMessage voiceMessage(WxVoiceMessage arg0) {
        return new WxNullMessage();
    }

}
