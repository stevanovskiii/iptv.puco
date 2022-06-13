package com.puco.iptv;

import android.app.Activity;
import android.content.Intent;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends Activity {

//   // TextView google_btn;
//    GoogleSignInOptions gso;
//    GoogleSignInClient gsc;
//    private BeginSignInRequest signInRequest;
    private FirebaseAuth mAuth;
//
    private EditText editTextTextPersonName, editTextTextPassword;
    private Button login;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//
        editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        editTextTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
        login = (Button) findViewById(R.id.signin_google);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                SignInWithEmail();
            }
        });

        mAuth = FirebaseAuth.getInstance();
//
//      //  google_btn = findViewById(R.id.signin_google);
//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        gsc = GoogleSignIn.getClient(this, gso);
//
////        google_btn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                SignInWithEmail();
////            }
////        });
   }
//
//    private void SignIn() {
//        Intent intent=gsc.getSignInIntent();
//        startActivityForResult(intent,100);
//    }
//
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
                    startActivity(new Intent(LoginActivity.this,WlcmActivity.class));
                    Toast.makeText(LoginActivity.this, "Uspesna najava", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Neuspesna najava", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode==100){
//            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
//            try{
//                task.getResult(ApiException.class);
//                WlcmActivity();
//            } catch (ApiException e){
//                Toast.makeText(this,"Error", Toast.LENGTH_SHORT ).show();
//            }
//        }
//    }
//    private void WlcmActivity(){
//        finish();
//        Intent intent=new Intent(getApplicationContext(),WlcmActivity.class);
//        startActivity(intent);
//    }
//
//    private void Najava(){
////        signInRequest = BeginSignInRequest.builder()
////                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
////                        .setSupported(true)
////                        // Your server's client ID, not your Android client ID.
////                        .setServerClientId(getString(R.string.default_web_client_id))
////                        // Only show accounts previously used to sign in.
////                        .setFilterByAuthorizedAccounts(true)
////                        .build())
////                .build();
//    }
//    public void Klik(View v) {
//        switch (v.getId()){
//            case R.id.signin_google:
//                startActivity(new Intent(this,WlcmActivity.class));
//                SignInWithEmail();
//                break;
//        }
//    }

}