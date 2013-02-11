package com.klusman.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

	private SensorManager sensorManager;
	
	private Sensor proximitySensor;
	private Sensor geomagneticSensor;
	private Sensor accelerometerSensor;
	private Sensor temperatureSensor;
	private Sensor lightSensor;
	
	private TextView distance;
	private TextView gm_x;
	private TextView gm_y;
	private TextView gm_z;
	private TextView acc_x;
	private TextView acc_y;
	private TextView acc_z;
	private TextView temp;
	private TextView light;
	
	
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        setContentView(R.layout.activity_main);
        
        distance = (TextView) findViewById(R.id.Prox);
        gm_x = (TextView) findViewById(R.id.GeoX);
        gm_y = (TextView) findViewById(R.id.GeoY);
        gm_z = (TextView) findViewById(R.id.GeoZ);
        acc_x = (TextView) findViewById(R.id.AccX);
        acc_y = (TextView) findViewById(R.id.AccY);
        acc_z = (TextView) findViewById(R.id.AccZ);
        temp = (TextView) findViewById(R.id.Temp);
        light = (TextView) findViewById(R.id.Light);
        
        
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        geomagneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        
        /////////// PROX
        if (proximitySensor == null)
        {
        	distance.setText("unavailable");
        }
        else
        {
        	sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        	Log.i("SENSOR", "prox");
        }
        
        ///////////  GEO 
        if(geomagneticSensor == null)
        {
        	gm_x.setText("unavailable");
        	gm_y.setText("unavailable");
        	gm_z.setText("unavailable");
        }
        else
        {
        	sensorManager.registerListener(this, geomagneticSensor, SensorManager.SENSOR_DELAY_NORMAL);
        	Log.i("SENSOR", "geo");
        	gm_x.setText("TEST TEST");
        }
        
        ///////////  ACC 
        if(accelerometerSensor == null)
        {
        	acc_x.setText("unavailable");
        	acc_y.setText("unavailable");
        	acc_z.setText("unavailable");
        }
        else
        {
        	sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        	Log.i("SENSOR", "acc");
        }
        
        ///////////  TEMP 
        if(temperatureSensor == null)
        {
        	temp.setText("unavailable");
        }
        else
        {
        	sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        	Log.i("SENSOR", "temp");
        }
        
        
        ///////////  LIGHT 
        if(lightSensor == null)
        {
        	light.setText("unavailable");
        }
        else
        {
        	sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        	Log.i("SENSOR", "light");
        }
             
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    } // END onCreateOptionsMenu

    
    
    
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
		
	} // END onAccuracyChanged

	
	
	
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor == proximitySensor)
		{
			distance.setText(String.valueOf(event.values[0]));
		}
		
		else if(event.sensor == geomagneticSensor)
		{
			
			gm_x.setText(String.valueOf(event.values[0]));
			gm_y.setText(String.valueOf(event.values[1]));
			gm_z.setText(String.valueOf(event.values[2]));
		}
		
		else if(event.sensor == accelerometerSensor)
		{
			acc_x.setText(String.valueOf(event.values[0]));
			acc_y.setText(String.valueOf(event.values[1]));
			acc_z.setText(String.valueOf(event.values[2]));
		}
		
		else if(event.sensor == temperatureSensor)
		{
			temp.setText(String.valueOf(event.values[0]));
		}
		
		else if(event.sensor == lightSensor)
		{
			light.setText(String.valueOf(event.values[0]));
		}
		

		
	}// END onSensorChanged

	
	@Override
	protected void onPause() {
		
		super.onPause();
		sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_FASTEST);
	}

	
	
	@Override
	protected void onResume() {
		
		super.onResume();
		sensorManager.unregisterListener(this);
	}
    
	
	
	
	
}
