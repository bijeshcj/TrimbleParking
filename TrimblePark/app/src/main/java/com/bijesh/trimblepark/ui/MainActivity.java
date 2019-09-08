package com.bijesh.trimblepark.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bijesh.trimblepark.R;


public class MainActivity extends BaseActivity {


    private EditText edtTxtEmail,edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnRegister = findViewById(R.id.registerButton);
        Button btnLogin = findViewById(R.id.loginButton);

        edtTxtEmail = findViewById(R.id.emailInput);
        edtPassword = findViewById(R.id.passwordInput);

        btnLogin.setOnClickListener(view -> {
              mAuth.signInWithEmailAndPassword(edtTxtEmail.getText().toString().trim(),edtPassword.getText().toString().trim()).addOnCompleteListener(task -> {
                  if(task.isSuccessful()){
                      Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                      startActivity(intent);
                  }else{
                      Toast.makeText(MainActivity.this,"Authentication Failed...",Toast.LENGTH_LONG).show();
                  }
              });
        });


        btnRegister.setOnClickListener((view)->{
            Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(intent);
        });


    }
}
