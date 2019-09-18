package ece.course.eesm5060_lab2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;

public class AccelerometerSensor implements SensorEventListener {
    public final static String TAG_VALUE_DX = "tagValueDx";
    public final static String TAG_VALUE_DY = "tagValueDy";
    public final static String TAG_VALUE_DZ = "tagValueDz";

    private boolean mIsStarted = false;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Handler mHandler;

    public AccelerometerSensor(Context context, Handler handler) {
        mHandler = handler;
        mSensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;
        float dx = sensorEvent.values[0];
        float dy = sensorEvent.values[1];
        float dz = sensorEvent.values[2];
        if (mHandler != null) {
            Message message = mHandler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putFloat(TAG_VALUE_DX, dx);
            bundle.putFloat(TAG_VALUE_DY, dy);
            bundle.putFloat(TAG_VALUE_DZ, dz);
            message.setData(bundle);
            mHandler.sendMessage(message);
        }
    }

    public void startListening() {
        if (mIsStarted)
            return;
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
        mIsStarted = true;
    }
    public void stopListening() {
        if (!mIsStarted)
            return;
        mSensorManager.unregisterListener(this);
        mIsStarted = false;
    }
}
