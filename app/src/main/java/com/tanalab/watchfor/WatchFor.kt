package com.tanalab.watchfor

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.gms.maps.MapView

import kotlinx.android.synthetic.main.activity_watch_for.*
import kotlinx.android.synthetic.main.content_watch_for.*
import java.util.*
import kotlin.collections.ArrayList

class WatchFor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch_for)
        setSupportActionBar(toolbar)

        startstop.text = "更新"

        chart.setTouchEnabled(true)

        chart.setBackgroundColor(Color.WHITE)

        var entries = ArrayList<Entry>()
        entries.add(Entry(10f,0f))
        entries.add(Entry(20f,1f))
        entries.add(Entry(30f,5f))
        entries.add(Entry(40f,3f))

        var dataSet = LineDataSet(entries,"move")

        var data  = LineData(dataSet)

        data.setValueTextColor(Color.BLACK)
        chart.setData(data)

        chart.invalidate()

        startstop.setOnClickListener {
            var calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.add(Calendar.SECOND,5)

            val intent = Intent(this,AlarmBroadcastReceiver::class.java)
            val pending = PendingIntent.getBroadcast(this,0,intent,0)

            var am : AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            am.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pending)
            Toast.makeText(this,"SetAlarm",Toast.LENGTH_SHORT).show()


        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_watch_for, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
