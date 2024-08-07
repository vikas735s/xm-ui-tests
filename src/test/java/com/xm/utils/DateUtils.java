package com.xm.utils;

import org.testng.Reporter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * Calculates the future date based on the number of days from today.
     * This method adds the specified number of days to the current date and returns the resulting date as a formatted string.
     *
     * @param days the number of days to add to the current date
     * @return the future date formatted as "yyyy MMM dd"
     */
    public static String getDateFromToday(int days) {
        // Get the current date and time
        Calendar calendar = Calendar.getInstance();

        // Add the specified number of days to the current date
        calendar.add(Calendar.DAY_OF_YEAR, days);

        // Get the new date
        Date futureDate = calendar.getTime();

        // Define the date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");

        // Log the date using TestNG Reporter
        Reporter.log("Date set from Util : " + sdf, true);

        // Return the formatted date as a string
        return sdf.format(futureDate);
    }

    /**
     * Converts a time string in the format "mm:ss" to total seconds.
     * This method splits the time string into minutes and seconds, converts them to integers, and calculates the total seconds.
     *
     * @param timeString the time string in the format "mm:ss"
     * @return the total time in seconds
     */
    public static int convertTimeToSeconds(String timeString) {
        // Split the string into minutes and seconds
        String[] parts = timeString.split(":");

        // Parse the minutes and seconds as integers
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);

        // Convert minutes to seconds and add to the total seconds
        return minutes * 60 + seconds;
    }
}

