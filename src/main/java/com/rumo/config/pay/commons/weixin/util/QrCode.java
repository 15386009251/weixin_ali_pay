package com.rumo.config.pay.commons.weixin.util;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;


/**
 * 微信支付二维码生成 
 * QrCode<BR>
 * 创建人:小威 <BR>
 * 时间：2015年10月16日-下午4:02:58 <BR>
 * @version 1.0.0
 *
 */
public class QrCode {
       
	// 定义二维码颜色值(黑色)
	private static final int BLACK = 0xFF000000;
	// 定义二维码的背景颜色值（白色）
	private static final int WHITE = 0xFFFFFFFF;
	
    /**
    * 生成二维码的工具类
    * (这里用一句话描述这个方法的作用)<BR>
    * 方法名：QrcodeImage<BR>
    * 创建人：小威 <BR>
    * 时间：2015年10月16日-下午5:09:10 <BR>
    * @param width
    * @param height
    * @param contents
    * @return BufferedImage<BR>
    * @exception <BR>
    * @since  1.0.0
    */
	public static BufferedImage QrcodeImage(int width,int height,String contents){
		try {
				Hashtable<EncodeHintType, Object> ht = new Hashtable<EncodeHintType, Object>();
				// 设置编码，防止中文乱码
				ht.put(EncodeHintType.CHARACTER_SET, "utf-8");
				// 设置二维码的参数(编码内容，编码类型，生成图片宽度，高度)
				BitMatrix bitMatrix  = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height,ht);
				// 获取宽度和高度
				int b_width = bitMatrix.getWidth();
				int b_height = bitMatrix.getHeight();
				// 建立图像缓冲器
				BufferedImage image = new BufferedImage(b_width, b_height, BufferedImage.TYPE_INT_BGR);
				// 生成二维码
				for(int x = 0; x < b_width; x++){ // 横坐标
					for(int y = 0; y < b_height; y++){
						image.setRGB(x, y,bitMatrix.get(x, y) ? BLACK : WHITE);
					}
				}
				return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
}
