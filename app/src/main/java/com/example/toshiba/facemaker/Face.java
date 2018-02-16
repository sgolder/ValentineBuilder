package com.example.toshiba.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import java.util.Random;
import static android.graphics.Color.rgb;

/**
 * Face class draws a face with eyes, a mouth
 * and a variety of hairstyles on the SurfaceView
 *
 * @Author Sarah Golder on 2/12/2018.
 */

public class Face extends SurfaceView {

    private int skinColor;
    private int eyeColor;
    private int hairColor;
    //0:girl cut, 1:frosted tips, 2:comb over
    private int hairStyle;
    //0:red, 1:green, 2:blue
    private int[] skinArray = new int[3];
    private int[] eyeArray = new int[3];
    private int[] hairArray = new int[3];
    private Paint skin = new Paint();
    private Paint eyes = new Paint();
    private Paint hair = new Paint();

    /**
     * Face extends surface view to draw face
     */
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
    /**
     * Pt class for making paths on Face SurfaceView
     */
    private class Pt{
        float x, y;
        Pt(float _x, float _y) {
            x = _x;
            y = _y;
        }
    }

    /**
     * Tells SurfaceView to draw Face
     */
    private void generalInit() {
        setWillNotDraw(false);
    }

    /**
     * Draws face, eyes, and mouth on SurfaceView
     */
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
            drawCombOver(canvas, xCenter, yCenter);
        }

        //Paint face
        for(int i = 0; i<100; i = i+10){
            canvas.drawCircle(xCenter, yCenter+i, 400.0f, skin);
        }
        //Paint eyes
        canvas.drawCircle(xCenter - 175, yCenter, 170, eyes);
        canvas.drawCircle(xCenter + 175, yCenter, 170, eyes);
        myPaint.setColor(Color.BLACK);
        canvas.drawCircle(xCenter - 175, yCenter, 120, myPaint);
        canvas.drawCircle(xCenter + 175, yCenter, 120, myPaint);

        //Mouth
        canvas.drawArc(xCenter-100,yCenter+100, xCenter+100,
                yCenter+400, 0, 180, true, myPaint);
    }

    /**
     * Creates random values for red, green, and blue
     * for each facial feature
     */
    public void randomize() {
        Log.i("FaceMaker", "randomize");
        Random rand = new Random();
        for(int i = 0; i<3; i++){
            skinArray[i] = rand.nextInt(255);
            eyeArray[i] = rand.nextInt(255);
            hairArray[i] = rand.nextInt(255);
        }
        hairStyle = rand.nextInt(3);
        rgbToInt();
    }

    /**
     * Draws the girl hairstyle on the SurfaceView
     */
    public void drawGirlCut(Canvas canvas, int xCenter, int yCenter){
        hair.setStrokeWidth(5.0f);
        canvas.drawCircle(xCenter, yCenter, 500, hair);
        canvas.drawRect(xCenter-500.0f, yCenter, xCenter+500,
                    yCenter+700, hair);
    }

    /**
     * Draws the frosted tips hairstyle on SurfaceView
     */
    public void drawFrostedTips(Canvas canvas, int xCenter, int yCenter){
        canvas.drawRect(xCenter-400, yCenter-450, xCenter+400,
                yCenter, hair);
        /**
         External Citation
         Date: 13 February 2018
         Problem: How to create and draw a path on the canvas
         Resource:
         http://android-er.blogspot.nl/2011/08/drawpath-on-canvas.html
         Solution: I used the example code from this post.
         */
        Paint frosty = new Paint();
        frosty.setColor(Color.WHITE);
        frosty.setStrokeWidth(10.0f);
        frosty.setStyle(Paint.Style.FILL);

        Pt[] myPath = { new Pt(xCenter-400,yCenter-450),
                new Pt(xCenter-300, yCenter-500),
                new Pt(xCenter-200, yCenter-450),
                new Pt(xCenter-100, yCenter-500),
                new Pt(xCenter, yCenter-450),
                new Pt(xCenter+100, yCenter-500),
                new Pt(xCenter+200, yCenter-450),
                new Pt(xCenter+300, yCenter-500),
                new Pt(xCenter+400,yCenter-450),
        };

        Path path = new Path();
        path.moveTo(myPath[0].x, myPath[0].y);
        for( int i = 0; i < myPath.length; i++){
            Log.i("Path", myPath[i].x+" "+myPath[i].y);
            path.lineTo(myPath[i].x, myPath[i].y);
        }
        canvas.drawPath(path, frosty);
    }

    public void drawUniHorn(Canvas canvas, int xCenter, int yCenter){
        //Draw triangle outline
        //Stripes with the overlapping methods??
        Paint partay = new Paint();
        partay.setColor(Color.WHITE);
        partay.setStrokeWidth(10.0f);
        partay.setStyle(Paint.Style.FILL);

        Pt[] myPath = {new Pt(xCenter - 50, yCenter - 250),
                new Pt(xCenter + 300, yCenter-600),
                new Pt(xCenter + 50, yCenter - 250)};

        Path path = new Path();
        path.moveTo(myPath[0].x, myPath[0].y);
        for( int i = 0; i < myPath.length; i++){
            Log.i("Path", myPath[i].x+" "+myPath[i].y);
            path.lineTo(myPath[i].x, myPath[i].y);
        }
        canvas.drawPath(path, partay);
    }

    /**
     * Draws the comb over hairstyle on the canvas
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void drawCombOver(Canvas canvas, int xCenter, int yCenter) {

        Pt[] myPath = {new Pt(xCenter - 300, yCenter),
                new Pt(xCenter-400, yCenter - 200),
                new Pt(xCenter-250, yCenter-350),
                new Pt(xCenter, yCenter-200),
                new Pt(xCenter+250, yCenter-350),
                new Pt(xCenter+400, yCenter-200),
                new Pt(xCenter+300, yCenter)
        };
        Path path = new Path();
        path.moveTo(myPath[0].x, myPath[0].y);
        for( int i = 0; i < myPath.length; i++){
            Log.i("Path", myPath[i].x+" "+myPath[i].y);
            path.lineTo(myPath[i].x, myPath[i].y);
        }
        canvas.drawPath(path, hair);
        hair.setStyle(Paint.Style.STROKE);
        canvas.drawArc(xCenter-250, yCenter-410, xCenter+250,
                yCenter-190, 180, 360, false, hair);
        canvas.drawArc(xCenter-250, yCenter-420, xCenter+250,
                yCenter-200, 180, 360, false, hair);
        canvas.drawArc(xCenter-250, yCenter-430, xCenter+250,
                yCenter-200, 180, 360, false, hair);
        hair.setStyle(Paint.Style.FILL);
    }

    /**
     * Assigns color arrays to int colors for each facial feature
     */
    public void rgbToInt(){
        skinColor = rgb(skinArray[0], skinArray[1], skinArray[2]);
        eyeColor = rgb(eyeArray[0], eyeArray[1], eyeArray[2]);
        hairColor = rgb(hairArray[0], hairArray[1], hairArray[2]);
    }

    public void update(){
        //Redraw SurfaceView with any new changes
        invalidate();
    }

    //Getters and setters

    /**
     * Changes red, green, or blue value when user adjusts seekbar
     */
    public void setColorVal(int newValue, int color, String faceFeat){

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

    public void setHairStyle(int style){ hairStyle = style; }

    public int[] getSkinArray(){ return skinArray; }

    public int[] getEyeArray(){ return eyeArray; }

    public int[] getHairArray() { return hairArray; }

    public int getHairStyle() { return hairStyle; }
}
