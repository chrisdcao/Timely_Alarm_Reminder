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
      /*
          Trong ngoặc: nếu action chúng ta làm là action mà chúng ta muốn kích hoạt, chúng ta toast 1 đoạn text ra ngoài màn hình
          Chúng ta liên tục để safe call, mặc dù khá chắc rằng intent sẽ ko null nhưng vẫn để để chẳng may nó có null thật thì ctrinh sẽ throw null exception dễ nhìn hơn
      */
        if (intent?.action.equals("com.tester.alarmmanager")) {
            val b = intent?.extras
            Toast.makeText(context, b?.getString("message"), Toast.LENGTH_LONG).show()
        }
        else if (intent?.action.equals("android.intent.action.BOOT_COMPLETED")) {
            val saveData = SaveData(context!!)
            saveData.setAlarm()
        }
    }

}