package com.yunce.common.core.utils.sign;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import org.bouncycastle.crypto.Digest;

import java.util.Map;

//public class SignUtils {
//    /**
//     * 签名工具
//     * */
//    public static String  genSign(Map<String,String > map,String appSecret){
//        Digester md5 = new Digester(DigestAlgorithm.SHA256);
//        String content = map.toString() + "." + appSecret;
//        return md5.digestHex(content);
//    }
//}
