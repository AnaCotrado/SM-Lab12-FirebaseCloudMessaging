package com.solmov.lab12firebasecloudmessaging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    Button btnSubscribe;
    private static final String TAG = "Subscription result ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("Time");

        btnSubscribe = findViewById(R.id.btnSubscribe);
        OnClickSubscribe();
    }

    private void OnClickSubscribe(){
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnSubscribe.getText().equals("Suscribirse")){
                    FirebaseMessaging.getInstance().subscribeToTopic("Time")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String msg = getString(R.string.msg_subscribed);
                                    btnSubscribe.setText(R.string.desuscribirse);
                                    if (!task.isSuccessful()) {
                                        msg = getString(R.string.msg_subscribe_failed);
                                        btnSubscribe.setText(R.string.suscribirse);
                                    }
                                    Log.d(TAG, msg);
                                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else{
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("Time")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String msg = getString(R.string.msg_unsubscribed);
                                    btnSubscribe.setText(R.string.suscribirse);
                                    if (!task.isSuccessful()) {
                                        msg = getString(R.string.msg_unsubscribe_failed);
                                        btnSubscribe.setText(R.string.desuscribirse);
                                    }
                                    Log.d(TAG, msg);
                                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }
}