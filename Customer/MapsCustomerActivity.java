package com.example.karchunkan.fyp.Customer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.karchunkan.fyp.API.GetDirectionsData;
import com.example.karchunkan.fyp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;


public class MapsCustomerActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMarkerDragListener
{

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    int PROXIMITY_RADIUS = 10000;
    double latitude, longitude;
    double end_latitude, end_longitude;
    double[][] waypoints=new double[2][2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

//        latitude=getIntent().getDoubleExtra("driverGpsX",0);
//        longitude=getIntent().getDoubleExtra("driverGpsX",0);
//        end_latitude=getIntent().getDoubleExtra("custGpsX",0);
//        end_longitude=getIntent().getDoubleExtra("custGpsY",0);
        LatLng ll= getIntent().getParcelableExtra("driverGps");
        latitude=ll.latitude;
        longitude=ll.longitude;
        ll=getIntent().getParcelableExtra("custGps");
        end_latitude=ll.latitude;
        end_longitude=ll.longitude;

        Log.d("lat",""+latitude);

        Object[] objectArray = (Object[]) getIntent().getExtras().getSerializable("waypoints");
        if(objectArray!=null){
            waypoints = new double[objectArray.length][2];
            for(int i=0;i<objectArray.length;i++){
                waypoints[i]=(double[]) objectArray[i];
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        //Check if Google Play Services Available or not
        if (!CheckGooglePlayServices()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
            finish();
        }
        else {
            Log.d("onCreate","Google Play Services available.");
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Initialize Google Play Services
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        mMap.setOnMarkerDragListener(this);
        mMap.setOnMarkerClickListener(this);

//        mMap = googleMap;
//
        //testing data, use database data when us;
//        double start_Lat=22.298112,start_lon=114.236966,end_Lat=22.2921837,end_lon=114.238738; //start=driverGPS , end=customerGPS
//        waypoints[0][0]= 22.2964529;
//        waypoints[0][1]= 114.2386977;
//        waypoints[1][0]= 22.2944669;
//        waypoints[1][1]= 114.2401341;

        String waypoint="";

        for(int i=0;i<waypoints.length;i++) {
            waypoint+= waypoints[i][0]+"%2C"+waypoints[i][1];
            if(waypoints.length>1&&i!=waypoints.length-1){
                waypoint+="%7C";
            }
        }
        Log.d("waypoint", waypoint);
//        latitude=start_Lat;
//        longitude=start_lon;
//        end_latitude=end_Lat;
//        end_longitude=end_lon;



        Object dataTransfer[] = new Object[3];
//                String url = getDirectionsUrl(start_Lat,start_lon,end_Lat,end_lon,waypoint);
        String url="https://www.google.com";
        String url_test="https://maps.googleapis.com/maps/api/directions/json?"+
                "origin="+latitude+","+longitude+
                "&destination="+end_latitude+","+end_longitude+
                "&waypoints=optimize:true|"+waypoint+
                "&key=";
        Log.d("url", url_test);
        //Toast.makeText(MapsActivity.this, "Url: " + url, Toast.LENGTH_LONG).show();
        GetDirectionsData getDirectionsData = new GetDirectionsData();
        dataTransfer[0] = mMap;
        dataTransfer[1] = url;
        dataTransfer[2] = new LatLng(end_latitude, end_longitude);
        getDirectionsData.execute(dataTransfer);

        LatLng latLng = new LatLng(latitude, longitude);
        CameraPosition cameraPosition= new CameraPosition.Builder().target(latLng).zoom(15).build();
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }



    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

//    public void onClick(View v)
//    {
//        Object dataTransfer[] = new Object[2];
//        //GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
//
//
//        switch(v.getId()) {
//            case R.id.B_search: {
//                EditText tf_location = (EditText) findViewById(R.id.TF_location);
//                String location = tf_location.getText().toString();
//                List<Address> addressList = null;
//                MarkerOptions markerOptions = new MarkerOptions();
//                Log.d("location = ", location);
//
//                if (!location.equals("")) {
//                    Geocoder geocoder = new Geocoder(this);
//                    try {
//                        addressList = geocoder.getFromLocationName(location, 5);
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    if (addressList != null) {
//                        for (int i = 0; i < addressList.size(); i++) {
//                            Address myAddress = addressList.get(i);
//                            LatLng latLng = new LatLng(myAddress.getLatitude(), myAddress.getLongitude());
//                            markerOptions.position(latLng);
//                            mMap.addMarker(markerOptions);
//                            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//                        }
//                    }
//
//                }
//            }
//            break;
//            case R.id.B_to:
//                //testing data, use database data when use
//                double[][] waypoints=new double[2][2];
//                double start_Lat=22.298112,start_lon=114.236966,end_Lat=22.2921837,end_lon=114.238738; //start=driverGPS , end=customerGPS
//                waypoints[0][0]= 22.2964529;
//                waypoints[0][1]= 114.2386977;
//                waypoints[1][0]= 22.2944669;
//                waypoints[1][1]= 114.2401341;
//                String waypoint="";
//
//                for(int i=0;i<waypoints.length;i++) {
//                    waypoint+= waypoints[i][0]+"%2C"+waypoints[i][1];
//                    if(waypoints.length>1&&i!=waypoints.length-1){
//                        waypoint+="%7C";
//                    }
//                }
//                latitude=start_Lat;
//                longitude=start_lon;
//                end_latitude=end_Lat;
//                end_longitude=end_lon;
//
//                LatLng latLng = new LatLng(latitude, longitude);
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
//
//
//                dataTransfer = new Object[3];
////                String url = getDirectionsUrl(start_Lat,start_lon,end_Lat,end_lon,waypoint);
//                String url="https://www.google.com";
//                //Toast.makeText(MapsActivity.this, "Url: " + url, Toast.LENGTH_LONG).show();
//                GetDirectionsData getDirectionsData = new GetDirectionsData();
//                dataTransfer[0] = mMap;
//                dataTransfer[1] = url;
//                dataTransfer[2] = new LatLng(end_latitude, end_longitude);
//                getDirectionsData.execute(dataTransfer);
//
//                //
////                dataTransfer = new Object[3];
////                String url = getDirectionsUrl();
////                //Toast.makeText(MapsActivity.this, "Url: " + url, Toast.LENGTH_LONG).show();
////                GetDirectionsData getDirectionsData = new GetDirectionsData();
////                dataTransfer[0] = mMap;
////                dataTransfer[1] = url;
////                dataTransfer[2] = new LatLng(end_latitude, end_longitude);
////                getDirectionsData.execute(dataTransfer);
//                break;
//
//
//        }
//    }
//
//    private String getDirectionsUrl(double lat,double lon,double end_lat,double end_lon,String waypoints)
//    {
//        StringBuilder googleDirectionsUrl = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
//        googleDirectionsUrl.append("origin="+lat+","+lon);
//        googleDirectionsUrl.append("&destination="+end_lat+","+end_lon);
//        googleDirectionsUrl.append("&waypoints=optimize:true|"+waypoints);
//        googleDirectionsUrl.append("&key="+"");
//        Log.d("url_with4", googleDirectionsUrl.toString());
//        return googleDirectionsUrl.toString();
//    }
//
//    private String getDirectionsUrl()
//    {
//        latitude=22.2964529;
//        longitude=114.2386984;
//        end_latitude=22.2981119;
//        end_longitude=114.2369657;
//        StringBuilder googleDirectionsUrl = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
//        googleDirectionsUrl.append("origin="+latitude+","+longitude);
//        googleDirectionsUrl.append("&destination="+end_latitude+","+end_longitude);
//        googleDirectionsUrl.append("&key="+"");
//        Log.d("url", googleDirectionsUrl.toString());
//        return googleDirectionsUrl.toString();
//    }

//    private String getUrl(double latitude, double longitude, String nearbyPlace)
//    {
//        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
//        googlePlacesUrl.append("location=" + latitude + "," + longitude);
//        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
//        googlePlacesUrl.append("&type=" + nearbyPlace);
//        googlePlacesUrl.append("&sensor=true");
//        googlePlacesUrl.append("&key=" + "");
//        Log.d("getUrl", googlePlacesUrl.toString());
//        return (googlePlacesUrl.toString());
//    }




    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }



    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
//        Log.d("onLocationChanged", "entered");
//
//        mLastLocation = location;
//        if (mCurrLocationMarker != null) {
//            mCurrLocationMarker.remove();
//        }
//
//        latitude = location.getLatitude();
//        longitude = location.getLongitude();
//
//
//        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.draggable(true);
//        markerOptions.title("Current Position");
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//        mCurrLocationMarker = mMap.addMarker(markerOptions);
//
//        //move map camera
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
//
//
//        Toast.makeText(MapsActivity.this,"Your Current Location", Toast.LENGTH_LONG).show();
//
//
//        //stop location updates
//        if (mGoogleApiClient != null) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//            Log.d("onLocationChanged", "Removing Location Updates");
//        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
//        marker.setDraggable(true);
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
//        end_latitude = marker.getPosition().latitude;
//        end_longitude =  marker.getPosition().longitude;
//
//        Log.d("end_lat",""+end_latitude);
//        Log.d("end_lng",""+end_longitude);
    }
}
