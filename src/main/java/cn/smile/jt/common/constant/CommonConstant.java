package cn.smile.jt.common.constant;

/**
 * <p>
 * 通用常量
 * </p>
 *
 * @author longjuntao
 * @since 2020/10/30 15:54
 */
@SuppressWarnings("unused")
public class CommonConstant {

    /**
     * 微信接口produces属性
     */
    public static final String PRODUCES_STRING_UTF8 = "application/xml;charset=UTF-8";
    public static final String PRODUCES_STRING_OCTET_STREAM = "application/octet-stream";

    /**
     * 异常时返回给微信的字符串
     */
    public static final String NULL_STRING = "";

    /**
     * 告诉微信成功,暂时不处理
     */
    public static final String SUCCESS_STRING = "success";

    /**
     * ArrayList类路径
     */
    public static final String ARRAY_LIST_STRING = "java.util.ArrayList";

    /**
     * xml开始标签
     */
    public static final String XML_START = "<xml>";

    /**
     * xml结束标签
     */
    public static final String XML_END = "</xml>";

    /**
     * 左尖括号
     */
    public static final String LEFT_BRACKET = "<";

    /**
     * 左尖括号加斜杠
     */
    public static final String LEFT_BRACKET_AND_SLASH = "</";

    /**
     * 右尖括号
     */
    public static final String RIGHT_BRACKET = ">";

    /**
     * CDATA标签左
     */
    public static final String CDATA_LEFT = "><![CDATA[";

    /**
     * CDATA标签右
     */
    public static final String CDATA_RIGHT = "]]></";

    /**
     * 微信接口token
     */
    public static final String WE_CHAT_TOKEN = "LongJunTao";

    /**
     * SHA-1加密
     */
    public static final String SHA_1 = "SHA-1";

    /**
     * defaultMessage
     * 返回暂时不支持的消息类型
     */
    public static final String DEFAULT_MESSAGE_STRING = "抱歉,暂时不支持该消息类型,我们会尽快完善,感谢您的支持!";
    public static final String DEFAULT_EVENT_MESSAGE_STRING = "感谢您对我们的关注,目前我们正处于开发阶段,很多功能尚未完善,开发完成会第一时间通知您!";

    public static final String SWITCH_TEXT = "回复方式已经切换为文本";
    public static final String SWITCH_VOICE = "回复方式已经切换为语音";
    public static final String SWITCH_CONTAIN = "回复方式已经切换为";

    /**
     * 微信的AppID
     */
    public static final String APP_ID = "wxba0bd24757980434";

    /**
     * 微信的AppSecret密码
     */
    public static final String APP_SECRET = "bf1ff4a393fc240e5256f446ded468af";

    /**
     * 获取微信 AccessToken 的url地址
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    /**
     * 微信客服接口-发消息 POST
     */
    public static final String REPLY_WE_CHAT_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?";

    /**
     * 微信素材上传接口
     */
    public static final String UPLOAD_WE_CHAT_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?";
    public static final String UPLOAD_WE_CHAT_MEDIA_PERMANENT_URL = "https://api.weixin.qq.com/cgi-bin/material/add_material?";

    /**
     * 创建微信自定义菜单 POST
     */
    public static final String CREATE_WE_CHAT_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?";

    /**
     * 查询微信自定义菜单 GET
     */
    public static final String SELECT_WE_CHAT_MENU_URL = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?";

    /**
     * 删除微信自定义菜单 GET
     */
    public static final String DELETE_WE_CHAT_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?";

    /**
     * 获取微信Token返回的 AccessToken 的Key
     */
    public static final String ACCESS_TOKEN_KEY = "access_token";

    /**
     * 微信上传素材类型Key
     */
    public static final String TYPE_KEY = "type";

    /**
     * 获取微信Token返回的 Token 有效时间Key
     */
    public static final String EXPIRES_IN_KEY = "expires_in";

    /**
     * 获取微信Token返回的错误码Key
     */
    public static final String ERR_CODE_KEY = "errcode";

    /**
     * 获取微信Token返回的错误信息Key
     */
    public static final String ERR_MSG_KEY = "errmsg";

    /**
     * Get请求参数链接符号
     */
    public static final String GET_URL_AND = "&";

    /**
     * appId参数的Key
     */
    public static final String APP_ID_KEY = "appid";

    /**
     * appSecret的Key
     */
    public static final String APP_SECRET_KEY = "secret";

    /**
     * 等于符号
     */
    public static final String EQUAL = "=";

    /**
     * 请求失败字符串
     */
    public static final String FAIL_MSG = "处理失败";

    /**
     * 返回对象处理失败代码
     */
    public static final String FAIL_CODE = "2001";

    /**
     * 存入Redis中微信Token的Key
     */
    public static final String WE_CHAT_TOKEN_KEY = "weChatToken";

    /**
     * 微信错误编码 -1 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_MINUS_ONE = "系统繁忙，此时请开发者稍候再试";

    /**
     * 微信错误编码 0 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_0 = "请求成功";

    /**
     * 微信错误编码 40001 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_40001 = "AppSecret错误或者AppSecret不属于这个公众号，请开发者确认AppSecret的正确性";

    /**
     * 微信错误编码 40002 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_40002 = "请确保grant_type字段值为client_credential";

    /**
     * 微信错误编码 40164 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_40164 = "调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置";

    /**
     * 微信错误编码 89503 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_89503 = "此IP调用需要管理员确认,请联系管理员";

    /**
     * 微信错误编码 89501 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_89501 = "此IP正在等待管理员确认,请联系管理员";

    /**
     * 微信错误编码 89506 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_89506 = "24小时内该IP被管理员拒绝调用两次，24小时内不可再使用该IP调用";

    /**
     * 微信错误编码 89507 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_89507 = "1小时内该IP被管理员拒绝调用一次，1小时内不可再使用该IP调用";

    /**
     * 微信其他错误编码对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_OTHER = "其他原因";

    /**
     * Http请求成功码
     */
    public static final int HTTP_REQUEST_SUCCESS_CODE = 200;

    /**
     * 请求获取微信ACCESS_TOKEN
     */
    public static final String TASK_GET_WE_CHAT_TOKEN_URL = "http://laoshengle.cn/api/weChat/weChatService/getAccessToken";

    /**
     * 文件解析失败返回字符串
     */
    public static final String FILE_PARSING_FAILED_TEXT = "文件解析失败!";

    /**
     * 文件解析成功返回字符串
     */
    public static final String FILE_PARSED_SUCCESS_FULLY_TEXT = "文件解析失败!";

    /**
     * 文件上传成功返回字符串
     */
    public static final String FILE_UPLOAD_SUCCESS_TEXT = "文件上传成功!";

    /**
     * 默认开始页数
     */
    public static final Integer PAGE_INDEX = 1;

    /**
     * 默认每页数量
     */
    public static final Integer PAGE_SIZE = 10;

    /**
     * 每日精选最迟上传时间
     */
    public static final String LAST_UPLOAD_TIME = "10:30";

    /**
     * 返回对象处理成功
     */
    public static final String RESULT_SUCCESS_TEXT = "处理成功";

    /**
     * 返回对象处理成功代码
     */
    public static final String RESULT_SUCCESS_CODE = "2000";

    /**
     * String的减号
     */
    public static final String SYMBOL_MINUS = "-";

    /**
     * 用户状态-启用
     */
    public static final int USER_STATUS_ENABLE = 1;

    /**
     * RabbitMQ消息队列名称
     */
    public static final String QUEUE_NAME = "myQueue";

    /**
     * 新建用户初始密码
     */
    public static final String INITIAL_PASSWORD = "123456";

    /**
     * 返回前端通用数据Key
     */
    public static final String RESULT_DATA = "data";

    /**
     * 数字0
     */
    public static final int NUMBER_ZERO = 0;
    /**
     * 数字1
     */
    public static final int NUMBER_ONE = 1;
    /**
     * 数字2
     */
    public static final int NUMBER_TWO = 2;
    /**
     * 数字7
     */
    public static final int NUMBER_SEVEN = 7;
    /**
     * 数字58
     */
    public static final int NUMBER_FIFTY_EIGHT = 58;
    /**
     * 数字100
     */
    public static final int NUMBER_HUNDRED = 100;
    /**
     * 数字100
     */
    public static final int NUMBER_THOUSAND = 1000;
    /**
     * 文本转语音最大长度
     */
    public static final int VOICE_TEXT_MAX_LENGTH = 44;

    /**
     * 微信公众号取消关注
     */
    public static final String UNSUBSCRIBE = "unsubscribe";

    /**
     * 微信公众号关注
     */
    public static final String SUBSCRIBE = "subscribe";

    /**
     * 微信公众号点击事件
     */
    public static final String CLICK = "CLICK";

    /**
     * NowAPI接口URL地址(HTTP)
     */
    public static final String NOW_API_URL_HTTP = "http://api.k780.com";

    /**
     * NowAPI接口URL地址(HTTPS)
     */
    public static final String NOW_API_URL_HTTPS = "https://sapi.k780.com";

    /**
     * NowApi接口的 appKey
     */
    public static final String NOW_API_APP_KEY = "appkey";
    public static final Integer NOW_API_APP_KEY_VALUE = 23954;

    /**
     * NowApi接口的 sign
     */
    public static final String NOW_API_SIGN = "sign";
    public static final String NOW_API_SIGN_VALUE = "054d333be1642c236cbf1bd2be1e6984";

    /**
     * NowApi处理成功返回Code
     */
    public static final String NOW_API_SUCCESS_CODE = "1";

    /**
     * 服务器域名
     */
    public static final String SERVER_URL = "https://laoshengle.cn/";

    /**
     * 腾讯闲聊机器人AppId
     */
    public static final String CHAT_BOT_APP_ID = "app_id";
    public static final String CHAT_BOT_APP_ID_VALUE = "2160060608";

    /**
     * 腾讯闲聊机器人AppKey
     */
    public static final String CHAT_BOT_APP_KEY = "app_key";
    public static final String CHAT_BOT_APP_KEY_VALUE = "ciiUlOBkJRGTjSjX";

    /**
     * 微信闲聊API地址
     */
    public static final String CHAT_BOT_APP_URL = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_textchat";
    /**
     * 腾讯语音合成URL
     */
    public static final String TEN_CENT_VOICE_COMPOUND_APP_URL = "https://api.ai.qq.com/fcgi-bin/aai/aai_tts";

    /**
     * 腾讯API接口请求Key
     */
    public static final String URL_ENCODE = "UTF-8";
    public static final String MAP_KEY_SIGN = "sign";
    public static final String MAP_KEY_TIME_STAMP = "time_stamp";
    public static final String MAP_KEY_NONCE_STR = "nonce_str";
    public static final String MAP_KEY_SESSION = "session";
    public static final String MAP_KEY_QUESTION = "question";
    public static final String MAP_KEY_SPEAKER = "speaker";
    public static final String MAP_KEY_FORMAT = "format";
    public static final String MAP_KEY_VOLUME = "volume";
    public static final String MAP_KEY_SPEED = "speed";
    public static final String MAP_KEY_TEXT = "text";
    public static final String MAP_KEY_VOICE = "voice";
    public static final String MAP_KEY_AHT = "aht";
    public static final String MAP_KEY_APC = "apc";

    /**
     * 腾讯API接口返回对象的Key
     */
    public static final String RESULT_OBJECT_KEY_RET = "ret";
    public static final String RESULT_OBJECT_KEY_DATA = "data";

    /**
     * 微信素材上传参数Key
     */
    public static final String UPLOAD_PARAM_FILE_NAME_KEY = "filename";
    public static final String UPLOAD_PARAM_FILE_LENGTH_KEY = "filelength";
    public static final String UPLOAD_PARAM_CONTENT_TYPE_KEY = "content-type";
    public static final String UPLOAD_PARAM_FILE_KEY = "file";
}
