package com.restaurantreservation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class AuthenticationActivity extends AppCompatActivity implements KeyEvent.Callback {

    private SharedPreferences userInfoPref;

    private EditText[] textCode;
    private String code;

    private String phoneNumber;

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        dbManager = LoginActivity.dbManager;

        userInfoPref = getSharedPreferences("userInfo", MODE_PRIVATE);

        phoneNumber = userInfoPref.getString("phoneNumber", "");

        textCode = new EditText[]{ findViewById(R.id.textAuthenticationCode1),
                findViewById(R.id.textAuthenticationCode2),
                findViewById(R.id.textAuthenticationCode3),
                findViewById(R.id.textAuthenticationCode4)};

        for(int i = 0; i < textCode.length - 1; i++){
            textCode[i].addTextChangedListener(new OnEnterCodeListener(textCode[i + 1]));
        }

        // TODO change MainActivity.class to Reservation Page
        textCode[textCode.length - 1].addTextChangedListener(new OnFinishInputCodeListener(this, new Intent(AuthenticationActivity.this, MainActivity.class)));
    }

    public static String GenerateCode(int digits){
        String code = "";
        Random rng = new Random();
        for(int i = 0; i < digits; i++){
            code += rng.nextInt(10);
        }
        return code;
    }

    private void ResetFields(){
        for(int i = 0; i < textCode.length; i++){
           textCode[i].setText("");
        }
        textCode[0].requestFocus();
    }

    class OnFinishInputCodeListener implements TextWatcher{

        private Intent nextActivity;

        private AuthenticationActivity activity;

        public OnFinishInputCodeListener(AuthenticationActivity activity, Intent nextActivity){
            this.activity = activity;
            this.nextActivity = nextActivity;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.toString().length() > 0){
                // TODO verify code with the database
                code = "";
                for(int i = 0; i < textCode.length; i++){
                    code += textCode[i].getText();
                }
                String c = dbManager.getAuthenticationCode(phoneNumber);
                if(code.equalsIgnoreCase(c)){
                    startActivity(nextActivity);
                }
                else {
                    activity.ResetFields();
                    Toast.makeText(getApplicationContext(), "Incorrect Code!", Toast.LENGTH_SHORT).show();
                }
            }

        }


    }

    class OnEnterCodeListener implements TextWatcher{

        private EditText nextEditText;

        public OnEnterCodeListener(EditText nextEditText){
            this.nextEditText = nextEditText;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            nextEditText.requestFocus();
        }
    }
}
