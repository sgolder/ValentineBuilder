package com.example.toshiba.facemaker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Listener events = new Listener();
        Log.i("FaceMaker", "Activity main called");
        Button randButton =
                (Button)findViewById(R.id.buttonRandomize);
        randButton.setOnClickListener(events);

        //0:Hair, 1:Eye, 2:Skin
        RadioButton[] radGroup = new RadioButton[3];
        radGroup[0] = (RadioButton) findViewById(R.id.radioButtonHair);
        radGroup[1] = (RadioButton) findViewById(R.id.radioButtonEyes);
        radGroup[2] = (RadioButton) findViewById(R.id.radioButtonSkin);

        SeekBar[] seekBars = new SeekBar[3];
        seekBars[0] = (SeekBar)findViewById(R.id.seekBarRed);
        seekBars[1] = (SeekBar)findViewById(R.id.seekBarGreen);
        seekBars[2] = (SeekBar)findViewById(R.id.seekBarBlue);

        //Add listeners
        for( int i = 0; i<3; i++){
            radGroup[i].setOnClickListener(events);
            seekBars[i].setOnSeekBarChangeListener(events);
        }
        radGroup[2].setOnClickListener(events);

        Face face = (Face)findViewById(R.id.surfaceView);


        events.addViews(face, seekBars, radGroup);
        face.randomize();
    }
}
