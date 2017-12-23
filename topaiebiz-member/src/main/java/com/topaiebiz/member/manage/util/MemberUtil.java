package com.topaiebiz.member.manage.util;

/**
 * Created by Luoqy on 2017/12/22.
 * desc:
 */
public class MemberUtil {

    /**
     * 获取随机的用户名
     * @param len
     * @return
     */
    public static String getUsername(int len) {
        // 字符源，可以根据需要删减
        String generateSource = "0123456789abcdefghigklmnopqrstuvwxyz_";
        String rtnStr = "";
        for (int i = 0; i < len; i++) {
            // 循环随机获得当次字符，并移走选出的字符
            String nowStr = String
                    .valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
            rtnStr += nowStr;
            generateSource = generateSource.replaceAll(nowStr, "");
        }
        return rtnStr;
    }

}
