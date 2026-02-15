package com.kce.census.Util;

import org.springframework.lang.NonNull;
import java.util.Base64;

public class PasswordUtil {

    public static String encode(@NonNull String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public static boolean matches(@NonNull String raw, @NonNull String encoded) {
        String encodedRaw = encode(raw);
        return encodedRaw.equals(encoded);
    }
}