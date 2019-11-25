package com.example.sarwan.tawseel.modules.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseActivity
import com.example.sarwan.tawseel.base.Tawseel
import com.example.sarwan.tawseel.entities.UserProfile
import com.example.sarwan.tawseel.entities.requests.FcmRequest
import com.example.sarwan.tawseel.entities.responses.GeneralResponse
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.network.NetworkRepository
import com.example.sarwan.tawseel.network.RetrofitCustomResponse
import com.example.sarwan.tawseel.utils.GlobalData
import com.google.firebase.messaging.RemoteMessage
import java.util.concurrent.atomic.AtomicInteger

class NotificationHelper(private val context: Context) {

    private val CHANNEL_ID = "Tawseel_Channel"

    fun fcmTokenApi(params: FcmRequest): LiveData<ApiResponse<GeneralResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<GeneralResponse>> = MutableLiveData()
        NetworkRepository.getInstance().fcmToken(params)
            .enqueue(object : RetrofitCustomResponse<GeneralResponse>(responseLiveData) {})
        return responseLiveData
    }

    fun saveTokenInSharedPreference(token: String) {
        (context as Tawseel).getRepository().apply {
            saveDataInSharedPreference(GlobalData.FCM_TOKEN, token)
        }
    }

    fun makeNotification(remoteMessage: RemoteMessage?, messagingService: MessagingService) {
        remoteMessage?.let {
            val intent = it.toIntent()
            intent?.action = if (remoteMessage.notification?.title == "Incoming Order") "DRIVER" else "CUSTOMER"
            intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val pendingIntent = PendingIntent.getActivity(messagingService, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT)

            val notificationBuilder = NotificationCompat.Builder(messagingService, CHANNEL_ID)
                .setContentTitle(it.notification?.title)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                .setContentText(it.notification?.body)
                .setAutoCancel(true)
                .setTimeoutAfter(30000)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent)
                .setColor(messagingService.resources.getColor(R.color.colorPrimary))
                .setSmallIcon(R.mipmap.ic_launcher_round)

            val notificationManager = messagingService.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Since android Oreo riderSearch channel is needed.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(CHANNEL_ID,
                    "Tawseel",
                    NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(channel)
            }
            notificationManager.notify(getID() /* ID of riderSearch */, notificationBuilder.build())
        }
    }

    companion object {
        private val c = AtomicInteger(0)
        fun getID(): Int {
            return c.incrementAndGet()
        }
    }
}