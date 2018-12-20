package com.rumo.config.pay.commons.weixin.util;

import java.util.Random;

/**
 * 随机生成字符串
 * RandomStringGenerator<BR>
 * 创建人:小威 <BR>
 * 时间：2015年10月16日-下午1:52:52 <BR>
 * @version 1.0.0
 *
 */
public class RandomStringGenerator {
    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
