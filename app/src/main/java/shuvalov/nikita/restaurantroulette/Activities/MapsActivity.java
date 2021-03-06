package shuvalov.nikita.restaurantroulette.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import shuvalov.nikita.restaurantroulette.DateNightHelper;
import shuvalov.nikita.restaurantroulette.OurAppConstants;
import shuvalov.nikita.restaurantroulette.R;
import shuvalov.nikita.restaurantroulette.RestaurantSearchHelper;
import shuvalov.nikita.restaurantroulette.RouletteHelper;
import shuvalov.nikita.restaurantroulette.YelpResources.YelpObjects.Business;

import static shuvalov.nikita.restaurantroulette.OurAppConstants.USER_LAST_LAT;
import static shuvalov.nikita.restaurantroulette.OurAppConstants.USER_LAST_LOCATION;
import static shuvalov.nikita.restaurantroulette.OurAppConstants.USER_LAST_LON;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String TAG = "MapsActivity";

    private GoogleMap mMap;
    public Business mBusiness;
    public int mBusinessPosition;
    private String origin;
    private boolean isMystery, mDateNight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Gets Instance of the Business
        mBusinessPosition = getIntent().getIntExtra(OurAppConstants.BUSINESS_POSITION_INTENT_KEY, -1);
        if (getIntent().getStringExtra("origin").equals("roulette")) { //ToDo: Change to constant values.
            mBusiness = RouletteHelper.getInstance().getBusinessAtPosition(mBusinessPosition);
            isMystery = true;
        } else if (getIntent().getStringExtra(OurAppConstants.ORIGIN).equals(OurAppConstants.DATE_NIGHT_ORIGIN)){
            mDateNight = true;
        }else {
            mBusiness = RestaurantSearchHelper.getInstance().getBusinessAtPosition(mBusinessPosition);
            isMystery = false;
        }
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

        if(mDateNight){
            populateDateNightMap(googleMap);
        }else {


            // User Location
            SharedPreferences sharedPreferences = getSharedPreferences(USER_LAST_LOCATION,
                    Context.MODE_PRIVATE);

            String userLat = sharedPreferences.getString(USER_LAST_LAT, "userLastLat");
            String userLon = sharedPreferences.getString(USER_LAST_LON, "userLastLon");

            Log.d(TAG, "onMapReady: " + userLat);
            Log.d(TAG, "onMapReady: " + userLon);

            LatLng userLocation = new LatLng(Double.parseDouble(userLat), Double.parseDouble(userLon));
            mMap.addMarker(new MarkerOptions()
                    .position(userLocation)
                    .title("My Location")).showInfoWindow();

            Log.d(TAG, "onMapReady: " + userLocation);

            // Business Location
            String title;
            if (isMystery) {
                title = "???????";
            } else {
                title = mBusiness.getName();
            }

            LatLng businessCoordinates = new LatLng(mBusiness.getCoordinates().getLatitude(),
                    mBusiness.getCoordinates().getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(businessCoordinates)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                    .title(title))
                    .showInfoWindow();

            // Changes Zoom based on the distance between User Location and Business

            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(userLocation);
            builder.include(businessCoordinates);
            LatLngBounds bounds = builder.build();

            CameraUpdate latLngBounds = CameraUpdateFactory.newLatLngBounds(bounds, 150);

            mMap.animateCamera(latLngBounds);
        }
    }

    public void populateDateNightMap(GoogleMap googleMap){
        List<Business> dateItinerary = DateNightHelper.getInstance().getDateItinerary();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        int i =0;

        SharedPreferences locationPreferences = getSharedPreferences(OurAppConstants.USER_LAST_LOCATION,MODE_PRIVATE);

        String userLat = locationPreferences.getString(USER_LAST_LAT, "userLastLat");
        String userLon = locationPreferences.getString(USER_LAST_LON, "userLastLon");

        LatLng userLocation = new LatLng(Double.parseDouble(userLat),Double.parseDouble(userLon));

        googleMap.addMarker(new MarkerOptions()
                .position(userLocation)
                .title("You are here"))
                .showInfoWindow();

        builder.include(userLocation);

        for(Business business: dateItinerary){
            LatLng latLng = new LatLng(business.getCoordinates().getLatitude(), business.getCoordinates().getLongitude());
            i++;
            googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))//ToDo:Color code each place
                    .snippet("Place #"+i)
                    .title(business.getName()))
                    .showInfoWindow();

            builder.include(latLng);
        }
        LatLngBounds bounds = builder.build();
        CameraUpdate latLngBounds = CameraUpdateFactory.newLatLngBounds(bounds, 150);
        mMap.animateCamera(latLngBounds);
    }
}
