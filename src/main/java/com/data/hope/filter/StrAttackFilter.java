package com.data.hope.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 文件上传字符串过滤特殊字符
 * @author: gqh
 */
public class StrAttackFilter {

    public static String filter(String str) throws PatternSyntaxException {
        // 清除掉所有特殊字符,可以追加
        String regEx = "[`_《》~!@#$%^&*()+=|{}':;',\\[\\].<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

//    public static void main(String[] args) {
//        String filter = filter("@#jeecg/《》【bo】￥%……&*（o）)))！@t<>,.,/?'\'~~`");
//        System.out.println(filter);
//    }
}
