package tn.esprit.safeguardapplication

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import tn.esprit.safeguardapplication.util.NotificationUtils

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: ${remoteMessage.from}")

        // Check if the message contains data payload
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")

            // Handle the data payload
            // You can parse the data and customize the notification accordingly
            val name = remoteMessage.data["name"]
            val description = remoteMessage.data["description"]


            // Display notification
            NotificationUtils.showNotification(applicationContext, name, description)
        }

        // Check if the message contains a notification payload
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
        }
    }

    companion object {
        private const val TAG = "MyFirebaseMessagingService"
    }
}
