package com.hostel.hostel_management.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {

     static {
        Dotenv dotenv = Dotenv.load();

        System.setProperty("SERVER_PORT", dotenv.get("SERVER_PORT"));
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
        System.setProperty("RAZORPAY_KEY_ID", dotenv.get("RAZORPAY_KEY_ID"));
        System.setProperty("RAZORPAY_KEY_SECRET", dotenv.get("RAZORPAY_KEY_SECRET"));
    }

}