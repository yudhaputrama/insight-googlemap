package org.d3ifcool.googlemap;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    ImageButton mIbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.map_sheet));
        final  BottomSheetBehavior bottomSheetBehavior1 = BottomSheetBehavior.from(findViewById(R.id.building_sheet));
        bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_HIDDEN);

        mIbutton = findViewById(R.id.button_direction);
        mIbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        AppCompatButton appCompatButton = findViewById(R.id.info_button);
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, BuildingActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        ArrayList<Building> list = new ArrayList<>();
        list.add(new Building("Gedung Fakultas Ilmu Terapan"));
        list.add(new Building("Gedung Fakultas Komunikasi dan Bisnis"));
        list.add(new Building("Gedung Fakultas Ekonomi dan Bisnis"));
        list.add(new Building("Gedung Fakultas Ilmu Kreatif"));
        list.add(new Building("Gedung Fakultas Informatika"));
        list.add(new Building("Gedung Fakultas Rekayasa Industri"));
        list.add(new Building("Gedung Fakultas Teknik Elektro"));
        list.add(new Building("Gedung Learning Center"));
        list.add(new Building("Gedung L - Perkantoran Administrasi"));
        list.add(new Building("Gedung G - Pascasarjana"));
        list.add(new Building("Gedung Kuliah Umum 10 lantai"));
        list.add(new Building("Gedung Bisnis Center"));
        list.add(new Building("Gedung Student Center"));
        list.add(new Building("Gedung H (Gedung Serba Guna)"));
        list.add(new Building("Gedung K (Auditorium)"));
        list.add(new Building("Grha Wiyata Cacuk Sudarijanto-A"));
        list.add(new Building("Grha Wiyata Cacuk Sudarijanto-B"));
        list.add(new Building("Gedung Convention Hall"));
        list.add(new Building("Gedung Dekanat FEB"));

        BuildingAdapter adapter = new BuildingAdapter(this, list);
        adapter.setOnClickListener(new BuildingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Building building, int position) {
                bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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

        // Add a marker in Sydney and move the camera
        LatLng telkomLocation = new LatLng(-6.973719, 107.630539);

        Drawable background = ContextCompat.getDrawable(this, R.drawable.ic_user_top);
        background.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        background.draw(canvas);


        mMap.setLocationSource(new LocationSource() {
            @Override
            public void activate(OnLocationChangedListener onLocationChangedListener) {
                Location location = new Location("Custom");
                location.setLatitude(-6.973719);
                location.setLongitude(107.630539);
                onLocationChangedListener.onLocationChanged(location);
            }

            @Override
            public void deactivate() {

            }
        });

        Marker telkomMarker = mMap.addMarker(new MarkerOptions()
                .position(telkomLocation)
                .title("Telkom University")
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(telkomLocation,18));
    }
}
