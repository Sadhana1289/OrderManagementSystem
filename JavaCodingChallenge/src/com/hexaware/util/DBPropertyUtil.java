package com.hexaware.util;

import java.io.*;
import java.util.*;

public class DBPropertyUtil {
    public static String getConnectionString(String propertyFileName) {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream(propertyFileName)) {
            props.load(input);
            return props.getProperty("db.url");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
