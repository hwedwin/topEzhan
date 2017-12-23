package qrcode.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import qrcode.util.QRCodeUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * Description： 二维码处理类
 *
 *
 * Author: hxpeng
 * createTime: 2017/11/10
 *
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 *
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class QRCodeHandler {

	private static final Logger log = LoggerFactory.getLogger(QRCodeHandler.class);

	// 二维码后缀
	private static final String IMAGE_FORMAT_NAME = "JPG";

	// 二维码前缀
	private static final String QRCODE_PIC_TMP_PREFIX = "QRcode_";

	// LOGO地址
	private static final String BEINGMATE_LOGO_PATH = "beingmate_logo.jpg";

	// LOGO宽度
	private static final int DEFAULT_LOGO_WIDTH = 30;

	// LOGO高度
	private static final int DEFAULT_LOGO_HEIGHT = 30;

	/**
	*
	* Description: 生成二维码图片路径
	*
	* Author: hxpeng
	* createTime: 2017/11/10
	*
	* @param content    二维码正文
	* @param qrCodeSize    二维码像素
	* @param decorateLogoFalg   是否装饰LOGO
	**/
	public static String generateQRCodeImageUrl(String content, int qrCodeSize, boolean decorateLogoFalg) {
		String qRCodeImageUrl = "";
		if (StringUtils.isEmpty(content)) return qRCodeImageUrl;
		try {
			BufferedImage codeImage = QRCodeUtil.createImage(content, qrCodeSize);
			if (decorateLogoFalg) {
				// 动态获取文件路劲并实例化文件流

				InputStream inputStream = new FileInputStream("C:\\Users\\hxpeng\\Desktop\\beingmate_logo.jpg");

//				File file = new File("C:\\Users\\hxpeng\\Desktop\\beingmate_logo.jpg");

//				InputStream logoFileInputStream =
//						new Object() {
//							public Class getClazz() {
//								return this.getClass();
//							}
//						}.getClass().getResourceAsStream(BEINGMATE_LOGO_PATH);
				QRCodeUtil.insertLogoImage(codeImage, qrCodeSize, inputStream, true, DEFAULT_LOGO_WIDTH, DEFAULT_LOGO_HEIGHT);
			}
			File codeFile = File.createTempFile(
					QRCODE_PIC_TMP_PREFIX + System.currentTimeMillis(), "." + IMAGE_FORMAT_NAME);
			ImageIO.write(codeImage, IMAGE_FORMAT_NAME, codeFile);
			return codeFile.getAbsolutePath();
		} catch (Exception e) {
			/**
			 * TODO 抛一个生成二维码图片异常
			 */
			log.info("生成二维码图片异常：" + e.getMessage());
			e.printStackTrace();
		}
		return qRCodeImageUrl;

	}

	/**
	*
	* Description: 解析一个二维码
	*
	* Author: hxpeng
	* createTime: 2017/11/15
	*
	* @param:
	**/
	public String deCodeQRCodeByImageUrl(String imageUrl) {
		String result = "";
		try {
			File imageFile = this.downFileFromUrl(imageUrl);
			//File imageFile=new File("C:/Users/IBM_AD~1/AppData/Local/Temp/QRcode_14520746654441924291136347432968.JPG");
			if (null != imageFile) {
				result = QRCodeUtil.decode(imageFile);
			}
		} catch (Exception e) {
			/**
			 * TODO 抛一个解析二维码失败
			 */
			log.error("解析二维码失败：" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	private File downFileFromUrl(String fileUrl) {
		File imageFile = null;
		try {
			imageFile = File.createTempFile(
					QRCODE_PIC_TMP_PREFIX + System.currentTimeMillis() + ".",
					IMAGE_FORMAT_NAME);
			OutputStream oputstream = new FileOutputStream(imageFile);
			URL url = new URL(fileUrl);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setDoInput(true);//设置是否要从 URL 连接读取数据,默认为true
			uc.connect();
			InputStream iputstream = uc.getInputStream();
			byte[] buffer = new byte[4 * 1024];
			int byteRead = -1;
			while ((byteRead = (iputstream.read(buffer))) != -1) {
				oputstream.write(buffer, 0, byteRead);
			}
			oputstream.flush();
			iputstream.close();
			oputstream.close();
		} catch (Exception e) {
			/**
			 * TODO 抛一个获取二维码失败
			 */
			log.error("获取网络二维码图片失败：" + e.getMessage());
			e.printStackTrace();
		}
		return imageFile;
	}


}
