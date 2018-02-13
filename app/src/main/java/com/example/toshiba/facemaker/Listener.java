package com.example.toshiba.facemaker;

import android.view.View;
import android.widget.SeekBar;

/**
 * Created by Sarah Golder on 2/12/2018.
 */

public class Listener implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener{
    private SeekBar redSeek = null;
    private SeekBar greenSeek = null;
    private SeekBar blueSeek = null;

    @Override
    public void onClick(View v) {
        //Randomize button listener
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        SeekBar cur = (SeekBar) seekBar;
        int curId = cur.getId();
        String seekLabel = (String)cur.getResources().
                getResourceEntryName(curId);

        if(seekLabel.equalsIgnoreCase("")){

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //Will not use
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //Will not use
    }
}
