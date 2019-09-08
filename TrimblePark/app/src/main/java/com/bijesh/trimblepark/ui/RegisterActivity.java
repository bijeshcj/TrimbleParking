package com.bijesh.trimblepark.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bijesh.trimblepark.R;
import com.bijesh.trimblepark.models.Vehicle;
import com.bijesh.trimblepark.models.enums.ParkingTypes;
import com.bijesh.trimblepark.utils.Validations;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.HashMap;

public class RegisterActivity extends BaseActivity {

    private static final String TAG = "RegisterActivity";

    private Spinner spinner;
    private ArrayAdapter<String> arrayAdapter;
    private EditText editTextPass,editTextCon,editTextVehNum,editTextVehModel,editTextEmail;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(view -> {
            registerUser(editTextEmail.getText().toString().trim(),editTextCon.getText().toString().trim());
        });

        spinner = findViewById(R.id.spinnerParkingType);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getNames(ParkingTypes.class));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(3);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPass = findViewById(R.id.editTextPassword);
        editTextCon = findViewById(R.id.editTextConfirmPassword);
        editTextVehNum = findViewById(R.id.editTextVehicleNumber);
        editTextVehModel = findViewById(R.id.editTextModel);

        progressBar = findViewById(R.id.progressBar);

    }


    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }


    private void registerUser(String email,String password){

        progressBar.setVisibility(View.VISIBLE);
        if (!Validations.emailValidator(editTextEmail.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "Invalid Email ", Toast.LENGTH_LONG).show();
            return;
        }

        if(!validatePasswords()){
            Toast.makeText(this,"Invalid password combination",Toast.LENGTH_LONG).show();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                            String userId = getUid();
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference = database.getReference();

                            databaseReference.push().setValue(buildVehicleDetails(userId)).addOnCompleteListener(updateVehicleTask ->{
                                if(updateVehicleTask.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this,"User and Vehicle created ",Toast.LENGTH_LONG).show();
                                    RegisterActivity.this.finish();
                                    progressBar.setVisibility(View.INVISIBLE);
                                }else{
                                    Toast.makeText(RegisterActivity.this,"Vehicle update Failed ",Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Registration failed try later...",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
//                            updateUI(null);
                        }

                    }
                });

    }

    private boolean validatePasswords(){
        return editTextPass.getText().toString().trim().equals(editTextCon.getText().toString().trim());
    }

    private HashMap<String,Vehicle> buildVehicleDetails(String userid){

        Vehicle vehicle =  new Vehicle(editTextVehNum.getText().toString().trim(),editTextVehModel.getText().toString().trim(),
                spinner.getSelectedItem().toString());

        HashMap<String,Vehicle> map = new HashMap<>();
        map.put(userid,vehicle);
        return map;
    }

}
