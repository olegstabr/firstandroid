package com.example.olegs.firstapp

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.NotificationCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import android.media.RingtoneManager



class MainActivity : AppCompatActivity() {
    val NOTIFY_ID = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionButton = findViewById(R.id.main_floatbutton)
        actionButton?.setOnClickListener(OnClickListener {
            v -> v
            val snackbar = Snackbar.make(v, "Own action", Snackbar.LENGTH_SHORT).setAction("OK", onSnackbarClickListener)
            snackbar.show()

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when (id) {
            R.id.action_item1 -> {
                Toast.makeText(applicationContext, "Вы создали уведомление", Toast.LENGTH_LONG).show()
                onSettingsButtonClick()
            }
            R.id.action_item2 -> Toast.makeText(applicationContext, "Вы выбрали пункт 2", Toast.LENGTH_LONG).show()
            R.id.action_item3 -> Toast.makeText(applicationContext, "Вы выбрали пункт 3", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

    val onSnackbarClickListener : View.OnClickListener = OnClickListener {
        Toast.makeText(applicationContext, "Good Job!", Toast.LENGTH_SHORT).show()
    }

    fun onSettingsButtonClick() {
        val context = applicationContext
        val notificationIntent = Intent(context, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT)
        val resources = context.resources
        val notificationBuilder = NotificationCompat.Builder(context)

        notificationBuilder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_stat_onesignal_default)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_notification))
                .setTicker(resources.getString(R.string.warning))
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle(resources.getString(R.string.notifytitle))
                .setContentText(resources.getString(R.string.notifytext))
        val notification = notificationBuilder.build()
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val ringURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val vibrate = longArrayOf(1000, 1000, 1000)

        notification.sound = ringURI
        notification.vibrate = vibrate;
        notification.ledARGB = Color.CYAN
        notification.ledOffMS = 0
        notification.ledOnMS = 1
        notification.flags = notification.flags or Notification.FLAG_SHOW_LIGHTS

        notificationManager.notify(NOTIFY_ID, notification)
    }
}
