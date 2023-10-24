package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class CommonMethods {

    public static void print(String string){
        System.out.println(string);
    }

    public static void holdOn(long seconds){
        try{
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            print(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static JsonObject parseToJsonObject(CloseableHttpResponse response){
        try{
        String jsonString = EntityUtils.toString(response.getEntity());
        Gson gson = new Gson();
            return gson.fromJson(jsonString, JsonObject.class);

        }catch(Exception e){
            print(e.getMessage());
            return null;
        }
    }

    public static String generateDateTimeString() {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String formattedDateTime = sdf.format(currentDate);

        return formattedDateTime;
    }

    public static String todayDayAndMonth() {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        String formattedDateTime = sdf.format(currentDate);

        return formattedDateTime;
    }

    public static String convertFormatDateDDMM(String inputDate) {
        try {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM");

            Date date = inputDateFormat.parse(inputDate);
            String outputDate = outputDateFormat.format(date);

            return outputDate;
        } catch (ParseException e) {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM");
            LocalDateTime dateTime = LocalDateTime.parse(inputDate, inputFormatter);
            String output = dateTime.format(outputFormatter);
            return output;
        }
    }

    public static String extractHourAndMinutes(String dateTimeString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");

        try {
            Date date = inputFormat.parse(dateTimeString);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String generateNewPincode() {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
        String formattedDateTime = sdf.format(currentDate);

        return formattedDateTime;
    }

    public static String getDateThreeMonthsAfterToday() {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateThreeMonthsAfter = currentDate.plusMonths(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = dateThreeMonthsAfter.format(formatter);
        return formattedDate;
    }

    public static int generateRandom3DigitNumber() {
        Random random = new Random();
        // Generate a random integer between 100 and 999 (inclusive)
        int randomNumber = random.nextInt(900) + 100;
        return randomNumber;
    }

    public static String convertDateTime(String input) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMMM d 'at' HH:mm");
            String formattedDateTime = dateTime.format(outputFormatter);

            return formattedDateTime;
        } catch (DateTimeParseException e) {
            print("Error: Invalid date-time format.");
            return null;
        }
    }

    public static String getDateMonthInLetters(String input) {
        LocalDateTime dateTime = LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String monthName = dateTime.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String output = monthName + " " + dateTime.getDayOfMonth() + " at " + dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));

        return output;
    }

    public static boolean parseStringToBoolean(String value){
        if(value=="f"){
            return false;
        }
        else{
            return true;
        }
    }


}
