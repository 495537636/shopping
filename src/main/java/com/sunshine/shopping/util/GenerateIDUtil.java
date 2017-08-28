package com.sunshine.shopping.util;

import java.util.UUID;

public class GenerateIDUtil {
	
	public static String generateID() {
        UUID random = UUID.randomUUID();
        String str = random.toString();
        String uuid = str.replace("-", "");
        return uuid;
    }
    
    public static void main(String[] args) {
	    System.out.println(GenerateIDUtil.generateID());
    }
	
}
