package com.example.sarwan.tawseel.modules.notifications

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService() : FirebaseMessagingService(){

    var TAG = "MessagingService"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("onMessageReceived",remoteMessage.notification?.title + " " +
                remoteMessage.notification?.body)
     //   showInAppNotification(remoteMessage)
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Registration token$token")
        saveFirebaseTokenLocalOrServer(token)
    }

    private fun saveFirebaseTokenLocalOrServer(token: String?) {
        token?.let { fcmToken ->
            NotificationHelper(applicationContext).processToken(token)
        }
    }
}