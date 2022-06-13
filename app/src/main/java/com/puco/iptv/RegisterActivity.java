package com.puco.iptv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editTextTextPersonName, editTextTextPassword;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        editTextTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Register();
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    private void Register() {
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

        if(password.length() < 8){
            editTextTextPassword.setError("Passwordot mora da ima 8 karakteri");
            editTextTextPassword.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    startActivity(new Intent(RegisterActivity.this,SigninActivity.class));
                    Toast.makeText(RegisterActivity.this, "Успрешно се регистриравте", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "Неуспешна регистрација", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}