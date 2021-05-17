package com.cao.alarmreminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import java.util.*

class SaveData {

    var context: Context? = null
    var sharedRef: SharedPreferences? = null

    constructor(context:Context) {
        this.context = context
//        file preference nay se la 1 xml file
        this.sharedRef = context.getSharedPreferences("myref", Context.MODE_PRIVATE)
    }

    fun SaveData(hour: Int, minute: Int) {
/*
          editor này để edit data
          như vậy mỗi instance SaveData được tạo ra sẽ đòi lấy file preference (đã để vào biến sharedRef), khiến nó editable
*/
        var editor = sharedRef?.edit()
//        rồi cho số hour vào phần "hour" tương ứng, và minute vào "minute" tương ứng c
        editor?.putInt("hour", hour)
        editor?.putInt("minute", minute)
        editor?.apply()
    }
/*
      bởi function này bắt buộc phải trả về type int, vậy nên e k để safe call ở đây, nếu không return type sẽ phải thành Any. Nhưng bởi chúng ta luôn check null bằng safe call ở phía trên,
      nên nếu có vấn đề biến = null thì ctrinh sẽ cảnh báo ở phía trên trc lỗi này, và có gì e sẽ sửa ở trên đó. Nên khi đến đây e có thể khá chắc biến k phải null rồi
      Default value của 2 cái này = 0
*/
    fun getHour(): Int = sharedRef!!.getInt("hour", 0)

    fun getMinute(): Int = sharedRef!!.getInt("minute", 0)

    fun setAlarm() {
        val hour = getHour()
        val minute = getMinute()
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
//        repeat mỗi khi chúng ta chạm tới giờ này(setRepeating), tại mỗi ngày(INTERVEL_DAY), đây là chế độ mặc định
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY,pi)

    }

}