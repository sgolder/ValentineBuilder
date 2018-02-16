package com.example.toshiba.facemaker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
/**
 * MainActivity initializes app
 *
 * @Author Sarah Golder on 2/12/2018.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create listener and face instances
        Listener events = new Listener();
        Face face = (Face)findViewById(R.id.surfaceView);

        //Instantiate all buttons, seekbars, and spinner
        Button randButton =
                (Button)findViewById(R.id.buttonRandomize);
        randButton.setOnClickListener(events);
        //0:Hair, 1:Eye, 2:Skin
        RadioButton[] radGroup = new RadioButton[3];
        radGroup[0] = (RadioButton) findViewById(R.id.radioButtonHair);
        radGroup[1] = (RadioButton) findViewById(R.id.radioButtonEyes);
        radGroup[2] = (RadioButton) findViewById(R.id.radioButtonSkin);
        //O:Red, 1:Green, 2:Blue
        SeekBar[] seekBars = new SeekBar[3];
        seekBars[0] = (SeekBar)findViewById(R.id.seekBarRed);
        seekBars[1] = (SeekBar)findViewById(R.id.seekBarGreen);
        seekBars[2] = (SeekBar)findViewById(R.id.seekBarBlue);

        //Setup spinner for hairstyle selection
        /**
         External Citation
         Date: 13 February 2018
         Problem: Couldn't populate the spinner
         Resource:
         https://developer.android.com/guide/topics/ui/controls
            /spinner.html#SelectListener
         Solution: I used the example code from this post.
         */
        Spinner hairSpin = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.
                createFromResource(this, R.array.hairstyles,
                        R.layout.support_simple_spinner_dropdown_item );
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        hairSpin.setAdapter(adapter);

        //Add listeners
        for( int i = 0; i<3; i++){
            radGroup[i].setOnClickListener(events);
            seekBars[i].setOnSeekBarChangeListener(events);
        }
        hairSpin.setOnItemSelectedListener(events);

        //Setup page on startup
        events.addViews(face, seekBars, radGroup, hairSpin, randButton);
        face.randomize();
        face.setBackgroundColor(Color.CYAN);
        events.updateSeekBars(face.getHairArray());
        radGroup[0].setChecked(true);
    }
}
