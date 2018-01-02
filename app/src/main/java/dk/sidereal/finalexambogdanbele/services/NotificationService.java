package dk.sidereal.finalexambogdanbele.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;

import com.google.gson.Gson;

import dk.sidereal.finalexambogdanbele.models.Product;

/**
 * Created by Bogdan on 1/2/2018.
 */

public class NotificationService extends IntentService{
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public NotificationService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        assert intent != null;
        Product product = new Gson().fromJson(intent.getStringExtra("product"),Product.class);
        Notification.Builder notificationBuilder = new Notification.Builder(this);
        notificationBuilder.setContentTitle(product.getName() + " is expiring ");
        notificationBuilder.setContentText(product.getName() + " is going to expire on " + product.getExpirationDate().toString());
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context. NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(product.hashCode(),notificationBuilder.build());
        }
    }
}
