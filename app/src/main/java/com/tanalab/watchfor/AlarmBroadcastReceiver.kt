package com.tanalab.watchfor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * Created by tana on 17/10/28.
 */
class AlarmBroadcastReceiver() : BroadcastReceiver()  {

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"Received",Toast.LENGTH_LONG).show()
    }
}