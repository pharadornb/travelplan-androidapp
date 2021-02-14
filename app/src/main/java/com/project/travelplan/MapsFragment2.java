package com.project.travelplan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment2 extends Fragment {
    DbHelper dbh;

    String s_lat, s_log, s_name;
    Double d_lat=14.918750, d_log=102.008056;

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

            LatLng sydney = new LatLng(d_lat, d_log);
            googleMap.addMarker(new MarkerOptions().position(sydney).title(s_name));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12.0f));
            googleMap.getUiSettings().setZoomControlsEnabled(false);
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    //Toast.makeText(getActivity(), s_name, Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                            builder
                            .setMessage("เพิ่มลงสถานที่ท่องเที่ยวของฉันไหม?")
                            .setPositiveButton("ใช่",  new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dbh = new DbHelper(getActivity());

                                    boolean IsSucceed = dbh.AddData(s_name, d_lat, d_log);
                                    if(IsSucceed){
                                        Toast.makeText(getActivity(), "สำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(getActivity(), "ไม่สามารถเพิ่มได้", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("ไม่ใช่", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            })
                            .show();

                    return false;
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if(bundle != null){
            s_lat = bundle.getString("key_lat");
            s_log = bundle.getString("key_log");
            s_name = bundle.getString("key_name");
        }

        d_lat = Double.parseDouble(s_lat);
        d_log = Double.parseDouble(s_log);

        return inflater.inflate(R.layout.fragment_maps2, container, false);
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