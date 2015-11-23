package com.kabam.kabam;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;

/**
 * Created by tanke_000 on 11/21/2015.
 */
public class NotificationThread extends Thread{
//    public static volatile boolean notify=false;
//    public static volatile String description;

    public static FragmentActivity fa;
    public NotificationThread(FragmentActivity fa){
        this.fa=fa;
    }
   public void run() {
//       Notification noti = notiBuilder.getNotification();
//       noti.flags=Notification.FLAG_AUTO_CANCEL;
//       NotificationManager notificationManager = (NotificationManager) fa.getSystemService(fa.NOTIFICATION_SERVICE);
//       while (true) {
////           if (notify==true) {
//               notificationManager.notify(0, noti);
////               description="";
////               notify=false;
////           }
//               try{
//              sleep(1000);
//          }
//          catch(InterruptedException ie){
//              ie.printStackTrace();
//          }
//
//       }
   }
    public static void notifyOnce(String description){
        Intent intent=new Intent();
        PendingIntent pIntent = PendingIntent.getActivity(fa, 100, intent, 0);
        NotificationCompat.Builder notiBuilder=new NotificationCompat.Builder(fa).setTicker("Ticker Title")
                .setContentTitle("Notification Content Title")
                .setContentText("Notification Content.")
                .setSmallIcon(R.drawable.logo)
                .setContentIntent(pIntent);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notiBuilder.setSound(alarmSound);
        notiBuilder.setContentText(description);
        Notification noti = notiBuilder.getNotification();
        noti.flags=Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) fa.getSystemService(fa.NOTIFICATION_SERVICE);
        notificationManager.notify(0,noti);
    }
}
