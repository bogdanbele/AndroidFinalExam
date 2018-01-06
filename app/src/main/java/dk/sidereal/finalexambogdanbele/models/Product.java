package dk.sidereal.finalexambogdanbele.models;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;

import dk.sidereal.finalexambogdanbele.utils.UniqueIdentifiers;
import dk.sidereal.finalexambogdanbele.utils.TimeUtils;

import com.google.gson.Gson;


import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import dk.sidereal.finalexambogdanbele.services.NotificationService;

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
        this.id = Integer.valueOf(last4Str);
        SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
        try {
            this.expirationDate = f.parse(expirationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void scheduleNotifications(Context context) {

        // We convert both the expiration date and the current date to MS
        // We subtract the current time from the expiration date and further
        // on we transform the MS to days to find the difference.

        long currentTime = Calendar.getInstance().getTime().getTime();
        long daysToExpiration = expirationDate.getTime() - currentTime;
        int notificationDay = (int) ((daysToExpiration / 86400000));

        if ( notificationDay <= 0 ){
            scheduleNotification(context,0);
            return;
        }
        while (notificationDay >= 1) {
            scheduleNotification(context, notificationDay);
            notificationDay = Math.round(notificationDay / 2);
            if(notificationDay == 1 ){
                scheduleNotification(context, 1);
                scheduleNotification(context, 0);
                return;
            }
        }


    }

    private void scheduleNotification(Context context, int days) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long when = expirationDate.getTime();
        long when2 = when - TimeUtils.DaysToMs(days);
        Intent intent = new Intent(context, NotificationService.class);
        intent.removeExtra("product");
        intent.putExtra("product", new Gson().toJson(this));
        intent.putExtra("days", days);
        PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT).cancel();


        PendingIntent pendingIntent = PendingIntent.getService(context, UniqueIdentifiers.generateUniqueTimestamp(), intent, 0);
        if (alarmManager != null) {
            alarmManager.set(AlarmManager.RTC, when2, pendingIntent);
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
        //to convert Date to String, use format method of SimpleDateFormat class.
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");


        return dateFormat.format(expirationDate);
        // return expirationDate.toString();


    }

}
