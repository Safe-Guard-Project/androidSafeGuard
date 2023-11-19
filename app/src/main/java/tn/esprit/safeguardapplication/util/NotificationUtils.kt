package tn.esprit.safeguardapplication.util
import android.content.Context
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import tn.esprit.safeguardapplication.R

object NotificationUtils {

    fun showNotification(context: Context, name: String?, data: String?) {
        val notificationBuilder = NotificationCompat.Builder(context, "channel_id")
            .setSmallIcon(R.drawable.earthquake)
            .setContentTitle(name)
            .setContentText(data)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Notification ID allows you to update or cancel the notification later on
        val notificationId = 123
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}
