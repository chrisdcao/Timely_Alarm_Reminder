package com.cao.alarmreminder

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buSetTime(view: View) {
        var popTime = PopTime()
        var fm = supportFragmentManager
        popTime.show(supportFragmentManager, "Select time")
    }

//    this is what happen when the user click the SetTimeButton
//    suppress textI18N syntax warning because it's not relevant to code
    @SuppressLint("SetTextI18n")
    fun setTime(Hours: Int, Minutes: Int) {
        tvShowTime.text = "$Hours:" + if (Minutes < 10) "0$Minutes" else "$Minutes"
    }

//    khi chúng ta cần app này chạy ở background trong 1 tgian dài, kể cả app đã bị đóng
//    broadcast receiver để nhận push-up noti từ các app khác nhau

//    từ Android O trở về trước, service thực sự chạy ở background
//    nhưng từ android O trở đi, để tiết kiệm pin, chúng ta không thể chạy app background ở tgian dài (bởi Android giải bài toán năng lượng, tài nguyên)
//    Bởi vậy ở Android O trở đi, job sẽ chỉ chạy ở background trong tigan ngắn trc khi bị kill. Bởi thế:
//    thay cho service đó, Android sử dụng JobScheduler để đến đúng hẹn cần gửi Noti, để nó ko cần chạy ở background mà vẫn có thể gửi Noti

//    Đối với GPS, nếu app chạy ở background sẽ bị limit số lượng read, còn ở foreground sẽ bthg
//    Nhưng background vẫn sẽ đọc đc GPS bthg NẾU foreground app có dùng GPS (bởi thế nhiều app demand enabling Location)

//    Tại android O: broad cast chỉ nhận: BOOT_COMPLETED, LOCAL_CHANGED, USB_DEVICE_ATTACHED

}