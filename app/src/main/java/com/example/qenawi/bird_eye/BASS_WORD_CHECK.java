package com.example.qenawi.bird_eye;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class BASS_WORD_CHECK extends android.support.v4.app.Fragment
{
ArrayList<String>GRID_DATA=new ArrayList<>();
    GridView grid;
    String income_pass="";
    Button GO_BTN;
    public ArrayAdapter<String> pass;

    void Fill_GRID(GridView tmp )
    {
        for(int i=1;i<=9;i++)
        {
            GRID_DATA.add(String.valueOf(i));
        }
pass=new ArrayAdapter<>(getActivity(), R.layout.layout,GRID_DATA);
        tmp.setAdapter(pass);
    }
    void Store_PASS(String state)
    {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString(getString(R.string.SHARED_KEY2), state).commit();
    }
    String get_PASS()
    {
        return   PreferenceManager.getDefaultSharedPreferences(getContext()).getString(getString(R.string.SHARED_KEY2),"0000");
    }

    private OnFragmentInteractionListener mListener;
    public BASS_WORD_CHECK()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Store_PASS("123");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_bass__word__check, container, false);
        GO_BTN=(Button)root.findViewById(R.id.GO_BTN);
        return  root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        View root=view;
        grid=(GridView)root.findViewById(R.id.GRID);
        Fill_GRID(grid);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                income_pass += String.valueOf(position + 1);
            }
        });
GO_BTN.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v)
    {
          if(income_pass.equals(get_PASS())){
              Log.v("hex","well DONE");ON_ACTION(1);}
        else {income_pass="";
              Toast.makeText(getActivity(),"WRONG PASS",Toast.LENGTH_SHORT).show();ON_ACTION(0);}

    }
});//listner


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void ON_ACTION(Object uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Object uri);
    }

}
