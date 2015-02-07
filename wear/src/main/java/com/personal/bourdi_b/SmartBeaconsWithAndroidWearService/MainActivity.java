package com.personal.bourdi_b.SmartBeaconsWithAndroidWearService;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bt_stop_start_service = (Button) findViewById(R.id.start_stop_service);
        if (isMyServiceRunning(DiscoverBeaconsService.class)) {
            bt_stop_start_service.setText(R.string.start_service);
        } else {
            startService(new Intent(MainActivity.this, DiscoverBeaconsService.class));
            bt_stop_start_service.setText(R.string.stop_service);
        }
        bt_stop_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMyServiceRunning(DiscoverBeaconsService.class)) {
                    stopService(new Intent(MainActivity.this, DiscoverBeaconsService.class));
                    bt_stop_start_service.setText(R.string.start_service);
                } else {
                    startService(new Intent(MainActivity.this, DiscoverBeaconsService.class));
                    bt_stop_start_service.setText(R.string.stop_service);
                }
            }
        });
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
