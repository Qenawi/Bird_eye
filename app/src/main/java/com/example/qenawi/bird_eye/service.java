package com.example.qenawi.bird_eye;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by QEnawi on 5/1/2016.
 */
public class service extends Service {

    public String RECON_RESULT;
    void Store_POP(String state)
    {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(getString(R.string.SHARED_KEY1), state).commit();
    }
    String get_POP()
    {
        return   PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.SHARED_KEY1),"NO DATA YET");
    }
    private final BroadcastReceiver receiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            final Bundle bundle = intent.getExtras();
            if(action.equals("android.provider.Telephony.SMS_RECEIVED"))// de el7eta bta3t elrasayl
            {
                //action for sms received
                if (bundle != null)
                {
                 // RECON_RESULT+="NOT_NULL"+"\n";
                    final Object[] pdusObj = (Object[]) bundle.get("pdus");
                    RECON_RESULT="_";

                    for (int i = 0; i < pdusObj.length; i++)
                    {
                        SimpleDateFormat sdf = new SimpleDateFormat("HH / mm / ss");
                        String currentDateandTime = sdf.format(new Date());
                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                        String senderNum = phoneNumber;
                        String message = currentMessage.getDisplayMessageBody();

                        RECON_RESULT+="Message  : "+message+" SENDER :  "+senderNum+" TIME "+currentDateandTime+"\n";
                    }//LOOP
                    Store_POP(RECON_RESULT);
                    }//BUNDEL
         //       RECON_RESULT+="SMS"+"\n";
            }
            //3ayz azwd el telephonat
            // wifi
            // wrong password
        }
    };

    @Override
    public void onCreate()
    {
        super.onCreate();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        filter.addAction(android.telephony.TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        registerReceiver(receiver, filter);

    }

    @Nullable
    Intent i = new Intent();
    @Override
    public IBinder onBind(Intent intent) {
        // enible me to bind activity to the service
        // it enable activity to have adirect acess to the service
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        // it s going to run untill we stop iT
        //  return super.onStartCommand(intent, flags, startId); // no tell yt
        Toast.makeText(this, "SERVICE ini4ated", Toast.LENGTH_LONG).show();
        //  assert i != null;
        this.sendBroadcast(i);
        // do what ever U neeD
        return START_STICKY;// - (START_STICKY) - it will keep the service going untill we excipictly Stop IT
    }

    @Override
    public void onDestroy()
    {
        // CAlled when i need to destry it
        unregisterReceiver(receiver);
        this.sendBroadcast(i);
        super.onDestroy();
 //       RECON_RESULT="";
        Toast.makeText(this, "SERVICE  is Going Dwn ", Toast.LENGTH_LONG).show();
    }
    // listner to the events
    // fetsh data form the activity
}