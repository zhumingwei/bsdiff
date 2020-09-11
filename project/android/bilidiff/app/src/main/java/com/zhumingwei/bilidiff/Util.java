package com.zhumingwei.bilidiff;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dalvik.system.PathClassLoader;

/**
 * @author zhumingwei
 * @date 2020/9/11 10:24
 * @email zdf312192599@163.com
 */
public class Util {
    public static String getSoPath(String name){
        return ((PathClassLoader)(Thread.currentThread().getContextClassLoader())).findLibrary("bsdiff");
    }

    public static int copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        int LENGTH = 1024;
        byte buffer[] = new byte[LENGTH];
        int len;
        while((len = inputStream.read(buffer,0,LENGTH)) != -1){
            outputStream.write(buffer,0,len);
            outputStream.flush();
        }
        return 0;
    }

    public static String sha256(String fileName) throws NoSuchAlgorithmException, IOException {
        byte[] buffer= new byte[8192];
        int count;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName));
        while ((count = bis.read(buffer)) > 0) {
            digest.update(buffer, 0, count);
        }
        bis.close();

        byte[] hash = digest.digest();
        System.out.println(bytesToHex(hash));
        return bytesToHex(hash);
    }

    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
    public static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }
}
