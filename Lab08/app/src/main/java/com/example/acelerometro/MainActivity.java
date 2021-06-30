package com.example.acelerometro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor sensor;
    TextView aX, aY, aZ;
    final float alpha = (float) 0.8;
    SensorEventListener sensorEventListenerA;
    TextView acelerometro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        //sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        //sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        acelerometro = (TextView) findViewById(R.id.Acelerometro);
        aX = (TextView) findViewById(R.id.Ax);
        aY = (TextView) findViewById(R.id.Ay);
        aZ = (TextView) findViewById(R.id.Az);

        if(sensor == null){
            finish();
        }

        sensorEventListenerA = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = Math.round(event.values[SensorManager.DATA_X]);
                //float x = (Math.round(event.values[0]));
                ///float y = (Math.round(event.values[1]));
                float y =  Math.round(event.values[SensorManager.DATA_Y]);
                //float z = (Math.round(event.values[2]));
                float z =  Math.round(event.values[SensorManager.DATA_Z]);
                aX.setText("X: " + x);
                aY.setText("y: " + y);
                aZ.setText("Z: " + z);


                if(x > 2){
                    getWindow().getDecorView().setBackgroundColor(Color.GRAY);
                    acelerometro.setTextColor(Color.WHITE);
                    aX.setTextColor(Color.WHITE);
                    aZ.setTextColor(Color.WHITE);
                    aY.setTextColor(Color.WHITE);
                    //sleep();
                }
                if(y > 2){
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                    acelerometro.setTextColor(Color.WHITE);
                    aX.setTextColor(Color.WHITE);
                    aZ.setTextColor(Color.WHITE);
                    aY.setTextColor(Color.WHITE);
                    //sleep();
                }
                if(z > 2){
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                    acelerometro.setTextColor(Color.WHITE);
                    aX.setTextColor(Color.WHITE);
                    aZ.setTextColor(Color.WHITE);
                    aY.setTextColor(Color.WHITE);
                    //sleep();
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }
    private void sleep(){
        try {
            Thread.sleep(5000);
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            acelerometro.setTextColor(Color.BLACK);
            aX.setTextColor(Color.BLACK);
            aZ.setTextColor(Color.BLACK);
            aY.setTextColor(Color.BLACK);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void start(){
        sensorManager.registerListener(sensorEventListenerA,sensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void stop(){
        sensorManager.unregisterListener(sensorEventListenerA);
    }

    @Override
    protected void onPause() {
        stop();
        super.onPause();
    }

    @Override
    protected void onResume() {
        start();
        super.onResume();
    }
}