package com.moviesExerciseREST.mms_backend.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class Utils {

    public static boolean isValidDate(String inputDate) {
        // List of common date formats to check
        List<String> dateFormats = List.of(
                "yyyy-MM-dd",
                "dd/MM/yyyy",
                "MM-dd-yyyy",
                "dd-MM-yyyy",
                "yyyy/MM/dd",
                "MM/dd/yyyy",
                "yyyy.MM.dd",
                "dd.MM.yyyy",
                "yyyyMMdd"
        );

        // Try parsing the date with each format
        for (String format : dateFormats) {
            if (isValidDateForFormat(inputDate, format)) {
                return true; // Return true if it matches any format
            }
        }

        // If none of the formats match, it's invalid
        System.out.println("Invalid date: " + inputDate);
        return false;
    }

    private static boolean isValidDateForFormat(String inputDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false); // Ensures strict validation

        try {
            Date date = sdf.parse(inputDate);
            System.out.println("Valid date: " + date + " (Format: " + format + ")");
            return true;
        } catch (ParseException e) {
            // Ignore the exception, continue trying other formats
        }

        return false;
    }

    // Method to decode Base64 string into byte array
    public byte[] decodeBase64(String base64String) {
        // Now decode the Base64 string into byte array
        return Base64.getDecoder().decode(base64String);
    }

}
