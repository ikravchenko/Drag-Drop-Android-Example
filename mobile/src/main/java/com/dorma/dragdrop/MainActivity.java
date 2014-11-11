package com.dorma.dragdrop;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationSet;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnLongClick;
import butterknife.OnTouch;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.inputView)
    View inputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        inputView.post(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator slideUp = ObjectAnimator.ofFloat(inputView, View.TRANSLATION_Y, inputView.getHeight(), 0);
                final AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                animatorSet.setDuration(1000);
                animatorSet.play(slideUp);
                animatorSet.start();
            }
        });
        inputView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                Log.d("Dragging", event.toString());
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        v.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        v.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                        break;
                    case DragEvent.ACTION_DROP:
                        v.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
                        break;
                }
                return true;
            }
        });
    }

    @OnLongClick(R.id.target_view)
    public boolean onTargetSelected(View view) {
        view.startDrag(null, new View.DragShadowBuilder(view), null, 0);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
