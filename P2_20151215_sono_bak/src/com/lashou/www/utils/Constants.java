package com.lashou.www.utils;

public class Constants {
  public static final class URL{
 	  private static final String HOST="http://192.168.56.1:8080/lashou_server/";
// 		public static final String HOST = "http://7xos3n.com1.z0.glb.clouddn.com/";
	  public static final String URL_CAINIXIHUAN=HOST+"cainixihuan.txt";
	  public static final String URL_HEADER_BANNER=HOST+"header_banner.txt";
	  public static final String URL_HEADER_TYPE=HOST+"header_type.txt";
	  public static final String URL_HOTFILM=HOST+"hotfilm.txt";
		public static final String CITIES = HOST + "cities.txt";
		public static final String DETAIL1 = HOST + "detail1.txt";
		
		
		public static final String URL_WEIBOUSER = "https://api.weibo.com/2/users/show.json?";
  }
  
  public static final class KEY{
		public static final String CITYNAME = "cityname";
		// 填写从短信SDK应用后台注册得到的APPKEY
		public static final String APPKEY = "d0d6bae627a6";
		// 填写从短信SDK应用后台注册得到的APPSECRET
		public static final String APPSECRET = "14afa026417158446eb76f45c5f04b04";
		
	}
  
   public static final class FILENAME{
	  public static final String SETTINGS="settings";
	  public static final String ONLYWIFI="OnlyWifi";
	  public static final String PUSHMSG="PushMsg";
  } 
   
   public static final class WEIBO{
	   /** 当前 DEMO 应用的 APP_KEY，第三方应用应该使用自己的 APP_KEY 替换该 APP_KEY */
	    public static final String APP_KEY      = "2517591814";
	    
	    /** 
	     * 当前 DEMO 应用的回调页，第三方应用可以使用自己的回调页。
	     * 
	     * <p>
	     * 注：关于授权回调页对移动客户端应用来说对用户是不可见的，所以定义为何种形式都将不影响，
	     * 但是没有定义将无法使用 SDK 认证登录。
	     * 建议使用默认回调页：https://api.weibo.com/oauth2/default.html
	     * </p>
	     */
	    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";

	    /**
	     * Scope 是 OAuth2.0 授权机制中 authorize 接口的一个参数。通过 Scope，平台将开放更多的微博
	     * 核心功能给开发者，同时也加强用户隐私保护，提升了用户体验，用户在新 OAuth2.0 授权页中有权利
	     * 选择赋予应用的功能。
	     * 
	     * 我们通过新浪微博开放平台-->管理中心-->我的应用-->接口管理处，能看到我们目前已有哪些接口的
	     * 使用权限，高级权限需要进行申请。
	     * 
	     * 目前 Scope 支持传入多个 Scope 权限，用逗号分隔。
	     * 
	     * 有关哪些 OpenAPI 需要权限申请，请查看：http://open.weibo.com/wiki/%E5%BE%AE%E5%8D%9AAPI
	     * 关于 Scope 概念及注意事项，请查看：http://open.weibo.com/wiki/Scope
	     */
	    public static final String SCOPE = 
	            "email,direct_messages_read,direct_messages_write,"
	            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
	            + "follow_app_official_microblog," + "invitation_write";
	  } 
}
