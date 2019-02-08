package org.d3ifcool.googlemap;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A simple {@link Fragment} subclass.
 */
public class BuildingListFragment extends Fragment {
    private static final String TAG = "GalleryActivity";


    public BuildingListFragment() {
        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_building_list, container, false);
        AppCompatButton appCompatButton = view.findViewById(R.id.info_button);
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BuildingActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
    }
}
