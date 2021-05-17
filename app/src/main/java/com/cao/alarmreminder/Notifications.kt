package com.cao.alarmreminder

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat.Builder


/*
##########################
##                      ##
##   TẠO NOTIFICATION   ##
##                      ##
##########################
 */
class  Notifications(){

    val NOTIFYTAG = "new request"

    fun Notify(context: Context, message: String?, number:Int){
        val intent= Intent(context,MainActivity::class.java)

        val builder= Builder(context, Constants().CHANNEL_ID)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentTitle("New request")
                .setContentText(message)
                .setNumber(number)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentIntent( PendingIntent.getActivity(context ,0,intent,PendingIntent.FLAG_UPDATE_CURRENT))
                .setAutoCancel(true)

        val nm=context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        nm.notify(NOTIFYTAG, 0, builder.build())

    }

}