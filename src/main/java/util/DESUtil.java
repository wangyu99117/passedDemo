package util;


import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES算法实现
 * @author xzb
 *
 */
public class DESUtil {
	/**
     * 申城秘钥
	 * @param keyStr
     * @return
     * @throws Exception
	 */
	private static SecretKey keyGenerator(String keyStr) throws Exception {
		byte input[] = BytesHexConver.hexStringToBytes(keyStr);
		DESKeySpec desKey = new DESKeySpec(input);
		//创建一个密匙工厂，然后用它把DESKeySpec转换成
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(desKey);
		return securekey;
	}
	/**
	 * 生成密钥
	 * @throws Exception 
	 */
	public static byte[] initKey(String keyStr) throws Exception{
//		//密钥生成器
//		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
//		//初始化密钥生成器
//		keyGen.init(56);
//		//生成密钥
//		SecretKey  secretKey = keyGen.generateKey();
		byte input[] = BytesHexConver.hexStringToBytes(keyStr);
		DESKeySpec desKey = new DESKeySpec(input);
		//创建一个密匙工厂，然后用它把DESKeySpec转换成
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKey);
		return secretKey.getEncoded();
	}

	/**
	 * 加密方法2
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptDES2(String data,String key) throws Exception {
		if(StringUtils.isEmpty(data)||StringUtils.isEmpty(key)){
			return null;
		}

		byte[] dataByte = data.getBytes();
		byte[] keyByte = initKey(key);

		//获得密钥
		SecretKey secretKey = new SecretKeySpec(keyByte, "DES");
		//Cipher完成加密
		Cipher cipher = Cipher.getInstance("DES");
		//初始化cipher
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		//加密
		byte[] encrypt = cipher.doFinal(dataByte);

		return BytesHexConver.bytesToHexString(encrypt);
	}


	/**
	 * 加密
	 * @throws Exception 
	 */
	public static byte[] encryptDES(byte[] data, byte[] key) throws Exception{
		//获得密钥
		SecretKey secretKey = new SecretKeySpec(key, "DES");
		//Cipher完成加密
		Cipher cipher = Cipher.getInstance("DES");
		//初始化cipher
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		//加密
		byte[] encrypt = cipher.doFinal(data);
		
		return encrypt;
	}

	/**
	 * 解密2
	 * @param data
	 * @param key
	 * @return
	 */
	public static String decryptDES2(String data,String key) throws Exception {
		if(StringUtils.isEmpty(data)||StringUtils.isEmpty(key)){
			return null;
		}

		byte[] dataByte = BytesHexConver.hexStringToBytes(data);
		byte[] keyByte = initKey(key);

		//恢复密钥
		SecretKey secretKey = new SecretKeySpec(keyByte, "DES");
		//Cipher完成解密
		Cipher cipher = Cipher.getInstance("DES");
		//初始化cipher
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		//解密
		byte[] plain = cipher.doFinal(dataByte);

		return new String(plain);
	}
	/**
	 * 解密
	 */
	public static byte[] decryptDES(byte[] data, byte[] key) throws Exception{
		//恢复密钥
		SecretKey secretKey = new SecretKeySpec(key, "DES");
		//Cipher完成解密
		Cipher cipher = Cipher.getInstance("DES");
		//初始化cipher
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		//解密
		byte[] plain = cipher.doFinal(data);
		
		return plain;
	}

}
