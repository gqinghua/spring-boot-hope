

package com.data.hope.core.base.consts;


import java.util.regex.Pattern;

public class Const {

    public static final byte VIZ_PUBLISH = 2;

    public static final byte DATA_STATUS_ACTIVE = 1;

    public static final byte DATA_STATUS_ARCHIVED = 0;

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_DATE = "yyyy-MM-dd";


    public static final String DEFAULT_DATE_FORMAT_TODATA = "yyyy-MM-dd HH:mm:ss";

    public static final int MINIMUM_SYNC_INTERVAL =  20;

    /**
     * 正则表达式
     */
    public static final String REG_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    public static final String REG_USER_PASSWORD = ".{6,20}";

    public static final String REG_IMG = "^.+(.JPEG|.jpeg|.JPG|.jpg|.PNG|.png|.GIF|.gif)$";


    /**
     * 脚本变量
     */
    //默认的变量引用符号
    public static final String DEFAULT_VARIABLE_QUOTE = "$";
    //变量匹配符
    public static final Pattern VARIABLE_PATTERN = Pattern.compile("\\$\\S+\\$");
    //变量正则模板
    public static final String VARIABLE_PATTERN_TEMPLATE = "\\$%s\\$";

    /**
     * 权限变量
     */
//    //管理员权限，拥有所有数据的权限
//    public static final String ALL_PERMISSION = "@ALL_PERMISSION@";

    /**
     * Token Key
     */
    public static final String TOKEN = "Authorization";

    public static final String TOKEN_HEADER_PREFIX = "Bearer ";

    /**
     * 权限等级定义
     */
    public static final int DISABLE = 0;

    public static final int ENABLE = 1;

    public static final int READ = 1 << 1;

    public static final int MANAGE = 1 << 2 | READ;

    public static final int GRANT = 1 << 3 | READ;

    public static final int DOWNLOAD = 1 << 5 | READ;

    public static final int SHARE = 1 << 6 | READ;

    public static final int CREATE = 1 << 7 | MANAGE;

    /*
        图片上传格式和大小
     */

    public static String DEFAULT_IMG_FORMAT = ".png";

    public static final int IMAGE_WIDTH = 256;

    public static final int IMAGE_HEIGHT = 256;


    public static final String ENCRYPT_FLAG = "_encrypted_";

}
