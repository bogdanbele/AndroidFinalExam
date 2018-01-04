package dk.sidereal.finalexambogdanbele.models;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;

import com.google.gson.Gson;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import dk.sidereal.finalexambogdanbele.services.NotificationService;

/**
 * Created by Bogdan on 12/27/2017.
 */

public class Product {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String name;
    private Date expirationDate;


    public Product(String name, String expirationDate) {
        this.name = name;
        long time = new Date().getTime();
        String tmpStr = String.valueOf(time);
        String last4Str = tmpStr.substring(tmpStr.length() - 5);
        int notificationId = Integer.valueOf(last4Str);
        this.id = notificationId;
        SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
        try {
            this.expirationDate = f.parse(expirationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void scheduleNotification(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long when = expirationDate.getTime();
        Intent intent = new Intent(context, NotificationService.class);
        intent.removeExtra("product");
        intent.putExtra("product",new Gson().toJson(this));
        PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT).cancel();

        long time = new Date().getTime();
        String tmpStr = String.valueOf(time);
        String last4Str = tmpStr.substring(tmpStr.length() - 5);
        int notificationId = Integer.valueOf(last4Str);

        PendingIntent pendingIntent = PendingIntent.getService(context, notificationId,intent,0);
        if (alarmManager != null) {
            alarmManager.set(AlarmManager.RTC,when,pendingIntent);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getExpirationDateToString() {
        SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");


        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        //to convert Date to String, use format method of SimpleDateFormat class.


        return dateFormat.format(expirationDate);
        // return expirationDate.toString();


    }

    public void setExpirationDate(String expirationDate) {
        SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
        try {
            this.expirationDate = f.parse(expirationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
