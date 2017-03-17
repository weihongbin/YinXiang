package com.example.administrator.myyinxiang.dataServer;

/**
 * Created by Administrator on 2017/2/3.
 */
public class ServerVariable {
    /**
     * 远程服务service
     **/
    public static final class Service {
        // 用户服务
        public static final String USER_MANAGER = "appUserService";
        //添加好友服务
        public static final String FRIEND_MANAGER = "appFriendService";
        //分组服务
        public static final String GROUP_MANAGER = "appGroupService";
        // 评论服务
        public static final String COMMENT_MANAGER = "appCommentService";
        // 收藏服务
        public static final String ATTENTION_MANAGER = "appAttentionService";
        // 设置服务
        public static final String SETTING_MANAGER = "appSettingService";

    }
    /**
     * 远程用户方法服务方法
     */
    public static final class UserMethod {
        // 注册用户方法
        public static final String registerUser = "";
        // 注册用户方法
        public static final String getLogout = "";
        // 获取用户登录方法
        public static final String userLogin = "";
        // 找回用户密码方法
        public static final String resetUserPassword = "";
        // 修改用户密码方法
        public static final String reviseUserPassword = "";
        // 用户登录(更换手机)
        public static final String replaceDeciceLogin = "";
        // 修改用户信息
        public static final String updateUserInfo = "";
        // 查询用户信息
        public static final String getuserinfo = "";
        // 获取手机验证码方法
        public static final String getIdentifyCode = "";

    }
    /**
     * 添加好友服务方法
     */
    public static final class FriendsMethod {
        //通过输入的信息找用户
        public static final String getUserByInfo = "getUserByInfo";
        //通过信息查自己的好友
        public static final String getFriendByInfo = "getFriendByInfo";
        //通过组的id查找好友
        public static final String getFriendByGroupId = "getFriendByGroupId";
        //通过id删除用户
        public static final String deleteFriendByUserId = "deleteFriendByUserId";
        //通过用户id和组的id添加好友
        public static final String addFriendByUserIdAndGroupId = "addFriendByUserIdAndGroupId";
        //通过组的id更改好友的信息
        public static final String updateFriendByGroupIdAndFriendName = "updateFriendByGroupIdAndFriendName";
    }

    public static final class GroupMethod {
        //通过用户id找组
        public static final String getGroupByUserId = "getGroupByUserId";
        //通过组名和用户id添加组
        public static final String addGroupByGroupNameAndUserId = "addGroupByGroupNameAndUserId";
        //通过组id和组名修改组
        public static final String updateGroupByGroupIdAndGroupName = "updateGroupByGroupIdAndGroupName";
        //通过组号删除组
        public static final String deleteGroupByGroupId = "deleteGroupByGroupId";


    }
    /**
     * 评论方法
     */
    public static final class CommentMethod {
        // 新增评论
        public static final String insertComment = "f1200201011";
        // 查询评论
        public static final String searchComment = "f1200201012";
        // 查询订单评论
        public static final String searchOrderComment = "f1200201032";
        // 根据条件查询标签
        public static final String searchTag = "f1200101066";
        // 根据条件查询标签(停车场)
        public static final String searchTagForParking = "f1200101068";
    }
    /**
     * 收藏方法
     */
    public static final class AttentionMethod {
        // 收藏停车场
        public static final String attentionPark = "f1200201026";
        // 取消收藏停车场
        public static final String unAttentionPark = "f1200201027";
        // 获取收藏信息
        public static final String getAttentionInfo = "f1200201028";
    }

    /**
     * 设置方法
     */
    public static final class SettingMethod {
        // 反馈信息
        public static final String insertFeedbBack = "f1200101050";
        // 获取版本信息
        public static final String getAppNewVersion = "f0201020002";
        // 获取历史消息
        public static final String getPushMessage = "f1000100203";
        // 获取版本信息
        public static final String getProblems = "f1200101061";
    }

}
