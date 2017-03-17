package com.example.administrator.myyinxiang.dataServer;

import android.content.Context;
import com.example.administrator.myyinxiang.BaseApplication;
import com.example.administrator.myyinxiang.dataServer.manager.AttentionManager;
import com.example.administrator.myyinxiang.dataServer.manager.CommentManager;
import com.example.administrator.myyinxiang.dataServer.manager.FriendsManager;
import com.example.administrator.myyinxiang.dataServer.manager.GroupManager;
import com.example.administrator.myyinxiang.dataServer.manager.UserManager;
import com.example.administrator.myyinxiang.model.MessageModel;
import com.example.administrator.myyinxiang.dataServer.manager.SettingManager;
import com.example.administrator.myyinxiang.model.UserModel;
import com.example.administrator.myyinxiang.utils.AppUtils;

/**
 * Created by Administrator on 2017/2/3.
 * 数据交换的统一入口
 */
public class ManagerEngine {
    // 单例实体
    private static ManagerEngine manager;
    // 服务器请求
    private HttpClient client;
    // 实体信息头
    private MessageModel message;
    //用户管理类
    private UserManager userManager;
    //组管理类
    private GroupManager groupManager;
    //好友管理类
    private FriendsManager friendsManager;
    //评论类
    private CommentManager commentManager;
    //收藏类
    private AttentionManager attentionManager;
    //设置类
    private SettingManager settingManager;

    private ManagerEngine() {
    }
    public  static ManagerEngine  getInstance(){
        return SingleInstanceHolder.sIntance;
    }

    //静态内部类
    private static class SingleInstanceHolder{
        private static final  ManagerEngine  sIntance=new ManagerEngine();
    }
    /**
     * 初始化
     */
    public void init(Context context) {
        if (message == null) {
            message = new MessageModel();
            UserModel user = ((BaseApplication) context).getUser();
//            message.setSessionId(user.getSessionId());
            message.setUserId(user.getUserId());
            message.setChannel("android");
            message.setVersionCode(AppUtils.getVersionCode(context) + "");
            message.setVersionName(AppUtils.getVersionName(context));
            message.setPhoneModel(AppUtils.getModel(context));
            message.setImei(AppUtils.getIMEI(context));
            message.setMacAddress(AppUtils.getUniqueCode(context));
            message.setSize("25");
            // 初始化
        }
        if (client == null) {
            client = new HttpClient(context);
        }
        if (userManager == null) {
            userManager = new UserManager(context, ServerVariable.Service.USER_MANAGER);
        }
        if (groupManager==null){
            groupManager = new GroupManager(context,ServerVariable.Service.GROUP_MANAGER);
        }
        if (friendsManager==null){
            friendsManager = new FriendsManager(context,ServerVariable.Service.FRIEND_MANAGER);
        }
        if(commentManager==null){
            commentManager=new CommentManager(context,ServerVariable.Service.COMMENT_MANAGER);
        }
        if(attentionManager==null){
            attentionManager=new AttentionManager(context,ServerVariable.Service.ATTENTION_MANAGER);
        }
        if(settingManager==null){
            settingManager=new SettingManager(context,ServerVariable.Service.SETTING_MANAGER);
        }
    }

    public void logout() {
        if (message != null) {
            message.setUserId("");
        }
    }

    public void login(UserModel user) {
        if (message != null) {
            message.setUserId(user.getUserId());
        }
    }

    public MessageModel getMessage() {
        return message;
    }

    public HttpClient getOkHttpClient() {
        return client;
    }

    /**
     * 获取用户管理类
     *
     * @return
     */
    public UserManager getUserManager() {
        return userManager;
    }

    /**
     * 获取组的管理类
     * @return
     */
    public GroupManager getGroupManager(){
        return groupManager;
    }

    /**
     * 获取好友的管理类
     * @return
     */
    public FriendsManager getFriendsManager(){
        return friendsManager;
    }

    /**
     * 获取评论的管理类
     * @return
     */
    public CommentManager getCommentManager(){
        return commentManager;
    }
    /**
     * 获取收藏的管理类
     * @return
     */
    public AttentionManager getAttentionManager(){
        return attentionManager;
    }
    /**
     * 获取评论的管理类
     * @return
     */
    public SettingManager getSettingManager(){
        return settingManager;
    }
 }
