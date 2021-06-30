package com.example.acelermetroymagnometro;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor sensorAce, sensorMag, sensorGrav;
    SensorEventListener sensorEventListenerA, sensorEventListenerM;
    int change = 0;
    TextView aX, aY, aZ, mX, mY, mZ;
    TextView acelerometro, magnetrometro;
    float auxX = 0;
    float auxY = 0;
    float auxZ = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorAce = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMag = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorGrav = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        acelerometro = (TextView) findViewById(R.id.Acelerometro);
        magnetrometro = (TextView) findViewById(R.id.Magnenometro);
        aX = (TextView) findViewById(R.id.Ax);aY = (TextView) findViewById(R.id.Ay);aZ = (TextView) findViewById(R.id.Az);
        mX = (TextView) findViewById(R.id.Mx);mY = (TextView) findViewById(R.id.My);mZ = (TextView) findViewById(R.id.Mz);

        if(sensorAce == null){
            finish();
        }
        if (sensorMag== null)
            finish();
        if (sensorGrav == null){
            finish();
        }

        sensorEventListenerA = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                        // si se desea usar el accelerometer comentar el código del caso de Gravedad
                        // si se desea usar la gravedad comentar el código del caso acelerometro
                        /*
                            float x = (Math.round(event.values[0]));
                            float y = (Math.round(event.values[1]));
                            float z = (Math.round(event.values[2]));
                            aX.setText("X: " + x);
                            aY.setText("Y: " + y);
                            aZ.setText("Z: " + z);
                            acelerometro.setTextColor(Color.WHITE);
                            magnetrometro.setTextColor(Color.WHITE);
                            aX.setTextColor(Color.WHITE);
                            aZ.setTextColor(Color.WHITE);
                            aY.setTextColor(Color.WHITE);
                            mX.setTextColor(Color.WHITE);
                            mZ.setTextColor(Color.WHITE);
                            mY.setTextColor(Color.WHITE);
                            if (x == -9 ){

                                getWindow().getDecorView().setBackgroundColor(Color.BLUE);

                            }else if(x == 9){

                                getWindow().getDecorView().setBackgroundColor(Color.RED);
                            }else if(x < 3 &&   x > -3 && y > 0){
                                getWindow().getDecorView().setBackgroundColor(Color.GRAY);
                            }else if (y == -10 && x > -3 && x < 3){
                                getWindow().getDecorView().setBackgroundColor(Color.MAGENTA);
                            }

                            */

                            acelerometro.setText("Gravedad");
                            float x = (Math.round(event.values[0]));
                            float y = (Math.round(event.values[1]));
                            float z = (Math.round(event.values[2]));
                            aX.setText("X: " + x);
                            aY.setText("Y: " + y);
                            aZ.setText("Z: " + z);

                            if (change == 0){
                                float auxX = x;
                                float auxY = y;
                                float auxZ = z;
                                System.out.println("/**************************" + x+ " " + y+" "+z);
                                change++;
                            }
                            acelerometro.setTextColor(Color.WHITE);
                            magnetrometro.setTextColor(Color.WHITE);
                            aX.setTextColor(Color.WHITE);
                            aZ.setTextColor(Color.WHITE);
                            aY.setTextColor(Color.WHITE);
                            mX.setTextColor(Color.WHITE);
                            mZ.setTextColor(Color.WHITE);
                            mY.setTextColor(Color.WHITE);

                            if(x < 2 && x >-2 && y > 5){
                                getWindow().getDecorView().setBackgroundColor(Color.GRAY);
                            }else if(y > -1 && y < 1 && x < -5){
                                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                            }else if(y > -1 && y < 1 && x > 5){
                                getWindow().getDecorView().setBackgroundColor(Color.RED);
                            } else if(x < 2 && x >-2 && y < -5){
                                getWindow().getDecorView().setBackgroundColor(Color.MAGENTA);
                            }


            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        sensorEventListenerM = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float a = Math.round(event.values[0]);
                float b = Math.round(event.values[1]);
                float c = Math.round(event.values[2]);
                mX.setText("X: " + a);
                mY.setText("Y: " + b);
                mZ.setText("Z: " + c);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        start();
    }

    private void start(){
        sensorManager.registerListener(sensorEventListenerA,sensorAce,SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(sensorEventListenerA,sensorMag,SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(sensorEventListenerM,sensorGrav,SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void stop(){
        sensorManager.unregisterListener(sensorEventListenerA);
        sensorManager.unregisterListener(sensorEventListenerM);
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