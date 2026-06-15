package com.joselv.mobile.core;

import java.io.InputStream;
import java.util.Properties;

/**
 * Loads framework configuration from {@code config/config.properties}.
 * Any property can be overridden at runtime with a JVM system property,
 * e.g. {@code mvn test -DdeviceName="Pixel_7"}.
 */
public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config/config.properties")) {
            if (input == null) {
                throw new IllegalStateException("config/config.properties not found on classpath");
            }
            PROPERTIES.load(input);
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Failed to load configuration: " + e.getMessage());
        }
    }

    private ConfigReader() {
    }

    /** System property wins over file value, so CI can override anything. */
    public static String get(String key) {
        String override = System.getProperty(key);
        return (override != null && !override.isBlank()) ? override : PROPERTIES.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        String value = get(key);
        return (value != null && !value.isBlank()) ? value : defaultValue;
    }

    public static int getInt(String key, int defaultValue) {
        String value = get(key);
        return (value != null && !value.isBlank()) ? Integer.parseInt(value.trim()) : defaultValue;
    }
}
