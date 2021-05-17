package com.cao.alarmreminder

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat


/*
##########################
##                      ##
##   TẠO NOTIFICATION   ##
##                      ##
##########################
*/
class  Notifications(){

    fun Notify(context: Context, message: String?, number: Int){

        val intent= Intent(context,MainActivity::class.java)

        val builder= NotificationCompat.Builder(context, MainActivity::CHANNEL_ID.toString())
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentTitle("New request")
                .setContentText(message)
                .setNumber(number)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentIntent(PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT))
                .setAutoCancel(true)

        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        nm.notify("New request", 0, builder.build())

    }

}