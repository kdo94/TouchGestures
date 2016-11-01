package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    // Define a GestureDetector to listen to events on the ScrollView
    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singleTapTextView = (TextView) findViewById(R.id.singleTapTextView);
        doubleTapTextView = (TextView) findViewById(R.id.doubleTapTextView);
        longPressTextView = (TextView) findViewById(R.id.longPressTextView);
        scrollTextView = (TextView) findViewById(R.id.scrollTextView);
        flingTextView = (TextView) findViewById(R.id.flingTextView);

        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);
        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView) ;
        // Hooking up the gesture detector to our scroll view
        // 4 out of 5 gestures handled
        gestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);
        // Special case: double tap
        gestureDetector.setOnDoubleTapListener(this);

    }

    // Any touch event is now dispatched from the Activity to the View
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return gestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        singleTaps++;
        // Let's append ( add text to the TextView ) to our gesture log
        gesturesLogTextView.append("\nonSingleTapConfirmed touch event");
        singleTapTextView.setText(String.valueOf(singleTaps));
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        doubleTaps++;
        gesturesLogTextView.append("\nonDoubleTapConfirmed touch event");
        doubleTapTextView.setText(String.valueOf(doubleTaps));
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        scrolls++;
        gesturesLogTextView.append("\nonScroll: distanceX is " + v + " distanceY is " + v1);
        scrollTextView.setText(String.valueOf(scrolls));
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        longPresses++;
        gesturesLogTextView.append("\nonLongPress touch event");
        longPressTextView.setText(String.valueOf(longPresses));
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        flings++;
        gesturesLogTextView.append("\nonFling: velocityX is " + v + " velocityY is " + v1);
        flingTextView.setText(String.valueOf(flings));
        return true;
    }

    public void clearAll(View view){
        gesturesLogTextView.setText("");
        singleTaps = 0;
        doubleTaps = 0;
        longPresses = 0;
        scrolls = 0;
        flings = 0;
        singleTapTextView.setText("0");
        doubleTapTextView.setText("0");
        longPressTextView.setText("0");
        scrollTextView.setText("0");
        flingTextView.setText("0");

    }
}
