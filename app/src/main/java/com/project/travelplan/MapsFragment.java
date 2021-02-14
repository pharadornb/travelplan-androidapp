package com.project.travelplan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {
    DbHelper dbh;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            dbh = new DbHelper(getActivity());
            Double a_lat, a_log;
            String a_name;
            LatLng myLocation = null;
            Cursor resDat = dbh.getAllData();
            if(resDat.getCount() == 0){
                Toast.makeText(getActivity(),"ไม่มีสถานที่ท่องเที่ยวของคุณ!!!", Toast.LENGTH_SHORT).show();
            }else{
                StringBuffer dataBuff = new StringBuffer();
                while (resDat.moveToNext()){
                    myLocation = new LatLng(Double.parseDouble(resDat.getString(2)), Double.parseDouble(resDat.getString(3)));
                    googleMap.addMarker(new MarkerOptions().position(myLocation).title(resDat.getString(1)));
                }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 7.0f));
                googleMap.getUiSettings().setZoomControlsEnabled(false);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}