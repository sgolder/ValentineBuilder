package com.example.toshiba.facemaker;

import android.content.Context;
import android.content.pm.PackageInfo;
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
 * Listener class listens to buttons, seekbars, and the spinner
 * and updates Face SurfaceView based on the changes.
 *
 * @Author Sarah Golder on 2/12/2018.
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
    private String faceFeat = "";

    /**
     * Listens to the randomize button and radio buttons
     */
    @Override
    public void onClick(View v) {
        int[] colorArray = new int[3];
        //Randomize button listener
        if(v.equals(randButton)){
            face.randomize();
        }
        //Listens to RadioGroup
        if( skinRad.isChecked() ) {
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

    /**
     * Listens to seekbars and changes colors accordingly
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.i("Listener", "onProgressChanged");
        if( !fromUser ){ return; }
        SeekBar cur = seekBar;
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

    /**
     * Listens to spinner and updates hairstyle in Face class
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            face.setHairStyle(position);
            face.update();
    }

    /**
     * Sets instances of each view for listener class
     */
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

        updateHairSpin();
    }

    public void updateHairSpin(){
        hairSpin.setSelection(face.getHairStyle());
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
