package com.example.toshiba.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

import java.util.Random;

import static android.graphics.Color.rgb;

/**
 * Created by Toshiba on 2/12/2018.
 */

public class Face extends SurfaceView {

    //0 = red, 1 = green, 2 = blue
    private int[] skinColor = new int[3];
    private int[] eyeColor = new int[3];
    private int[] hairColor = new int[3];
    private int hairStyle;

    public Face(Context context){
        super(context);
        setWillNotDraw(false);
        randomize();
    }

    public void onDraw(Canvas canvas){
        //Initialize face with random generation of color/style
        Paint skin = new Paint(skinColor);
        Paint eyes = new Paint(eyeColor);
        Paint hair = new Paint(hairColor);

        //Draw selected hairstyle
        if( hairStyle == 0 ){
            drawGirlCut(canvas, hair);
        }
        else if( hairStyle == 1 ){
            drawFrostedTips(canvas, hair);
        }
        else if( hairStyle == 2 ){
            drawPartyHat(canvas, hair);
        }

        //Paint face
        //Paint eyes
        //Nose? Mouth?
    }

    public void randomize() {
        Random rand = new Random();
        for(int i = 0; i<3; i++){
            skinColor[i] = rand.nextInt(255);
            eyeColor[i] = rand.nextInt(255);
            hairColor[i] = rand.nextInt(255);
        }

        hairStyle = rand.nextInt(2);

        //Set seekBars based on int values
        //Maybe have separate classes for each facial feat?
        //E.g. Eye: red val, green val, blue val with getter methods
    }

    public void drawGirlCut(Canvas canvas, Paint hair){
        //Paint circle
        //Paint rect
    }

    public void drawFrostedTips(Canvas canavas, Paint hair){
        //Paint rect
        //Loop triangles
    }

    public void drawPartyHat(Canvas canvas, Paint hair){
        //Draw triangle outline
        //Stripes with the overlapping methods??
    }

    public void setRedVal(int newRed, String faceFeat){
        if(faceFeat.equals("Hair")){

        }
        else if(faceFeat.equals("Eyes")){

        }
        else if(faceFeat.equals(""))
    }

    public void setGreenVal(int newGreen, String faceFeat){

    }

    public void setBlueVal(int newBlue, String faceFeat){

    }
}
