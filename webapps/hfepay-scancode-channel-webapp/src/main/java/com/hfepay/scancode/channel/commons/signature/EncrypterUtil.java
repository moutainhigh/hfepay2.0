package com.hfepay.scancode.channel.commons.signature;

import org.apache.commons.codec.binary.Base64;

import com.hfepay.scancode.api.entity.vo.Message;


public class EncrypterUtil {
	
	
	
	public static Message encode(final String message) throws Exception {
		String aesKey = Encrypter.getRandomString(16);
		Encrypter encrypter = new Encrypter(HfepayConfig.publickey, HfepayConfig.privatekey);
		String encryptData = new String(Base64.encodeBase64((encrypter.AESEncrypt(message, aesKey))), HfepayConfig.input_charset);
		String signData = new String(Base64.encodeBase64(encrypter.digitalSign(message.getBytes(HfepayConfig.input_charset))), HfepayConfig.input_charset);
		String encrtptKey = new String(Base64.encodeBase64(encrypter.RSAEncrypt(aesKey)), HfepayConfig.input_charset);
		return new Message(encryptData, signData, encrtptKey);
	}

	public static String decode(final String content, final String sign, final String key) throws Exception {
		Encrypter encrypter = new Encrypter(HfepayConfig.publickey, HfepayConfig.privatekey);
		byte[] decodeBase64KeyBytes = Base64.decodeBase64(key.getBytes(HfepayConfig.input_charset));
		byte[] merchantAESKeyBytes = encrypter.RSADecrypt(decodeBase64KeyBytes);
		// 使用base64解码商户请求报文
		byte[] decodeBase64DataBytes = Base64.decodeBase64(content.getBytes(HfepayConfig.input_charset));
		byte[] realText = encrypter.AESDecrypt(decodeBase64DataBytes, merchantAESKeyBytes);
		byte[] signBytes = Base64.decodeBase64(sign.getBytes(HfepayConfig.input_charset));
		if (!encrypter.verifyDigitalSign(realText, signBytes)) {
//			throw new ValidatException(AdaptiveErrorCode.VALIDAT_100001.getCode(), AdaptiveErrorCode.VALIDAT_100001.getDesc());
		}
		
		return new String(realText, HfepayConfig.input_charset);
	}
	
	
}
