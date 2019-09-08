package com.bijesh.trimblepark.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bijesh.trimblepark.AppConstant;
import com.bijesh.trimblepark.R;
import com.bijesh.trimblepark.utils.CheckoutUtils;

public class CheckoutFragment extends BaseFragment implements AppConstant {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view){
        EditText edtTxtHrs = view.findViewById(R.id.editTextEnterHour);
        TextView txtViewAmount = view.findViewById(R.id.textViewDueAmount);
        Button btnCheckout = view.findViewById(R.id.buttonCharge);

        btnCheckout.setOnClickListener(v ->{
               int hrs = 0;
               if(!edtTxtHrs.getText().toString().trim().contains("Enter"))
                   hrs = Integer.parseInt(edtTxtHrs.getText().toString().trim());
               double amt = CheckoutUtils.getCheckout(CheckoutUtils.getParkingType(sharedPreferences.getString(SHARED_PREFS_VEHICLE_TYPE,"MotorCycle")),
                       hrs);
               txtViewAmount.setText(""+amt);
        });

    }




}
