package tn.esprit.safeguardapplication.util
import android.content.Context
import android.app.NotificationManager
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import tn.esprit.safeguardapplication.R

object NotificationUtils {

    fun showNotification(context: Context, name: String?, description: String?) {
        val contentView = RemoteViews(
            context.packageName,
            R.layout.notification
        )

        contentView.setTextViewText(R.id.notifTitle, "You are in the radius of a catastrophe")
        contentView.setTextViewText(R.id.notifDescription, description)
        contentView.setImageViewResource(R.id.notifLogo, R.drawable.earthquake)
        val notificationBuilder = NotificationCompat.Builder(context, "channel_id")
            .setSmallIcon(R.drawable.earthquake)
            .setContentTitle(name)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContent(contentView)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Notification ID allows you to update or cancel the notification later on
        val notificationId = 123
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}
