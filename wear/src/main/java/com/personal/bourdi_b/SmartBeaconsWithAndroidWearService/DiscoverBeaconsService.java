package com.personal.bourdi_b.SmartBeaconsWithAndroidWearService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.List;

import eu.smartbeacon.sdk.core.SBBeacon;
import eu.smartbeacon.sdk.core.SBLocationManager;
import eu.smartbeacon.sdk.core.SBLocationManagerListener;
import eu.smartbeacon.sdk.utils.SBLogger;

public class DiscoverBeaconsService extends Service implements SBLocationManagerListener {

    private boolean _isInitialized = false;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeBeacons();
        Toast.makeText(this, "On create service", Toast.LENGTH_SHORT).show();
    }

    private void initializeBeacons() {
        if (_isInitialized == false) {
            _isInitialized = true;
            // disable logging message
            SBLogger.setSilentMode(true);

            SBLocationManager sbManager = SBLocationManager.getInstance(this);
            sbManager.addEntireSBRegion();
            sbManager.addBeaconLocationListener(this);
            sbManager.startMonitoringAllBeaconRegions();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initializeBeacons();
        Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
        return 0;
    }

    @Override
    public void onDestroy() {
        SBLocationManager.getInstance(this)
                .stopMonitoringAllBeaconRegions();
        super.onDestroy();
    }

    @Override
    public void onEnteredBeacons(List<SBBeacon> sbBeacons) {
        Toast.makeText(this, "Beacon entered !!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onExitedBeacons(List<SBBeacon> sbBeacons) {
        Toast.makeText(this, "Beacon exited !!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDiscoveredBeacons(List<SBBeacon> sbBeacons) {
        Toast.makeText(this, "Beacon disco !!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdatedProximity(SBBeacon sbBeacon, SBBeacon.Proximity proximity,
                                   SBBeacon.Proximity proximity2) {
        Toast.makeText(this, "Beacon update !!!", Toast.LENGTH_SHORT).show();
    }
}
