package com.example.android_app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SmsActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        Button sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(SmsActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    sendSms();
                } else {
                    ActivityCompat.requestPermissions(SmsActivity.this, new String[]{
                                    Manifest.permission.SEND_SMS
                            }, 100
                    );
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sendSms();
        } else {
            Toast.makeText(this, "SMS izni reddedildi.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSms()
    {
        EditText phone = findViewById(R.id.phone);
        EditText message = findViewById(R.id.message);

        String phoneNumber = phone.getText().toString();
        String messageText = message.getText().toString();

        try {
            if(phoneNumber != ""){
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber,null,messageText,null,null);
            }else{
                Toast.makeText(this, "Lütfen Telefon Numarası ve Mesaj giriniz",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            showMessage();
        }
    }

    private void showMessage(){
        Toast.makeText(this, "Sms Gönderimi Başarısız",Toast.LENGTH_SHORT).show();
    }
}
