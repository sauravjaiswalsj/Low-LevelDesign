package Util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
    public static String getHashPsswd(String password) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            final byte[] hashbytes = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8));
            String sha3Hex = bytesToHex(hashbytes);
            return sha3Hex;
        }catch (NoSuchAlgorithmException ex){
            System.out.println("Can not hash password");
        }
        return password;
    }

    private static String bytesToHex(byte[] hashbytes){
        StringBuilder sb = new StringBuilder(2 * hashbytes.length);
        for(int i = 0; i <hashbytes.length; i++){
            String hex = Integer.toHexString(0xff & hashbytes[i]);
            if(hex.length() == 1){
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
