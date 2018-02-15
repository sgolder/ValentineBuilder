package com.example.toshiba.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 * Created by Sarah Golder on 2/12/2018.
 */

public class Listener implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener, AdapterView.OnItemSelectedListener {
    private SeekBar redSeek = null;
    private SeekBar greenSeek = null;
    private SeekBar blueSeek = null;
    private RadioButton hairRad = null;
    private RadioButton eyesRad = null;
    private RadioButton skinRad = null;
    private Face face = null;
    private Spinner hairSpin;
    private Button randButton = null;
    String faceFeat = "";

    @Override
    public void onClick(View v) {
        //Randomize button listener
        Log.i("Listener", "onClick");
        int[] colorArray = new int[3];

        if(v.equals(randButton)){
            face.randomize();
        }

        //RadioGroup
        if( skinRad.isChecked() )
        {
            colorArray = face.getSkinArray();
            updateSeekBars(colorArray);
            faceFeat = "Skin";
        }
        else if( eyesRad.isChecked() ){
            colorArray = face.getEyeArray();
            updateSeekBars(colorArray);
            faceFeat = "Eye";
        }
        else if( hairRad.isChecked() ){
            colorArray = face.getHairArray();
            updateSeekBars(colorArray);
            faceFeat = "Hair";
        }
        face.update();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.i("Listener", "onProgressChanged");
        if( !fromUser ){ return; }
        SeekBar cur = (SeekBar) seekBar;
        int curId = cur.getId();
        //Insert external citation
        String seekLabel = cur.getResources().
                getResourceEntryName(curId);

        if(seekLabel.equals("seekBarRed")){
            face.setColorVal(progress, 0, faceFeat);
        }
        else if(seekLabel.equals("seekBarGreen")){
            face.setColorVal(progress, 1, faceFeat);
        }
        else if(seekLabel.equals("seekBarBlue")){
            face.setColorVal(progress, 2, faceFeat);
        }

        face.update();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            face.setHairStyle(position);
    }

    public void addViews(SurfaceView initface, SeekBar[] seekBars,
                         RadioButton[] radGroup, Spinner spinner,
                         Button rand){
        Log.i("Listener", "addViews");
        face = (Face)initface;

        redSeek = seekBars[0];
        greenSeek = seekBars[1];
        blueSeek = seekBars[2];

        hairRad = radGroup[0];
        eyesRad = radGroup[1];
        skinRad = radGroup[2];

        randButton = rand;
        hairSpin = spinner;
    }

    public void updateSeekBars(int[] colorArray){
        Log.i("Listener", "updateSeekBars");
        redSeek.setProgress(colorArray[0]);
        greenSeek.setProgress(colorArray[1]);
        blueSeek.setProgress(colorArray[2]);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //Will not use
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //Will not use
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Will not use
    }
}
