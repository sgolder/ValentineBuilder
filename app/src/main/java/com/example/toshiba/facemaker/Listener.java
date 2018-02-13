package com.example.toshiba.facemaker;

import android.view.View;
import android.widget.SeekBar;

/**
 * Created by Sarah Golder on 2/12/2018.
 */

public class Listener implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener{
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

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
