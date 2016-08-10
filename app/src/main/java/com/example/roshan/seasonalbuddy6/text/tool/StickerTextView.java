package com.example.roshan.seasonalbuddy6.text.tool;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.example.roshan.seasonalbuddy6.text.tool.util.AutoResizeTextView;

/**
 * Created by cheungchingai on 6/15/15.
 */
public class StickerTextView extends StickerView{
    private AutoResizeTextView tv_main;
    private Typeface typeface;

    public StickerTextView(Context context) {
        super(context);
    }

    public StickerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StickerTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public View getMainView() {
        if(tv_main != null)
            return tv_main;

        tv_main = new AutoResizeTextView(getContext());
        //this.tv_main.setScaleType(ImageView.ScaleType.FIT_XY);
       // tv_main.setTextSize(22);
      //tv_main.setTextColor(Color.WHITE);
       tv_main.setGravity(Gravity.CENTER);
      //  tv_main.setTextSize(400);
        //tv_main.setShadowLayer(4, 0, 0, Color.BLACK);
        tv_main.setMaxLines(3);
        LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT
        );
        params.gravity = Gravity.CENTER;
        tv_main.setLayoutParams(params);
        if(getImageViewFlip()!=null)
            getImageViewFlip().setVisibility(View.GONE);
        return tv_main;
    }

    public void setText(String text){
        if(tv_main!=null)
            tv_main.setText(text);
    }
    public void setsize(int s){
        if(tv_main!=null)
            tv_main.setTextSize(s);

    }

    public void setcolor(int color){
        if(tv_main!=null)
        tv_main.setTextColor(color);
    }

    public String getText(){
        if(tv_main!=null)
            return tv_main.getText().toString();

        return null;
    }
    public void setMin(float si){
        if(tv_main!= null)
            tv_main.setMinSize(si);

    }

    public void setTypeface(Typeface typeface) {
        if(tv_main!=null)
            tv_main.setTFace(typeface);
    }

    public void setAutoMin(float m){
        if(tv_main!=null)
            tv_main.setMinTextSize(m);
    }


    public static float pixelsToSp(Context context, float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px/scaledDensity;
    }

    @Override
    protected void onScaling(boolean scaleUp) {
        super.onScaling(scaleUp);
    }



}
