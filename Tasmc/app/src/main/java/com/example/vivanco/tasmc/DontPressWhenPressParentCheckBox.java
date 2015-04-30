package com.example.vivanco.tasmc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by VIVANCO on 21/04/2015.
 */
public class DontPressWhenPressParentCheckBox extends CheckBox {
    public DontPressWhenPressParentCheckBox(Context context) {
        super(context);
    }

    public DontPressWhenPressParentCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DontPressWhenPressParentCheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setPressed(boolean pressed) {
        if (pressed && getParent() instanceof View && ((View) getParent()).isPressed()) {
            return;
        }
        super.setPressed(pressed);
    }
}
