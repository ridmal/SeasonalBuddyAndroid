package com.example.roshan.seasonalbuddy6.mainpage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import com.example.roshan.seasonalbuddy6.R;

import java.io.InputStream;

/**
 * Created by Shaki on 2/22/2016.
 */
public class GifView extends View{
    private InputStream gifInputStream;
    private Movie gifMovie;
    private int movieWidth, movieHeight;
    private  long movieDuration;
    private long movieStart;

    public GifView(Context context) {
        super(context);
        init(context);
    }

    public GifView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GifView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        setFocusable(true);
//        gifInputStream = context.getResources().openRawResource(+R.drawable.butterfly);
        gifInputStream = context.getResources().openRawResource(+R.drawable.snow);
        gifMovie = Movie.decodeStream(gifInputStream);
        movieWidth = gifMovie.width();
        movieHeight = gifMovie.height();
        movieDuration = gifMovie.duration();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        setMeasuredDimension(movieWidth, movieHeight);
    }

    public int getMovieWidth(){
        return movieWidth;
    }
    public int getMovieHeight(){
        return movieHeight;
    }
    public long getMovieDuration(){
        return movieDuration;
    }

    @Override
    protected void onDraw(Canvas canvas){
        long now = SystemClock.uptimeMillis();
        if(movieStart==0){
            movieStart = now;
        }
        if(gifMovie != null){
            int dur = gifMovie.duration();
            if(dur==0){
                dur =1000;
            }
            int relTime = (int)((now - movieStart)%dur);
            gifMovie.setTime(relTime);
            gifMovie.draw(canvas, 0,0);
            invalidate();
        }

    }
}//End of Main Class
