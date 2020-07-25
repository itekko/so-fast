package com.sofast;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;

public class SignTest {

    public static final String PRIVATE_KEY_BASE64 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIYK4cfreH9skZ4qxmEhOQXyXz+jlO+Q8gzPjn1JMBUTOtmTHnXZlDWTeazaV3nEjzcf8cTHVlJiUcMF9UMfWLILpe+kLK2vRUtM6zch9Ciam3q4kMIFvPzJRwF76rbwj0gpJ8PbgrDMFoyCDJrcVh+IRMBmy9gMyJMax+qiGj2NAgMBAAECgYA/WVM9hbasGiZz5ulcrDPj8Jk9XVUdJ5aNoWZJJda4jx8IBvSlY9AcxfcCTyp6XQAGYG2Fv2O3icn0bpNsRptG0pCqeToqtHPt68nIwrzn0vs225dVZYUMvmxK7qZPBjRGsAu5AUGPUzf5tCVFItR27xmymKJbtcKvFcAVRLUabQJBAMeB6PxiJ5JUSJSd0UJr8miN4z7avwv8mCihQ5iuYX3Ngyuk13MSZ0WNbSHuYCFDzVzFMTw2ZmrO3Y8rNvMDP88CQQCr/3wlpBdJiziHdQJBguJcpMf+f8ZqzvIuywo54OGfdhO9Mi4AUx9f4GNKRfER5KhH8S0SSuNra+MEanLtZgfjAkBG2PpMzepxi16u8BBEvyK5QFHHm1SmxA+gtgvUJ3DVhOqk0Af2hD2KNHvhxc37YiGGZTxjvonGOinx0o3T5H5/AkAT6AjVTm2HjH5pZwhwzpYmRMgE04MCX5s2ATfYxQc37nGQZxtd3ED4Vr8HHJsvB/jWArrFsDcr+puWqj9M7I8jAkEAhnb6aOwjUU8pd9+DmQvgjE3sfFqnxyCZ6wT1b/SrkfDePiDnTKRdpBDIAwXQtHUlT8q2Vxkqrb2gPneUdNoJXg==";

    public static final String PUBLIC_KEY_BASE64 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCGCuHH63h/bJGeKsZhITkF8l8/o5TvkPIMz459STAVEzrZkx512ZQ1k3ms2ld5xI83H/HEx1ZSYlHDBfVDH1iyC6XvpCytr0VLTOs3IfQompt6uJDCBbz8yUcBe+q28I9IKSfD24KwzBaMggya3FYfiETAZsvYDMiTGsfqoho9jQIDAQAB";


    @Test
    public void test01(){
        byte[] data = "我是一段测试字符串".getBytes();
        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA,PRIVATE_KEY_BASE64,PUBLIC_KEY_BASE64);

        //签名
        byte[] signed = sign.sign(data);
        System.out.println(new String(signed));
        //验证签名
        boolean verify = sign.verify(data, signed);
        System.out.println(verify);
    }

    @Test
    public void test02(){
        RSA rsa = new RSA();

        //获得私钥
        //rsa.getPrivateKey().
        String privateKeyBase64 = rsa.getPrivateKeyBase64();
        System.out.println(privateKeyBase64);
        //获得公钥
        //rsa.getPublicKey()
        String publicKeyBase64 = rsa.getPublicKeyBase64();
        System.out.println(publicKeyBase64);

        //公钥加密，私钥解密
        byte[] encrypt = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        System.out.println(new String(encrypt));
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);
        System.out.println(new String(decrypt));


        //Junit单元测试
        //Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));

        //私钥加密，公钥解密
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
        System.out.println(new String(encrypt2));
        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);
        System.out.println(new String(decrypt2));
        //Junit单元测试
        //Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));

    }

    @Test
    public void test03(){
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        pair.getPrivate();
        pair.getPublic();
    }

}
