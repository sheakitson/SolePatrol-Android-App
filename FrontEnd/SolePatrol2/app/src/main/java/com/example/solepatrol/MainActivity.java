package com.example.solepatrol;
// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// This Android Model has been modified to fit the needs of the SolePatrol Application

/**The Android Volley documentation was used to create the networking requests to communicate with Spring
 Reference: https://developer.android.com/training/volley**/

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.solepatrol.ui.login.LoginActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// Implement OnMapReadyCallback.
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private CameraPosition cameraPosition;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient fusedLocationProviderClient;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng defaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location lastKnownLocation;

    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    private static int AUTOCOMPLETE_REQUEST_CODE = 1;
    List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
    // Set the fields to specify which types of place data to
    // return after the user has made a selection.

    //Button for the destination search bar
    private Button btnEnterDestination;

    //Button for accessing emergency contact page
    private ImageButton helpButton;
    private Button logoutButton;

    //Route based parameters
    private final int route1Traffic = 30;
    private final int route1Lighting = 30;

    private final int route2Traffic = 50;
    private final int route2Lighting = 50;

    private final int route3Traffic = 10;
    private final int route3Lighting = 10;

    private int overallScoreR1 = route1Traffic+route1Lighting;
    private int overallScoreR2 = route2Traffic+route2Lighting;
    private int overallScoreR3 = route3Traffic+route3Lighting;

    private JSONArray responseFromUrl;
    private String responseFromPoly;
    private ArrayList<String> allPolys = new ArrayList<String>();
    private ArrayList<Integer> overallScores = new ArrayList<Integer>();


    //The OnCreate method to instruct the program on what needs built
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check the saved state to see if one exists
        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }
        // Set the layout file as the content view.
        setContentView(R.layout.activity_main);

        //Create a fused location to derive the user location.
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        // Get a handle to the fragment and register the callback.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // If the places object is not initialised then initialise it.
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getString(R.string.api_key));
        }

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.getView().findViewById(R.id.autocomplete_fragment).setVisibility(View.GONE);

        //Button to call the AutocompleteSupportFragment
        btnEnterDestination = (Button) findViewById(R.id.btnEnterDestination);
        btnEnterDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Once a places has been selected return the Place ID, NAME, and LAT/LONG
                autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));
                // Set up a PlaceSelectionListener to handle the response.
                autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                    @Override
                    public void onPlaceSelected(@NonNull Place place) {
                    }

                    @Override
                    public void onError(@NonNull Status status) {
                        System.out.println(status.getStatus());
                    }
                });
                // Start the autocomplete intent.
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
                        .build(MainActivity.this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        });


        helpButton = (ImageButton) findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, EmergencyContactActivity.class);
                startActivity(intent);
                finish();
            }
        });

        logoutButton = (Button) findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                String url = "http://10.0.2.2:9192/user/logout";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("Response: ",response.toString());
                                finish();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error.Response", error.toString());
                        String msg = "Could not logout. Please try again.";
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                        Log.e("Error.Response: ",error.toString());
                    }
                });
                requestQueue.add(stringRequest);
            }
        });
    }


    /**
     * This method does the following:
     *      Adds a marker at the destination
     *      Returns Crime
     *      Returns Polylines
     *      Sets the min max and median score of a route
     *      Draws the Polylines
     *      Redraws only the selected route
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //Once the AUTOCOMPLETE_REQUEST_CODE has been clicked begin the following
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //Set up the place object initialised with the intent.
                Place place = Autocomplete.getPlaceFromIntent(data);
                //Add a marker at the destination selected in the AUTOCOMPLETE_REQUEST using the place as a descriptor the long/lat coordinates.
                LatLng destination = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
                map.addMarker(new MarkerOptions().position(destination).title("Start Position"));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(destination.latitude, destination.longitude), DEFAULT_ZOOM));
                //Initialise the requestQueue for HTTP requests.
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                //Use the url below this when not using google hosted simulators
                //String url = "http://localhost:9192/routes/"+lastKnownLocation.getLatitude()+ ","+lastKnownLocation.getLongitude()+","+destination.latitude +","+ destination.longitude;


                //Use the url below this when using google hosted simulators
                String urlCrime = "http://10.0.2.2:9192/routes/crimeScores/54.574487,-5.956214," + destination.latitude + "," + destination.longitude;
                //The requests below should be used when attempting to retrieve data from the database.
                //However for now we will use static data.
                    // String url2 = "http://10.0.2.2:9192/lighting/calculateTotal/54.574487,-5.956214," + destination.latitude + "," + destination.longitude;
                    // String url3 = "http://10.0.2.2:9192/traffic/calculateTotal/54.574487,-5.956214," + destination.latitude + "," + destination.longitude;

                //Create an array list to store the URLs for crime
                ArrayList<String> urls = new ArrayList<String>();
                //Create an array list to store the URLs for Polylines
                ArrayList<String> PolylineUrls = new ArrayList<String>();
                // Add the crime url to the array list defined above
                urls.add(urlCrime);

                //We only want to plot 3 routes in our app the min score, mac score and median score.
                for(int i = 0; i < 3; i++){
                    String urlPolyLine = "http://10.0.2.2:9192/routes/getPoly/"+i+",54.574487,-5.956214," + destination.latitude +"," + destination.longitude;
                    PolylineUrls.add(urlPolyLine);
                }
                //Create a method to get the data from the url.
                JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, urls.get(0), null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                // Display response
                                try {
                                    // Define the max min and median
                                    String max = "", min = "",median = "";
                                    // Map the response to the responseFromUrl object.
                                    responseFromUrl = response;
                                    // Based on the responseFromUrl calculate the overall scores which include traffic and lighting data.
                                    calculateOverallRouteScore();


                                    // Max value checking (A > B && A > C || B > A && B > C || C)
                                    if(overallScores.get(0) > overallScores.get(1) && overallScores.get(0) > overallScores.get(2)) {
                                        max = allPolys.get(0);
                                    } else if(overallScores.get(1) > overallScores.get(0) && overallScores.get(1) > overallScores.get(2)) {
                                        max = allPolys.get(1);
                                    } else {
                                        max = allPolys.get(2);
                                    }

                                    //Min value checking (A > B && A > C || B > A && B > C || C)
                                    if(overallScores.get(0) < overallScores.get(1) && overallScores.get(0) < overallScores.get(2)){
                                        min = allPolys.get(0);
                                    }else if (overallScores.get(1) < overallScores.get(0) && overallScores.get(1) < overallScores.get(2)){
                                        min = allPolys.get(1);
                                    }else {
                                        min = allPolys.get(2);
                                    }

                                    //Median value Checking (A != min && A != max || B != min && B != max)
                                    if(allPolys.get(0) != min && allPolys.get(0) != max){
                                        median = allPolys.get(0);
                                    }else if (allPolys.get(1) != min && allPolys.get(1) != max){
                                        median = allPolys.get(1);
                                    }else {
                                        median = allPolys.get(2);
                                    }
                                    // Create an array list for the points on the route
                                    ArrayList<LatLng> points = new ArrayList<LatLng>();
                                    // Route Min Decode
                                    List<Point> points1 = decode(min, 1E5);
                                    // Route Max Decode
                                    List<Point> points2 = decode(median, 1E5);
                                    // Route Median Decode
                                    List<Point> points3 = decode(max, 1E5);
                                    // Set the colours for the routes.
                                    int red = Color.RED;
                                    int green = Color.GREEN;
                                    int orange = Color.MAGENTA;

                                    // Draw the Orange PolyLine - Median Case
                                    PolylineOptions polyOptions2 = new PolylineOptions()
                                            .clickable(true)
                                            .color(orange);
                                    for(Point point: points2) {
                                        polyOptions2.add(new LatLng(point.getLat(), point.getLng()));
                                    }
                                    Polyline polyline2 = map.addPolyline( polyOptions2);

                                    // Draw the Red PolyLine - Worst Case
                                    PolylineOptions polyOptions3 = new PolylineOptions()
                                            .clickable(true)
                                            .color(red);
                                    for(Point point: points3) {
                                        polyOptions3.add(new LatLng(point.getLat(), point.getLng()));
                                    }

                                    Polyline polyline3 = map.addPolyline( polyOptions3);

                                    // Draw the Green PolyLine - Best Case
                                    PolylineOptions polyOptions1 = new PolylineOptions()
                                            .clickable(true)
                                            .color(green);
                                    for(Point point: points1) {
                                        polyOptions1.add(new LatLng(point.getLat(), point.getLng()));
                                        points.add(new LatLng(point.getLat(), point.getLng()));
                                    }
                                    Polyline polyline1 = map.addPolyline( polyOptions1);

                                    // Move the camera to the routes
                                    cameraRouteCentered(map,points);

                                    // Create a listener for the polyline
                                    map.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener()
                                    {

                                        @Override
                                        public void onPolylineClick(Polyline polyline)
                                        {
                                            // If clicked create a green route
                                            PolylineOptions polyOptions4 = new PolylineOptions()
                                                    .clickable(true)
                                                    .color(green);
                                            // Add co ordinates for to green route
                                            for(int i = 0; i <  polyline.getPoints().size();i++) {
                                                polyOptions4.add(polyline.getPoints().get(i));
                                            }

                                            //Remove the existsing polylines
                                            polyline1.remove();
                                            polyline2.remove();
                                            polyline3.remove();

                                            //Draw the new green route selected by the user
                                            polyline = map.addPolyline(polyOptions4);

                                            //Move the camera to the start position of the route
                                            map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(polyline.getPoints().get(0).latitude, polyline.getPoints().get(1).longitude)));
                                        }
                                    });

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("Error.Response", error.toString());
                            }
                        }
                );
                //Set a request for the route to be 1 minute if it fails double this value
                arrayRequest.setRetryPolicy(new DefaultRetryPolicy(60000,
                        3,
                        2));
                // Add the array request to the queue
                requestQueue.add(arrayRequest);

                //Create a new request for the polylines
                for (String jsonPolyUrl : PolylineUrls) {
                    StringRequest PolyRequest = new StringRequest(Request.Method.GET, jsonPolyUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // display response from requests and add to the polylines arraylist
                                    responseFromPoly = response;
                                    allPolys.add(responseFromPoly);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d("Error.Response", error.toString());
                                }
                            }
                    );

                    PolyRequest.setRetryPolicy(new DefaultRetryPolicy(1000,
                            3,
                            2));
                    // Add the array request to the queue
                    requestQueue.add(PolyRequest);

                }
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Method for calculating the overall route scores
     */
    ArrayList<Integer> scores = new ArrayList<Integer>();
    private void calculateOverallRouteScore() throws JSONException {
        // Add the three scores to the array list
        scores.add(overallScoreR1);
        scores.add(overallScoreR2);
        scores.add(overallScoreR3);

        for (int i = 0; i < 3; i++) {
            int value  = (int) responseFromUrl.get(i);
            value = value - scores.get(i);
            // Add the overall score to the array list
            overallScores.add(value);
            System.out.printf("Value of overall score: %d\n",value);
        }
    }


    /**
     * Create the on save instance method. This refers to state of the current KEY_CAMERA_POSITION and KEY_LOCATION
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (map != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, map.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, lastKnownLocation);
        }
        super.onSaveInstanceState(outState);
    }

    public void openLogin()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /** Manipulates the map when it's available.
     This callback is triggered when the map is ready to be used.*/

    /** Manipulates the map when it's available.
     * This callback is triggered when the map is ready to be used.
     */
    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        // Use a custom info window adapter to handle multiple lines of text in the
        // info window contents.
        this.map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Nullable
            @Override
            public View getInfoContents(@NonNull Marker marker) {
                return null;
            }

            @Override
            // Return null here, so that getInfoContents() is called next.
            public View getInfoWindow(Marker arg0) {
                return null;
            }
        });
        // Prompt the user for permission.
        getLocationPermission();
        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();
        // Get the current location of the device and set the position of the map.
        getDeviceLocation();

    }

    /**
     * Gets the current location of the device, and positions the map's camera.
     */
    private void getDeviceLocation() {
        // Get the best and most recent location of the device, which may be null in rare
        // cases when a location is not available.
        try {
            //Check the locationPermissionGranted boolean
            if (locationPermissionGranted) {
                Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position and lastKnownLocation to the current location of the device.
                            lastKnownLocation = task.getResult();
                            if (lastKnownLocation != null) {
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(lastKnownLocation.getLatitude(),
                                                lastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                            }
                        } else {
                            //Move the camera to the default location
                            map.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                            //Set the my location enabled flag to false
                            map.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }

    /**
     * Prompts the user for permission to use the device location.
     */
    private void getLocationPermission() {

        //Request location permission, so that we can get the location of the
        //device. The result of the permission request is handled by a callback,
        //onRequestPermissionsResult.

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

    }

    /**
     * Handles the result of the request for location permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        locationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                // if the permission is granted set to true
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);

            } else {
                // if the permission is granted set to false
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                // attempt to get the location of the user
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    /**
     * Method to code polyline takes from here:
     * https://github.com/scoutant/polyline-decoder/blob/5b6f55fc1a2ebf97383fd14e16ba00bee853ea5a/src/main/java/org/scoutant/polyline/PolylineDecoder.java#L26
     *
     * Port to Java of Mark McClures Javascript PolylineEncoder :
     * http://facstaff.unca.edu/mcmcclur/GoogleMaps/EncodePolyline/decode.js
     *
     * Polyline encoding algorithm :
     * https://developers.google.com/maps/documentation/utilities/polylinealgorithm
     **/


    public List<Point> decode(String encoded, double precision) {
        List<Point> track = new ArrayList<Point>();
        int index = 0;
        int lat = 0, lng = 0;

        while (index < encoded.length()) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            Point p = new Point((double) lat / precision, (double) lng / precision);
            track.add(p);
        }
        return track;
    }

    /**
     * CameraRouteCentered centers the camera on all the routes
     **/

    public void cameraRouteCentered(GoogleMap map, List<LatLng> lastLatLngRoute) {
        //Create a boundsbuilder object.
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        // For all the points in a route include them in the bounds
        for (LatLng pointLatLong : lastLatLngRoute)
            boundsBuilder.include(pointLatLong);
        //Set the padding around the routes
        int routePadding = 100;
        //Build the latlong of the bounds
        LatLngBounds boundsLatLong = boundsBuilder.build();
        //Move the camera to the position of the bounds
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsLatLong, routePadding));
    }

}


