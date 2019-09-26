package com.example.map_prac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    SupportMapFragment mapFragment;
    SearchView searchView1,searchView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView1=findViewById(R.id.sv_location1);
        searchView2=findViewById(R.id.sv_location2);
        mapFragment=(SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);

        searchView1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String location1=searchView1.getQuery().toString();
                List<Address> addressList = null;

                if(location1!=null || !location1.equals("")){
                    Geocoder geocoder1 = new Geocoder(MainActivity.this);
                    try{
                        addressList = geocoder1.getFromLocationName(location1,1);
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    Address address1 = addressList.get(0);
                    LatLng latLng1 = new LatLng(address1.getLatitude(),address1.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng1).title(location1));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng1,10));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

       searchView2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String location2=searchView2.getQuery().toString();
                List<Address> addressList = null;

                if(location2!=null || !location2.equals("")){
                    Geocoder geocoder2 = new Geocoder(MainActivity.this);
                    try{
                        addressList = geocoder2.getFromLocationName(location2,1);
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    Address address2 = addressList.get(0);
                    LatLng latLng2 = new LatLng(address2.getLatitude(),address2.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng2).title(location2));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng2,10));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map=googleMap;


    }
}
