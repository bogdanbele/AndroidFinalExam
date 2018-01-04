package dk.sidereal.finalexambogdanbele.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;

import com.google.gson.Gson;

import java.util.Date;

import dk.sidereal.finalexambogdanbele.LandingScreenActivity;
import dk.sidereal.finalexambogdanbele.R;
import dk.sidereal.finalexambogdanbele.models.Product;

/**
 * Created by Bogdan on 1/2/2018.
 */

public class NotificationService extends IntentService {

    public NotificationService(String name) {
        super(name);
    }

    public NotificationService() {
        super("");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        assert intent != null;
        Product product = new Gson().fromJson(intent.getStringExtra("product"), Product.class);
        Notification.Builder notificationBuilder = new Notification.Builder(this);
        notificationBuilder.setContentTitle(product.getName() + " is expired ");
        notificationBuilder.setContentText(product.getName() + " has expired on " + product.getExpirationDate().toString());
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
