package com.soojung.a20191229_finaltest.fcm

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirbaseMessagingService : FirebaseMessagingService() {

//    푸시알림 받으면 실행되는 함수
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)



    override fun onNewToken(token: String?) {
        super.onNewToken(token)




    }

}
