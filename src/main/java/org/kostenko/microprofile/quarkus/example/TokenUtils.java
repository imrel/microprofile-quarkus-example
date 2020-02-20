package org.kostenko.microprofile.quarkus.example;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import org.eclipse.microprofile.jwt.Claims;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class TokenUtils {

    public static String generateJWT(String userName) {

        long currentTimeInSecs = currentTimeInSecs();
        
        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put("iss", "https://kostenko.org");
        claimMap.put("sub", "jwt-rbac");
        claimMap.put("exp", Long.MAX_VALUE);
        claimMap.put("iat", currentTimeInSecs);
        claimMap.put("auth_time", currentTimeInSecs);
        claimMap.put("jti", UUID.randomUUID().toString());
        claimMap.put("upn", "UPN");
        claimMap.put("groups", Arrays.asList("user"));
        claimMap.put("raw_token", UUID.randomUUID().toString());
        claimMap.put("user_name", userName);
                
        JwtClaimsBuilder claims = Jwt.claims(claimMap);
        claims.claim(Claims.auth_time.name(), currentTimeInSecs);
        
        String jwt;
        try {
            jwt = claims.jws().signatureKeyId("META-INF/private_key.pem").sign(readPrivateKey("META-INF/private_key.pem"));
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
        return jwt;
    }

    public static PrivateKey readPrivateKey(final String pemResName) throws Exception {
        InputStream contentIS =  Thread.currentThread().getContextClassLoader().getResourceAsStream(pemResName);
        byte[] tmp = new byte[4096];
        int length = contentIS.read(tmp);
        return decodePrivateKey(new String(tmp, 0, length, "UTF-8"));
    }

    public static PrivateKey decodePrivateKey(final String pemEncoded) throws Exception {
        byte[] encodedBytes = toEncodedBytes(pemEncoded);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }

    private static byte[] toEncodedBytes(final String pemEncoded) {
        final String normalizedPem = removeBeginEnd(pemEncoded);
        return Base64.getDecoder().decode(normalizedPem);
    }

    private static String removeBeginEnd(String pem) {
        pem = pem.replaceAll("-----BEGIN (.*)-----", "");
        pem = pem.replaceAll("-----END (.*)----", "");
        pem = pem.replaceAll("\r\n", "");
        pem = pem.replaceAll("\n", "");
        return pem.trim();
    }

    public static int currentTimeInSecs() {
        long currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS / 1000);
    }

}
