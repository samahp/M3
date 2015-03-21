package com.example.johntavious.shoppingwithfriends;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ViewSaleActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play
                        // services APK is not available.
    private User user;
    private double lat;
    private double lon;
    private String location;
    private DataController dc = new DataController(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sale);
        Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            user = dc.getUser(extras.getString("user"));
            location = extras.getString("location");
            if (location == null) {
                lat = extras.getDouble("lat");
                lon = extras.getDouble("lon");
            }
        }
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so
     * (i.e., the Google Play services APK is correctly installed)
     * and the map has not already been instantiated.
     * <p/>
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not
        // already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners
     * or move the camera. In this case, we add a marker at the Burj Khalifa
     * if no lat/long was retrieved when the sale was posted
     */
    private void setUpMap() {
        if (location == null) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon))
                    .title(getString(R.string.FoundItemGoogleMaps)));
        } else {
            //location of the Burj Khalifa in Dubai
            lat = 25.197170;
            lon = 55.2745;
            mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon))
                    .title(getString(R.string.Friend)
                            + getString(R.string.GoogleMapsNoLocation)));
        }
        mMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(new LatLng(lat, lon), 18));
    }
}