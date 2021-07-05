
package com.indielite.cam;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;
import static android.graphics.Color.rgb;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        // TODO(developer): Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        Log.d ("From: %s", Objects.requireNonNull (remoteMessage.getFrom ()));
        Log.d ("Notification Body: %s", Objects.requireNonNull (Objects.requireNonNull (remoteMessage.getNotification ()).getBody ()));
        Log.d ("Notification Title: %s", Objects.requireNonNull (Objects.requireNonNull (remoteMessage.getNotification ()).getTitle ()));
        Log.d ("Message Image: %s", String.valueOf (remoteMessage.getNotification ().getImageUrl ()));
        Log.d ("Message payload: %s", String.valueOf (remoteMessage.getData ()));
        sendNotification ((remoteMessage.getNotification ().getBody ()), (remoteMessage.getNotification ().getTitle ()));
        if (remoteMessage.getData ().size () > 0) {

            // Handle message within 10 seconds
            handleNow ();
        }
    }
    // [END receive_message]
    // [START on_new_token]

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(@NotNull String token) {
        Log.d ("Refreshed token: %s", token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer (token);
    }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {

    }

    /**
     * // [END on_new_token]
     * /**
     * Persist token to third-party servers.
     * <p>
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *