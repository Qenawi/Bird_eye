package com.example.qenawi.bird_eye;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {
    void Call_main_frag()
    {
    //  back_sack.push("main_frag");
startActivity(new Intent(this,BASS_ACTIVITY.class));

    }

    void Store_POP(String state)
    {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(getString(R.string.SHARED_KEY1), state).commit();
    }
    String get_POP()
    {
        return   PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.SHARED_KEY1),"NO DATA YET");
    }
    void Store_PASS(String state)
    {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(getString(R.string.SHARED_KEY2), state).commit();
    }
    String get_PASS()
    {
        return   PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.SHARED_KEY2),"0000");
    }
    Button start,end;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setTitle("BIRD_EYE");
        super.onCreate(savedInstanceState);
         Ubdate_R  ur =new Ubdate_R();
        registerReceiver(ur,new IntentFilter("Ahmed"));
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Call_main_frag();
        start=(Button)findViewById(R.id.startbt);
        end=(Button)findViewById(R.id.endbt);
        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
startService(new Intent(getBaseContext(),service.class));
            }
        });
        //-----------------------------------------------
        end.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplication(), show_comment_pop.class);
                intent.putExtra("ova", "HISTORY"+"\n"+get_POP());
                startActivity(intent);
                stopService(new Intent(getBaseContext(), service.class));

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public  static class   Ubdate_R extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {
             Log.v("hex"," :  "+intent.getStringExtra("sara"));
            Log.v("hex"," :  "+intent.getStringExtra("DOSA")+" V ");

        }
    }

}
