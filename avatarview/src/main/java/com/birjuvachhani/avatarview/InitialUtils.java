package com.birjuvachhani.avatarview;

import java.util.StringTokenizer;

/**
 * Created by Birju Vachhani on 21-07-2018.
 */
public class InitialUtils {

    public static String getInitials(String fullName) {
        if (fullName == null || fullName.equals("")) return "{}";
        StringTokenizer tokenizer = new StringTokenizer(fullName);
        if (tokenizer.countTokens() == 1) {
            return String.valueOf(tokenizer.nextToken().charAt(0));
        }
        return String.valueOf(tokenizer.nextToken().charAt(0)) + String.valueOf(tokenizer.nextToken().charAt(0));
    }
}
