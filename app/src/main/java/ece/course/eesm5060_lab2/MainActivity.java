package ece.course.eesm5060_lab2;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static float MAX_GRAVITY = 9.82f;
    private DisplayView mDisplayView;
    private AccelerometerSensor mAccSensor;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDisplayView = findViewById(R.id.mDisplayView);
        mAccSensor = new AccelerometerSensor(this, new Handler(){
            public void handleMessage(Message msg) {
                float tmpX = msg.getData().getFloat(AccelerometerSensor.TAG_VALUE_DX);
                float tmpY = -msg.getData().getFloat(AccelerometerSensor.TAG_VALUE_DY);
                float tmpZ = msg.getData().getFloat(AccelerometerSensor.TAG_VALUE_DZ);
                TextView tvValueX = (TextView) findViewById(R.id.acc_x);
                TextView tvValueY = (TextView) findViewById(R.id.acc_y);
                TextView tvValueZ = (TextView) findViewById(R.id.acc_z);
                tvValueX.setText("X: " + tmpX);
                tvValueY.setText("Y: " + tmpY);
                tvValueZ.setText("Z: " + tmpZ);
                mDisplayView.setPtr(tmpX / MAX_GRAVITY, tmpY / MAX_GRAVITY);
            }
        });
    }

    public synchronized void onResume() {
        super.onResume();
        if (mAccSensor != null)
            mAccSensor.startListening();
    }

    public synchronized void onPause() {
        if (mAccSensor != null)
            mAccSensor.stopListening();
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuPtrBall:
                mDisplayView.setPtrType(DisplayView.TYPE_BALL);
                return true;
            case R.id.menuPtrSquare:
                mDisplayView.setPtrType(DisplayView.TYPE_SQUARE);
                return true;
            case R.id.menuPtrDiamond:
                mDisplayView.setPtrType(DisplayView.TYPE_DIAMOND);
                return true;
            case R.id.menuPtrArc:
                mDisplayView.setPtrType(DisplayView.TYPE_ARC);
                return true;
            case R.id.menuPtrRed:
                mDisplayView.setPtrColor(Color.RED);
                return true;
            case R.id.menuPtrBlue:
                mDisplayView.setPtrColor(Color.BLUE);
                return true;
            case R.id.menuPtrGreen:
                mDisplayView.setPtrColor(Color.GREEN);
                return true;
            case R.id.menuPtrWhite:
                mDisplayView.setPtrColor(Color.WHITE);
                return true;
        } // end switch
        return false;
    }
}
