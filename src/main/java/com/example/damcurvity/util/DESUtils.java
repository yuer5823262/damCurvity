package com.example.damcurvity.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@Slf4j
public class DESUtils {
    /** 算法名称 */
    private static final String KEY_ALGORITHM = "DES";

    /** 算法名称/加密模式/填充方式 */
    private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";

    /** 8位KEY 默认 自定义*/
    private static final String DEFAULT_KEY = "SHXPJCHN";
    /** 8位IV 默认 自定义*/
    private static final String DEFAULT_IV = "JcHn1@34";

    /**
     * 生成密钥(base64字符串)
     */
    public static String initKey() throws NoSuchAlgorithmException, NoSuchProviderException {
        // 实例化密钥生成器
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        String name = kg.getProvider().getName();
        System.out.println(name);
        // 生成密钥
        SecretKey secretKey = kg.generateKey();
        // 获取二进制密钥编码形式
        return Base64.encodeBase64String(secretKey.getEncoded());
    }

    /**
     * 转换密钥
     */
    private static Key toKey(String key) throws Exception {
        // 实例化Des密钥
        DESKeySpec dks = new DESKeySpec(Base64.decodeBase64(key));
        // 实例化密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        // 生成密钥
        return keyFactory.generateSecret(dks);
    }

    /**
     * 加密数据
     *
     * @param data 待加密数据
     * @param key  密钥(8位字符的base64)
     * @param iv   加密向量(8位字符的base64)
     * @return 加密后的数据
     */
    public static byte[] encrypt(String data, String key, String iv) throws Exception {
        // 还原密钥
        Key k = toKey(key);
        // 实例化Cipher对象，它用于完成实际的加密操作
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(Base64.decodeBase64(iv));
        // 初始化Cipher对象，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, k, ivParameterSpec);
        // 执行加密操作。加密后的结果通常都会用Base64编码进行传输
        log.info("加密后字符="+cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        String s= String.valueOf(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        log.info("加密后字符串="+  s);
        return cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
//        return encryptBASE64(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
//        return Base64.encodeBase64String(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    public static byte[] encrypt(String data, String key) throws Exception {
        String iv = Base64.encodeBase64String(DEFAULT_IV.getBytes(StandardCharsets.UTF_8));
        return encrypt(data, key, iv);
    }

    public static byte[] encrypt(String data) throws Exception {
        String key = Base64.encodeBase64String(DEFAULT_KEY.getBytes(StandardCharsets.UTF_8));
        String iv = Base64.encodeBase64String(DEFAULT_IV.getBytes(StandardCharsets.UTF_8));
        return encrypt(data, key, iv);
    }

    /**
     * 解密数据
     *
     * @param
     * @param
     * @param
     * @return 解密后的数据
     */
    public static String decrypt(String data, String key, String iv) throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(Base64.decodeBase64(iv));
        // 初始化Cipher对象，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, k, ivParameterSpec);
        // 执行解密操作
        return new String(cipher.doFinal(Base64.decodeBase64(data)));
    }

    public static String decrypt(String data) throws Exception {
        String key = Base64.encodeBase64String(DEFAULT_KEY.getBytes(StandardCharsets.UTF_8));
        String iv = Base64.encodeBase64String(DEFAULT_IV.getBytes(StandardCharsets.UTF_8));
        return decrypt(data, key, iv);
    }

    public static String decrypt(String data, String key) throws Exception {
        String iv = Base64.encodeBase64String(DEFAULT_IV.getBytes(StandardCharsets.UTF_8));
        return decrypt(data, key, iv);
    }

    // Base64解码
    public static String encryptBASE64(byte[] data) {
        java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
        String encode = encoder.encodeToString(data);
        return encode;
    }

    // Base64编码
    public static byte[] decryptBASE64(String data) throws Exception {
        java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
        byte[] buffer = decoder.decode(data);
        return buffer;
    }

    public static void main(String[] args) throws Exception {
        String source = "{trainSubject: 'test1', trainTime: '2021-08-01',trainAddress:'万达广场',receiveTrainPeople:'张三,李四',promoter:'admin'}";
        System.out.println("原文: " + source);

        String key = initKey();
        System.out.println("密钥: " + key);

        byte[] encryptData = encrypt("admin");

        System.out.println("加密: " + encryptBASE64(encryptData));

        String decryptData = decrypt("Y0pF/dxSY5siD9YHxCG1RJaRyux3gPgbTgIRJt8QBe7j+WCwRIG56OP8Duh7JUAq8EERaBF/40awRYz5/4rkY6Eb3C/Db3VSZbIF6Q9lQ1U=");
        System.out.println("解密: " + decryptData);
    }
}
