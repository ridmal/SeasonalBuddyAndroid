package com.guna.libcolorpicker;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class ColorPickerOval extends Dialog {

    private OnColorChangedListener mListener;
    private int mInitialColor;
    private static String mKey;

    public ColorPickerOval(Context context, OnColorChangedListener listener, String key, int initialColor) {
        super(context);
        mKey = key;
        mListener = listener;
        mInitialColor = initialColor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnColorChangedListener l = new OnColorChangedListener() {
            public void colorChanged(String key, int color) {
                mListener.colorChanged(key, color);
                dismiss();
            }
        };
//        setContentView(new ColorPickerView(getContext(), l, mInitialColor));
        setContentView(R.layout.picker_oval);
        final ColorPickerOvalView view = (ColorPickerOvalView) findViewById(R.id.viewOval);
        view.init(l, mInitialColor, mKey);

        final EditText etHexColor = (EditText) findViewById(R.id.etHexColor);
        final EditText etR = (EditText) findViewById(R.id.etR);
        final EditText etG = (EditText) findViewById(R.id.etG);
        final EditText etB = (EditText) findViewById(R.id.etB);
        etHexColor.setText("#");
        Selection.setSelection(etHexColor.getText(), etHexColor.getText().toString().length());

        etHexColor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().startsWith("#")) {
                    etHexColor.setText("#");
                    Selection.setSelection(etHexColor.getText(), etHexColor.getText().toString().length());
                }
                if (s.toString().length() == 7 || s.toString().length() == 9) {
                    Log.v("App", etHexColor.getText().toString());
                    view.setColor(Color.parseColor(etHexColor.getText().toString()));
                }
            }
        });

        final int[] R = {0};
        final int[] G = {0};
        final int[] B = {0};
        etR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    R[0] = Integer.parseInt(s.toString());
                    if (s.toString().startsWith("0")) {
                        etR.setText(String.format("%d", R[0]));
                        Selection.setSelection(etR.getText(), etR.getText().toString().length());
                    }
                }
                if (R[0] > 255) {
                    R[0] = 255;
                    etR.setText("255");
                    Selection.setSelection(etR.getText(), etR.getText().toString().length());
                }
                view.setColor(Color.rgb(R[0], G[0], B[0]));
            }
        });

        etG.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    G[0] = Integer.parseInt(s.toString());
                    if (s.toString().startsWith("0")) {
                        etG.setText(String.format("%d", G[0]));
                        Selection.setSelection(etG.getText(), etG.getText().toString().length());
                    }
                }
                if (G[0] > 255) {
                    G[0] = 255;
                    etG.setText("255");
                    Selection.setSelection(etG.getText(), etG.getText().toString().length());
                }
                view.setColor(Color.rgb(R[0], G[0], B[0]));
            }
        });

        etB.addTextChangedListener(new TextWatcher() {
                                       @Override
                                       public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                       }

                                       @Override
                                       public void onTextChanged(CharSequence s, int start, int before, int count) {

                                       }

                                       @Override
                                       public void afterTextChanged(Editable s) {
                                           if (s.toString().length() > 0) {
                                               B[0] = Integer.parseInt(s.toString());
                                               if (s.toString().startsWith("0")) {
                                                   etB.setText(String.format("%d", B[0]));
                                                   Selection.setSelection(etB.getText(), etB.getText().toString().length());
                                               }
                                           }
                                           if (B[0] > 255) {
                                               B[0] = 255;
                                               etB.setText("255");
                                               Selection.setSelection(etB.getText(), etB.getText().toString().length());
                                           }
                                           view.setColor(Color.rgb(R[0], G[0], B[0]));
                                       }
                                   }

        );

        setTitle("Pick a Color");
    }
}
