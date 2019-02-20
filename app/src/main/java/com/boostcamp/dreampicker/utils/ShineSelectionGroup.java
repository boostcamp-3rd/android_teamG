package com.boostcamp.dreampicker.utils;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.boostcamp.dreampicker.R;
import com.sackcentury.shinebuttonlib.ShineButton;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import static androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID;

public class ShineSelectionGroup extends SelectionGroup {
    private ShineButton selector;

    public ShineSelectionGroup(Context context) {
        super(context);
    }

    public ShineSelectionGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShineSelectionGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void initSelector() {
        selector = new ShineButton(getContext());
        final int size = dp2px(DEFAULT_SIZE);
        final LayoutParams params = new LayoutParams(size, size);

        params.startToStart = PARENT_ID;
        params.endToEnd = PARENT_ID;
        params.topToTop = PARENT_ID;
        params.bottomToBottom = PARENT_ID;

        selector.setLayoutParams(params);

        selector.setClickable(false);
        selector.setElevation(5f);
        selector.setAllowRandomColor(true);
        selector.setBigShineColor(Color.parseColor("#66FFFF"));
        selector.setBtnColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
        selector.setBtnFillColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
        selector.setClickAnimDuration(200);
        selector.enableFlashing(false);
        selector.setAnimDuration(1500);
        selector.setShineCount(8);
        selector.setShineTurnAngle(10);
        selector.setShapeResource(R.raw.ic_finger);
        selector.setSmallShineColor(Color.parseColor("#CC9999"));
        selector.setSmallShineOffAngle(20);
        selector.setId(SELECTOR);
        super.selector = selector;
        addView(selector, getIndex());
    }

    @Override
    protected void dropAnimation(int position) {
        selector.startAnimation(new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                final ConstraintLayout.LayoutParams params =
                        (ConstraintLayout.LayoutParams) selector.getLayoutParams();

                if (position == NONE) {
                    params.startToStart = PARENT_ID;
                    params.endToEnd = PARENT_ID;
                } else if (position == LEFT) {
                    params.startToStart = PARENT_ID;
                    params.endToEnd = GUIDELINE;
                } else if (position == RIGHT) {
                    params.startToStart = GUIDELINE;
                    params.endToEnd = PARENT_ID;
                }
                selector.setLayoutParams(params);
                selector.showAnim();
            }
        });
    }
}
