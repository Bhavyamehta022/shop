package com.bhavya.shopfinderr;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean signedIn = false;
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Inflate the login page
                if(signedIn) {
                    //go to home page
                    openHome();
                } else {
                    // open login page, after successful login set signed in as true and go to home page
                    authenticate();
                    signedIn = true;
                    openHome();
                }
            }
        }, 200);

    }

    private void authenticate()
    {
        //firebase auth

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null)
        {
            //already signed in
            signedIn = true;
        }
        else
        {
            //not signed in

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.GoogleBuilder().build(),                   //to login using google account
                                    new AuthUI.IdpConfig.PhoneBuilder().build()))                   //to login using phone number
                            .build(),RC_SIGN_IN);

//            AuthUI.IdpConfig phoneConfigWithDefaultNumber = new AuthUI.IdpConfig.PhoneBuilder()
//            .setDefaultNumber(defaultCountryCode, defaultphoneNumber).build();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN)
        {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            //successfully signed in
            if (resultCode == RESULT_OK) {
                signedIn = true;
                openHome();
            }else {
                //sign in failed
                if (response == null)
                {
                    //user pressed back button
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    showSnackbar(R.string.sign_in_cancelled);
                    return;
                }
                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK)
                {
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    showSnackbar(R.string.no_internet_connection);
                }
            }
        }
    }

    public void showSnackbar(int snack)
    {
        //snackbar dikhana nahi ata isliye toast se kaam chala lia bad mein change kar sakte hain
        Toast.makeText(this, snack,Toast.LENGTH_LONG).show();
    }

    public void openHome(){
        Intent intent=new Intent(MainActivity.this,HomeActivity.class);
        startActivity(intent);
    }

    //we should have used setOnClickListener here
    public void goHome(View v)
    {
        openHome();
    }
}
