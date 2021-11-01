package com.example.attendendo;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;


import java.util.concurrent.Executor;

public class BiometricAct {
    private BiometricManager biometricManager;
    private Executor executor;
    private androidx.biometric.BiometricPrompt biometricPrompt;
    private androidx.biometric.BiometricPrompt.PromptInfo promptInfo;

    @RequiresApi(api= Build.VERSION_CODES.Q)
    //check if the device is compatible
    public boolean checkCompatibility(Context context){
        biometricManager = androidx.biometric.BiometricManager.from(context);
        // if the int value of canAuthenticate matches BBIOMETRIC.SUCCESS then authenticating hardware is present
        if (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK) == BiometricManager.BIOMETRIC_SUCCESS){

            return  true;
        }
        else {
            return false;
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.P)
    public  void  bioMetricPrompt(Context context){
        //creting an executor to manage our runnable task of recreating the prompt everytime the activity fragement is created
        executor = ContextCompat.getMainExecutor(context);
        // this will give up the result of execution and if we can log in or not
        biometricPrompt = new BiometricPrompt((FragmentActivity)context,executor,new BiometricPrompt.AuthenticationCallback(){
            // action for authentication error
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                    Toast.makeText(context, "Authentication Error", Toast.LENGTH_SHORT).show();
            }
            // action for authentication error
            // here if the authentication is successful then we
            //create a intent to openDairyActivity
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(context, "Authentication Succeed ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,DairyActivity.class);
                context.startActivity(intent);
            }
            // if authentication failed
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(context, "Authentication Failed", Toast.LENGTH_SHORT).show();
            }
        });
            // setting up the behaviour of prompt and creating our biometric dialog
        promptInfo = new androidx.biometric.BiometricPrompt.PromptInfo.Builder()
                .setTitle("BioMetric Login")
                .setSubtitle("Login Using your fingerprint")
                .setNegativeButtonText("cancle")
                .build();

        biometricPrompt.authenticate(promptInfo);





    }


}




