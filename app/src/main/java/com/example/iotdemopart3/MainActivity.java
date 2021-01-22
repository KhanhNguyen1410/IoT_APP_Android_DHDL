package com.example.iotdemopart3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;

    boolean stateToFirebase1;
    boolean stateToFirebase2;
    boolean stateToFirebase3;
    boolean stateToFirebase4;

    TextView temp;
    TextView humidity;
    TextView lightSensor;
    DatabaseReference dhtRef;
    String tempStatus;
    String humidityStatus;
    String sensorStatus;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("devices");
    DatabaseReference FirebaseLight1 = myRef.child("light1");
    DatabaseReference FirebaseLight2 = myRef.child("light2");
    DatabaseReference FirebaseLight3 = myRef.child("light3");
    DatabaseReference FirebaseLight4 = myRef.child("light4");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1 = (ImageView) findViewById(R.id.img1);
        imageView2 = (ImageView) findViewById(R.id.img2);
        imageView3 = (ImageView) findViewById(R.id.img3);
        imageView4 = (ImageView) findViewById(R.id.img4);

        temp = (TextView) findViewById(R.id.textView5);
        humidity = (TextView) findViewById(R.id.textView7);
        lightSensor = (TextView) findViewById(R.id.textView3);
        dhtRef = FirebaseDatabase.getInstance().getReference();
        dhtRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tempStatus = snapshot.child("Temp").getValue().toString();
                humidityStatus = snapshot.child("Humidity").getValue().toString();
                sensorStatus = snapshot.child("anhsang").getValue().toString();
                temp.setText(tempStatus);
                humidity.setText(humidityStatus);
                lightSensor.setText(sensorStatus);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void imgLight1(View view) {
        stateToFirebase1 = !stateToFirebase1;
        FirebaseLight1.setValue(stateToFirebase1);
        if(stateToFirebase1)
            imageView1.setImageResource(R.drawable.lightoff);
        else
            imageView1.setImageResource(R.drawable.lighton);
    }

    public void imgLight2(View view) {
        stateToFirebase2 = !stateToFirebase2;
        FirebaseLight2.setValue(stateToFirebase2);
        if(stateToFirebase2)
            imageView2.setImageResource(R.drawable.lightoff);
        else
            imageView2.setImageResource(R.drawable.lighton);
    }

    public void imgLight3(View view) {
        stateToFirebase3 = !stateToFirebase3;
        FirebaseLight3.setValue(stateToFirebase3);
        if(stateToFirebase3)
            imageView3.setImageResource(R.drawable.lightoff);
        else
            imageView3.setImageResource(R.drawable.lighton);
    }

    public void imgLight4(View view) {
        stateToFirebase4 = !stateToFirebase4;
        FirebaseLight4.setValue(stateToFirebase4);
        if(stateToFirebase4)
            imageView4.setImageResource(R.drawable.lightoff);
        else
            imageView4.setImageResource(R.drawable.lighton);
    }
}