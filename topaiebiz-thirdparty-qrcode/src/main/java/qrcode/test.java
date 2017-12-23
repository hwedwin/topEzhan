package qrcode;

import qrcode.handler.QRCodeHandler;

/**
 * Description TODO
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/10 15:38
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class test {

	public static void main(String[] args) {

		String result = "aaaaaaaaaaaaaaaaaaaaaa";

		System.out.println(QRCodeHandler.generateQRCodeImageUrl(result,300, true));

	}


}
