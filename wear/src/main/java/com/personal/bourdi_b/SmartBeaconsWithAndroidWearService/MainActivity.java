package com.personal.bourdi_b.SmartBeaconsWithAndroidWearService;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    private Button _bt_stop_start_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _bt_stop_start_service = (Button) findViewById(R.id.start_stop_service);

        if (isMyServiceRunning(DiscoverBeaconsService.class) == false) {
            startBeaconService();
        }
        _bt_stop_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, DiscoverBeaconsService.class));
            }
        });
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Toast.makeText(this, "is running", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }

    private void startBeaconService() {
        startService(new Intent(this, DiscoverBeaconsService.class));
    }
}
