package com.example.teemax.appconvert;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout distanceItem, speedItem,tempelatureItem,weightItem;
    public static int DISTANCE_CONVERT=0;
    public static int WEIGHT_CONVERT=1;
    public static int SPEED_CONVERT=2;
    public static int TEMPELATURE_CONVERT=3;

    private int convertType=DISTANCE_CONVERT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        distanceItem=findViewById(R.id.distance_item);
        weightItem=findViewById(R.id.weight_item);
        speedItem=findViewById(R.id.speed_item);
        tempelatureItem=findViewById(R.id.tempelature_item);

        distanceItem.setOnClickListener(this);
        weightItem.setOnClickListener(this);
        speedItem.setOnClickListener(this);
        tempelatureItem.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        int id=v.getId();

        if(id==R.id.distance_item)
        {
            Intent intent=new Intent(this,ConvertActivity.class);
            convertType=DISTANCE_CONVERT;
            intent.putExtra("convert_type",convertType);
            startActivity(intent);
        }
        else if(id==R.id.weight_item)
        {
            Intent intent=new Intent(this,ConvertActivity.class);
            convertType=WEIGHT_CONVERT;
            intent.putExtra("convert_type",convertType);
            startActivity(intent);
        }



    }
}
