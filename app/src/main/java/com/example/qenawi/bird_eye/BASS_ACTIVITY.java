package com.example.qenawi.bird_eye;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

/**
 * Created by QEnawi on 5/7/2016.
 */
public class BASS_ACTIVITY extends AppCompatActivity implements BASS_WORD_CHECK.OnFragmentInteractionListener
{
    int paSS_Status;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bass_activity);
        paSS_Status=0;
    }

    @Override
    public void onFragmentInteraction(Object uri)
    {
      paSS_Status=(int)uri;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(paSS_Status==0){return  false;}
        else
        return super.onKeyDown(keyCode, event);
    }
}
