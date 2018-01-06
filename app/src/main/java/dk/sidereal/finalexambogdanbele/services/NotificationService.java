package dk.sidereal.finalexambogdanbele.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import dk.sidereal.finalexambogdanbele.LandingScreenActivity;
import dk.sidereal.finalexambogdanbele.R;
import dk.sidereal.finalexambogdanbele.models.Product;

public class NotificationService extends IntentService {

    public NotificationService(String name) {
        super(name);
    }

    public NotificationService() {
        super("");
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        assert intent != null;

        NotificationStream(intent);
    }


    protected void NotificationStream(Intent intent) {

        Product product = new Gson().fromJson(intent.getStringExtra("product"), Product.class);
        int daysIntent = intent.getIntExtra("days", 1);
        String daysIntentString = Integer.toString(daysIntent);
        // add dates to intent

        String date = sdf.format(product.getExpirationDate());

        Notification.Builder notificationBuilder = new Notification.Builder(this);
        if (daysIntent == 30) {
            // prod will expire in a month
            notificationBuilder.setContentTitle(product.getName() + " is expiring in a month ");
            notificationBuilder.setContentText(product.getName() + " is expiring in " + daysIntentString);
        } else if (daysIntent == 7) {
            notificationBuilder.setContentTitle(product.getName() + " is expiring in a week ");
            notificationBuilder.setContentText(product.getName() + " is expiring in " + daysIntentString);

        } else if (daysIntent == 1) {
            notificationBuilder.setContentTitle(product.getName() + " is expiring tomorrow ");
            notificationBuilder.setContentText( "Back in Romania we wouldn't waste " + product.getName());
        } else if (daysIntent <= 0) {
            notificationBuilder.setContentTitle(product.getName() + " is expired ");
            notificationBuilder.setContentText(product.getName() + " has expired on " + date);
        }
        else{
            notificationBuilder.setContentTitle(product.getName() + " is expiring in " + daysIntentString + "days");
            notificationBuilder.setContentText(product.getName() + " is expiring on " + date);
        }



        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_foreground);

        Intent activityIntent = new Intent(this, LandingScreenActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            notificationManager.notify(product.hashCode(), notificationBuilder.build());
        }
    }
}
