package com.example.toshiba.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;

import static android.graphics.Color.rgb;

/**
 * Created by Toshiba on 2/12/2018.
 */

public class Face extends SurfaceView {

    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;
    //0 = red, 1 = green, 2 = blue
    private int[] skinArray = new int[3];
    private int[] eyeArray = new int[3];
    private int[] hairArray = new int[3];

    public Face(Context context){
        super(context);
        generalInit();
        randomize();
    }
    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        generalInit();
    }

    public Face(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        generalInit();
    }

    /**\
     * generalInit
     *
     * Initialization stuff used by all ctors
     *
     */
    private void generalInit() {
        setWillNotDraw(false);
    }

    @Override
    public void onDraw(Canvas canvas){
        //Initialize face with random generation of color/style
        Log.i("FaceMaker", "onDraw called");
        int xCenter = (canvas.getWidth())/2;
        int yCenter = (canvas.getHeight())/2;
        Paint skin = new Paint();
        skin.setColor(skinColor);
        skin.setStrokeWidth(5.0f);

        //Draw selected hairstyle
        if( hairStyle == 0 ){
            drawGirlCut(canvas);
        }
        else if( hairStyle == 1 ){
            drawFrostedTips(canvas);
        }
        else if( hairStyle == 2 ){
            drawPartyHat(canvas);
        }

        Paint eyes = new Paint();
        Paint hair = new Paint();
        eyes.setColor(eyeColor);
        hair.setColor(hairColor);

        //Paint face
        canvas.drawCircle(xCenter, yCenter, 400.0f, skin);


        //Paint eyes
        //Nose? Mouth?
    }

    public void randomize() {
        Random rand = new Random();
        for(int i = 0; i<3; i++){
            skinArray[i] = rand.nextInt(255);
            eyeArray[i] = rand.nextInt(255);
            hairArray[i] = rand.nextInt(255);
        }

        hairStyle = rand.nextInt(2);
        rgbToInt();
        Log.i("FaceMaker", "Red value: "+skinArray[0]);

        //Set seekBars based on int values
        //Maybe have separate classes for each facial feat?
        //E.g. Eye: red val, green val, blue val with getter methods
    }

    public void drawGirlCut(Canvas canvas){
        //Paint circle
        //Paint rect
    }

    public void drawFrostedTips(Canvas canavas){
        //Paint rect
        //Loop triangles
    }

    public void drawPartyHat(Canvas canvas){
        //Draw triangle outline
        //Stripes with the overlapping methods??
    }



    public void rgbToInt(){
        skinColor = rgb(skinArray[0], skinArray[1], skinArray[2]);
        eyeColor = rgb(eyeArray[0], eyeArray[1], eyeArray[2]);
        hairColor = rgb(hairArray[0], hairArray[1], hairArray[2]);
    }

    public void update(){
        //external citation
        //https://stackoverflow.com/questions/18607335/how-to-update-a-surfaceviewLog.i("Face", "Update Face");
        invalidate();
    }

    //Getters and setters
    public void setRedVal(int newRed, String faceFeat){
        if(faceFeat.equals("Hair Color")){

        }
        else if(faceFeat.equals("Eye Color")){

        }
        else if(faceFeat.equals("Skin Color")){

        }
    }

    public void setGreenVal(int newGreen, String faceFeat){

    }

    public void setBlueVal(int newBlue, String faceFeat){

    }

    public int[] getSkinArray(){
        return skinArray;
    }
}
