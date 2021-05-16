package com.cao.alarmreminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

class SaveData {

    var context: Context? = null

    constructor(context:Context) {
        this.context = context
    }

    fun setAlarm(hour: Int, minute: Int) {

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
//        báo thức không hỗ trợ đặt giây
        calendar.set(Calendar.SECOND, 0)

        val am = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

//        chúng ta muốn update intent đúng context hiện tại
        var intent = Intent(context, myBroadcastReceiver::class.java)
//        chúng ta store vào phần extra của intent tại name == "message" là "Alarm time"
        intent.putExtra("message", "Alarm Time")
//        gửi action tới myBroadcastReceiver
        intent.action = "com.test.alarmmanager"

//        we want to fire specific action at the time the setRepeating is called
        val pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//        repeat mỗi khi chúng ta chạm tới giờ này, tại mỗi ngày, đây là chế độ mặc định
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY,pi)

    }

}