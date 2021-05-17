package com.cao.alarmreminder

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.pop_time.view.*

/*
###############################################################
##                                                           ##
##   POP UP BẢNG GIAO DIỆN CHỌN GIỜ (DÙNG ANDROID FRAGMENT)  ##
##                                                           ##
###############################################################
 */
class PopTime: DialogFragment() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        var myView = inflater!!.inflate(R.layout.pop_time, container, false)
        var buDone = myView.BuDone as Button
        var tp1 = myView.timePicker1 as TimePicker

        buDone.setOnClickListener() {
            // đây là static object nên mình phải access thẳng, chứ không dùng new object
            val ma = activity as MainActivity
            ma.setTime(tp1.hour, tp1.minute)
            // after the person selected the time, he has to dimiss the activity
            this.dismiss()
        }

        //return myView so that it gets the context when it's created rather than this context before it's created
        return myView
    }

}