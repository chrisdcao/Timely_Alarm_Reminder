package com.cao.alarmreminder

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class myBroadcastReceiver: BroadcastReceiver() {
    @SuppressLint("ShowToast")
//    onReceive: sẽ được kích hoạt mỗi khi receive event của system hoạt động
    override fun onReceive(context: Context?, intent: Intent?) {
//        intent sẽ không phải null trong đk này + Nếu intent là null thì nó đơn giản sẽ không thực hiện lệnh if này, chứ không tạo ra lỗi gì cả,
//        nên chúng ta có thể suppress thay vì phải tạo safe call
//        Trong ngoặc: nếu action chúng ta làm là action mà chúng ta muốn kích hoạt, chúng ta toast 1 đoạn text ra ngoài màn hình
        if (intent!!.action.equals("com.tester.alarmmanager")) {
            val b = intent.extras
            Toast.makeText(context, b?.getString("message"), Toast.LENGTH_LONG).show()
        }
    }
}