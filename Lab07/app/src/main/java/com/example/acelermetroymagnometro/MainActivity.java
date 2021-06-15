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
    SensorEventListener sensorEventListener;
    int change = 0;
    TextView aX, aY, aZ, mX, mY, mZ;
    TextView acelerometro, magnetrometro;
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

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                synchronized (this) {
                    switch (event.sensor.getType()) {

                        // si se desea usar el accelerometer comentar el código del caso de Gravedad
                        // si se desea usar la gravedad comentar el código del caso acelerometro
                        /*case Sensor.TYPE_ACCELEROMETER:
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


                        case Sensor.TYPE_MAGNETIC_FIELD:
                            float a = Math.round(event.values[0]);
                            float b = Math.round(event.values[1]);
                            float c = Math.round(event.values[2]);
                            mX.setText("X: " + a);
                            mY.setText("Y: " + b);
                            mZ.setText("Z: " + c);

                        case Sensor.TYPE_GRAVITY:
                            acelerometro.setText("Gravedad");
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

                            if(x > 23){
                                getWindow().getDecorView().setBackgroundColor(Color.GRAY);
                            }else if(y > 23){
                                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                            }else if(y < -23){
                                getWindow().getDecorView().setBackgroundColor(Color.RED);
                            } else if(x < -23){
                                getWindow().getDecorView().setBackgroundColor(Color.MAGENTA);
                            }

                    }
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        start();
    }

    private void start(){
        sensorManager.registerListener(sensorEventListener,sensorAce,SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(sensorEventListener,sensorMag,SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void stop(){
        sensorManager.unregisterListener(sensorEventListener);
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