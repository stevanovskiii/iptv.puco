package com.puco.iptv;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {

    public static final int GOOGLE_SIGN_IN_CODE = 10005;
    private FirebaseAuth mAuth;
    private EditText editTextTextPersonName, editTextTextPassword;
    private Button sign_in_google,register, anonyLogin, googleLogin, mkd, eng;

    GoogleSignInOptions gso;
    GoogleSignInClient signInClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth = FirebaseAuth.getInstance();

        Language lang = new Language(this);


        mkd = (Button) findViewById(R.id.mkd);
        mkd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                lang.updateResource("mk");
                recreate();
            }
        });;
        eng = (Button) findViewById(R.id.eng);
        eng.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                lang.updateResource("en");
                recreate();
            }
        });;

        googleLogin = (Button) findViewById(R.id.googleLogin);
        googleLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loginWithGoogle();
            }
        });;

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        signInClient = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            Toast.makeText(SigninActivity.this, "Успешна најава", Toast.LENGTH_LONG).show();
        }

        editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        editTextTextPassword = (EditText) findViewById(R.id.editTextTextPassword);

        anonyLogin = (Button) findViewById(R.id.anonyLogin);

        anonyLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LoginGuest();
            }
        });

        sign_in_google = (Button) findViewById(R.id.signin_google);
        sign_in_google.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SignInWithEmail();
            }
        });
        register = (Button) findViewById(R.id.buttonReg);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SigninActivity.this,RegisterActivity.class));
            }
        });
    }

    private void loginWithGoogle() {
        Intent sign = signInClient.getSignInIntent();
        startActivityForResult(sign, GOOGLE_SIGN_IN_CODE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GOOGLE_SIGN_IN_CODE){
            Task<GoogleSignInAccount> signInTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount signInAcc = signInTask.getResult(ApiException.class);
//                signInTask.getResult(ApiException.class);
//                finish();
                Intent intent = new Intent(getApplicationContext(),WlcmActivity.class);
                startActivity(intent);
            }catch(ApiException e){
                Toast.makeText(this,"Грешка", Toast.LENGTH_SHORT).show();
            }


        }
    }

    private void LoginGuest() {
        mAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    startActivity(new Intent(SigninActivity.this,WlcmActivity.class));
                    Toast.makeText(SigninActivity.this, "Успешна најава", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SigninActivity.this, "Неуспешна најава", Toast.LENGTH_LONG).show();
                }
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