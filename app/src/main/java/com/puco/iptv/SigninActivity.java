package com.puco.iptv;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editTextTextPersonName, editTextTextPassword;
    private Button sign_in_google;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth = FirebaseAuth.getInstance();

        editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        editTextTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
        sign_in_google = (Button) findViewById(R.id.signin_google);
        sign_in_google.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SignInWithEmail();
            }
        });
    }

    private void SignInWithEmail() {

        String email= editTextTextPersonName.getText().toString().trim();
        String password= editTextTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextTextPersonName.setError("Zadolzitelen email");
            editTextTextPersonName.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextTextPersonName.setError("Nevaliden e-mail");
            editTextTextPersonName.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextTextPassword.setError("Zadolzitelen password");
            editTextTextPassword.requestFocus();
            return;
        }

/*        if(password.length() < 8){
            editTextPassword.setError(getResources().getString(R.string.invalid_password));
            editTextPassword.requestFocus();
            return;
        }*/
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    startActivity(new Intent(SigninActivity.this,WlcmActivity.class));
                    Toast.makeText(SigninActivity.this, "Uspesna najava", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SigninActivity.this, "Neuspesna najava", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}