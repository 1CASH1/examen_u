package com.hugo.evaluation.helper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.hugo.evaluation.R
import com.hugo.evaluation.view.HomeActivity

const val channelId = "canal_notificacion"
const val Channelname = "com.hugo.evaluation"

class FirebaseMessaging: FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        generateNotification(remoteMessage.notification!!.title!!,remoteMessage.notification!!.body!!)
    }

    override fun onNewToken(onNewToken: String) {
        super.onNewToken(onNewToken)
    }

    fun getRemoteView(title: String, msg: String): RemoteViews{
        val remoteView = RemoteViews("com.hugo.evaluation",R.layout.item_messaging)
        remoteView.setTextViewText(R.id.tvTitleMessaging,title)
        remoteView.setTextViewText(R.id.tvMessaging,msg)
        remoteView.setImageViewResource(R.id.ivImagenMessaging,R.drawable.icon_map)

        return remoteView
    }

    fun generateNotification(title: String, msg:String){
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)

        var  builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext,channelId)
            .setSmallIcon(R.drawable.icon_map)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pIntent)

        builder = builder.setContent(getRemoteView(title,msg))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(channelId, Channelname, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(0,builder.build())

    }
}