import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context


object NotificationHelper{
    private fun createChannel(){
        val channel= NotificationChannel("1","Main Channel", NotificationManager.IMPORTANCE_HIGH)
        channel.description="Some Channel description ..."
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }
}