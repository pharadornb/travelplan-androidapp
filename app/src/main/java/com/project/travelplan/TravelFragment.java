package com.project.travelplan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TravelFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    MapView mapView;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_travel, container, false);

        //Initilaize map fragemnt
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.travel);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       mapView = (MapView) view.findViewById(R.id.travel);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.travel);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                googleMap.clear();
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom( latLng, 10));
                googleMap.addMarker(markerOptions);
            }
        });

//        mMap = googleMap;
//        LatLng UCA = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(UCA).title("YOUR TITLE")).showInfoWindow();
//
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(UCA,17));
//
//        MapsInitializer.initialize(getContext());
//
//        googleMap = GoogleMap;
//        GoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        GoogleMap.addMarker(new MarkerOptions().position(new LatLng(40.689247, -74.044502)).title("State of Library").snippet("I hope to go there some day"));
    }
}