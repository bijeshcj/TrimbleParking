package com.bijesh.trimblepark.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bijesh.trimblepark.AppConstant;
import com.bijesh.trimblepark.R;
import com.bijesh.trimblepark.models.Vehicle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewMyVehicle extends BaseFragment implements AppConstant {

    private  Vehicle vehicle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_profile, container, false);
        initComponents(view);
        return view;
    }


    private void initComponents(View view) {

        TextView txtViewVehicleNumber = view.findViewById(R.id.textViewVehicleNumber);
        TextView txtViewVehicleModel = view.findViewById(R.id.textViewVehicleModel);
        TextView txtViewPark = view.findViewById(R.id.textViewParkingType);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        List<Vehicle> vehicles = new ArrayList<>();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    for (DataSnapshot s:ds.getChildren()){
                        if(s.getKey().equals(mAuth.getUid())){
                            vehicle = s.getValue(Vehicle.class);
                            Log.d("Veh ", vehicle.getVehicleModel());
                            vehicles.add(vehicle);

                        }
                    }
                }
                if(vehicle != null) {
                    txtViewVehicleNumber.setText(txtViewVehicleNumber.getText()+": "+vehicle.getVehicleNumber());
                    txtViewVehicleModel.setText(txtViewVehicleModel.getText()+": "+vehicle.getVehicleModel());
                    txtViewPark.setText(txtViewPark.getText()+": "+vehicle.getParkingTypes());
                    sharedPreferences.edit().putString(SHARED_PREFS_VEHICLE_TYPE,vehicle.getParkingTypes()).commit();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Veh","The read failed: " + databaseError.getCode());

            }
        });





    }


}
