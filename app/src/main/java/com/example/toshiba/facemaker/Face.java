package com.example.toshiba.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;

import static android.R.attr.x;
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
    private Paint skin = new Paint();
    private Paint eyes = new Paint();
    private Paint hair = new Paint();

    public Face(Context context){
        super(context);
        generalInit();
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onDraw(Canvas canvas){
        //Initialize face with random generation of color/style
        Log.i("FaceMaker", "onDraw");
        int xCenter = (canvas.getWidth())/2;
        int yCenter = (canvas.getHeight())/2;
        Paint myPaint = new Paint();
        skin.setColor(skinColor);
        skin.setStrokeWidth(5.0f);
        eyes.setColor(eyeColor);
        hair.setColor(hairColor);

        //Draw selected hairstyle
        if( hairStyle == 0 ){
            drawGirlCut(canvas, xCenter, yCenter);
        }
        else if( hairStyle == 1 ){
            drawFrostedTips(canvas, xCenter, yCenter);
        }
        else if( hairStyle == 2 ){
            drawPartyHat(canvas);
        }

        //Paint face
        for(int i = 0; i<100; i = i+10){
            canvas.drawCircle(xCenter, yCenter+i, 400.0f, skin);
        }
        //Paint eyes
        //external citation: set sty
        //https://developer.android.com/reference/android/graphics/Paint.Style.html
        canvas.drawCircle(xCenter - 175, yCenter, 170, eyes);
        canvas.drawCircle(xCenter + 175, yCenter, 170, eyes);
        myPaint.setColor(Color.BLACK);
        canvas.drawCircle(xCenter - 175, yCenter, 120, myPaint);
        canvas.drawCircle(xCenter + 175, yCenter, 120, myPaint);
        //myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(5.0f);
        //canvas.drawCircle(xCenter - 175, yCenter, 170, myPaint);
        //canvas.drawCircle(xCenter + 175, yCenter, 170, myPaint);

        //Nose? Mouth?
        canvas.drawArc(xCenter-100,yCenter+100, xCenter+100,
                yCenter+400, 0, 180, true, myPaint);
    }

    public void randomize() {
        Log.i("FaceMaker", "randomize");
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

    public void drawGirlCut(Canvas canvas, int xCenter, int yCenter){
        hair.setStrokeWidth(5.0f);
        canvas.drawCircle(xCenter, yCenter, 500, hair);
        canvas.drawRect(xCenter-500.0f, yCenter, xCenter+500,
                    yCenter+700, hair);
    }

    public void drawFrostedTips(Canvas canvas, int xCenter, int yCenter){
        canvas.drawRect(xCenter-400, yCenter-400, xCenter+400,
                yCenter, hair);
        //External citation
        //https://stackoverflow.com/questions/3501126/how-to-draw-a-filled-triangle-in-android-canvas

        hair.setColor(Color.WHITE);
        hair.setStrokeWidth(20.0f);
        //hair.setStyle(Paint.Style.FILL);
        hair.setAntiAlias(true);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        float x1, y1, x2, y2;
        x1 = 100;
        x2 = 200;
        y1 = 100;
        y2 = 200;
        path.moveTo(x1,y1);
        path.lineTo(x2, y2);
        path.moveTo(100.0f, 200.0f);
        path.lineTo(200.0f, 200.0f);
        path.moveTo(200.0f, 200.0f);
        path.lineTo(100.0f, 100.0f);
        path.close();

        canvas.drawPath(path, hair);
    }

    public void drawPartyHat(Canvas canvas){
        //Draw triangle outline
        //Stripes with the overlapping methods??
    }

    public void rgbToInt(){
        Log.i("FaceMaker", "rgbToInt");
        skinColor = rgb(skinArray[0], skinArray[1], skinArray[2]);
        eyeColor = rgb(eyeArray[0], eyeArray[1], eyeArray[2]);
        hairColor = rgb(hairArray[0], hairArray[1], hairArray[2]);
    }

    public void update(){
        Log.i("FaceMaker", "update");
        //external citation
        //https://stackoverflow.com/questions/18607335/how-to-update-a-surfaceviewLog.i("Face", "Update Face");
        invalidate();
    }

    //Getters and setters
    public void setColorVal(int newValue, int color, String faceFeat){
        Log.i("FaceMaker", "setColorVal");
        if(faceFeat.equals("Hair")){
            hairArray[color] = newValue;
        }
        else if(faceFeat.equals("Eye")){
            eyeArray[color] = newValue;
        }
        else if(faceFeat.equals("Skin")){
            skinArray[color] = newValue;
        }
        rgbToInt();
    }

    public int[] getSkinArray(){ return skinArray; }

    public int[] getEyeArray(){ return eyeArray; }

    public int[] getHairArray() { return hairArray; }
}
