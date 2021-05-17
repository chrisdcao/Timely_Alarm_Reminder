package com.cao.alarmreminder

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/*
  từ Android O trở về trước, service thực sự chạy ở background
  nhưng từ android O trở đi, để tiết kiệm pin, chúng ta không thể chạy app background ở tgian dài (bởi Android giải bài toán năng lượng, tài nguyên)
  Bởi vậy ở Android O trở đi, job sẽ chỉ chạy ở background trong tigan ngắn trc khi bị kill. Bởi thế:
  thay cho service đó, Android sử dụng JobScheduler để đến đúng hẹn cái scheduler này sẽ gửi Noti, như vậy nó ko cần chạy Alarm ở background toàn tgian mà vẫn có thể gửi Noti
  -> khi mà chúng ta đặt báo thức thì hệ thống sẽ lưu giờ bao thức lại và để system schedule lịch (vì phần mềm alarm ko phải chạy liên tục ở background, tiết kiệm năng lượng, đây là cơ chế hoạt động từ Android O trở đi)
  Tuy nhiên, do có khả năng ng dùng restart máy trong khi báo thức vẫn tồn tại, chúng ta phải tự lưu data vào file của riêng mình và set lại alarm mỗi khi system reboot
*/
class MainActivity: AppCompatActivity() {

    /*
        ####################################
        ##                                ##
        ##   onCreate: LOAD KHI STARTUP   ##
        ##                                ##
        ####################################
    */
    val CHANNEL_ID = "com.cao.alarmreminder.notificationChannelId"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val saveData = SaveData(applicationContext)
        val minuteDisplay = if (saveData.getMinute() < 10 ) "0${ saveData.getMinute() }" else "${ saveData.getMinute() }"
        tvShowTime.text = "${ saveData.getHour() }:$minuteDisplay"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    /*
        ##########################################################################
        ##                                                                      ##
        ##   buSetTime: POPUP BẢNG CHỌN GIỜ ĐẶT BÁO THỨC (GIAO DIỆN CHỌN GIỜ)   ##
        ##                                                                      ##
        ##########################################################################
    */
    fun buSetTime(view: View) {
        var popTime = PopTime()
        var fm = supportFragmentManager
        popTime.show(supportFragmentManager, "Select time")
    }

    /*
        ####################################################
        ##                                                ##
        ##   setTime: CỦA BUTTON 'DONE' TRONG GIAO DIỆN   ##
        ##                                                ##
        ####################################################
    */
    // this is what happen when the user click the SetTimeButton
    @SuppressLint("SetTextI18n")
    fun setTime(Hours: Int, Minutes: Int) {
        tvShowTime.text = "$Hours:" + if (Minutes < 10) "0$Minutes" else "$Minutes"
        val savedata = SaveData(applicationContext)
        // như vậy là mỗi khi set alarm time chúng ta lưu thông tin time này vào trong file xml sharedPreferences có tên "myref" (trong SaveData.kt)
        savedata.SaveData(Hours, Minutes)
        savedata.setAlarm()
    }

}