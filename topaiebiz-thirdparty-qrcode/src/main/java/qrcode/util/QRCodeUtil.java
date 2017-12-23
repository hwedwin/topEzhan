package qrcode.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Hashtable;

/**
*
* Description: 二维码工具类
*
* Author: hxpeng
* createTime: 2017/11/15
*
* @param:
**/
public class QRCodeUtil {

	private static final String CHARSET = "utf-8";

	// 二维码尺寸
	private static final int DEFAULT_QRCODE_SIZE = 300;
	// LOGO宽度
	private static final int DEFAULT_LOGO_WIDTH = 30;
	// LOGO高度
	private static final int DEFAULT_LOGO_HEIGHT = 30;
	// 图片类型后缀
	private static final String IMAGE_FORMAT_NAME = "JPG";

	/**
	*
	* Description: 创建一个二维码
	*
	* Author: hxpeng
	* createTime: 2017/11/10
	*
	* @param content    二维码包含内容
	* @param QRcodeSize 正方形二维码图片的大小（单位：像素）
	**/
	public static BufferedImage createImage(String content, int QRcodeSize) throws Exception {
		if (QRcodeSize <= 0) QRcodeSize = DEFAULT_QRCODE_SIZE;
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
				BarcodeFormat.QR_CODE, QRcodeSize, QRcodeSize, hints);
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
						: 0xFFFFFFFF);
			}
		}
		return image;
	}

	/**
	*
	* Description: 给已创建的二维码嵌套 LOGO
	*
	* Author: hxpeng
	* createTime: 2017/11/10
	*
	* @param source           二维码图片对象
	* @param QRcodeSize       正方形二维码图片的大小（单位：像素）
	* @param logoInputStream    二维码图片文件对象
	* @param logoNeedCompress logo图片是否压缩
	* @param logoWidth        logo图片宽度
	* @param logoHeight       logo图片高度
	* @throws Exception
	**/
	public static void insertLogoImage(BufferedImage source, int QRcodeSize, InputStream logoInputStream,
	                                   boolean logoNeedCompress, int logoWidth, int logoHeight) throws Exception {
		Image src = ImageIO.read(logoInputStream);
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (logoWidth <= 0) logoWidth = DEFAULT_LOGO_WIDTH;
		if (logoHeight <= 0) logoHeight = DEFAULT_LOGO_HEIGHT;
		if (logoNeedCompress) { // 压缩LOGO
			if (width > logoWidth) {
				width = logoWidth;
			}
			if (height > logoHeight) {
				height = logoHeight;
			}
			Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (QRcodeSize - width) / 2;
		int y = (QRcodeSize - height) / 2;
		graph.drawImage(src, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
	}

	/**
	*
	* Description: 生成二维码并内嵌LOGO
	*
	* Author: hxpeng
	* createTime: 2017/11/10
	*
	* @param content  内容
	* @param logoInputStream  logo流
	* @param QRcodeSize 二维码像素大小
	* @throws Exception
	**/
	public static File encode(String content, InputStream logoInputStream, int QRcodeSize) throws Exception {
		File codeFile = null;
		if (StringUtils.isEmpty(content)) return null;
		try {
			BufferedImage codeImage = QRCodeUtil.createImage(content, QRcodeSize);
			if (null != logoInputStream) {
				QRCodeUtil.insertLogoImage(codeImage, QRcodeSize, logoInputStream, true, DEFAULT_LOGO_WIDTH, DEFAULT_LOGO_HEIGHT);
			}
			codeFile = File.createTempFile("QR" + System.currentTimeMillis(), "." + IMAGE_FORMAT_NAME);
			ImageIO.write(codeImage, IMAGE_FORMAT_NAME, codeFile);
		} catch (Exception e) {
			System.err.println("生成二维码失败！" + e.getMessage());
		}
		return codeFile;
	}

	/**
	*
	* Description: 解析二维码，返回字符结果
	*
	* Author: hxpeng
	* createTime: 2017/11/10
	*
	* @param file 二维码文件对象
	**/
	public static String decode(File file) throws Exception {
		BufferedImage image;
		image = ImageIO.read(file);
		if (image == null) {
			return null;
		}
		BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result;
		Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
		hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
		result = new MultiFormatReader().decode(bitmap, hints);
		return result.getText();
	}

	public static void main(String[] args) {

		//File logoImageFile=new File(QRCodeUtil.class.getResource("/resources/image/gzl_logo.jpg").getFile());
		try {
			ResourceLoader resourceLoader = new DefaultResourceLoader();
			Resource resource = resourceLoader.getResource("/gzl_logo.jpg");
			//resources/image/gzl_logo.jpg
			System.out.println(resource.getFile().getAbsolutePath());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


	}
}

