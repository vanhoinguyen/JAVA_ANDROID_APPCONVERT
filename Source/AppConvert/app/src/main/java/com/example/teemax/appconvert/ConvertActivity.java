package com.example.teemax.appconvert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ConvertActivity extends AppCompatActivity implements TextWatcher, AdapterView.OnItemSelectedListener {

    private EditText edtInput;
    private TextView lblResult;
    private Spinner spUnit;
    private String[] arrUnit;
    private int convertType;


    private int currentUnit=0;
    //                                                meter     mile       cm         inch         yd
    private float[][] arrConvertDistancetRate={     { 1,    0.000621371f,   100f,   39.3701f,   1.09361f},  //meter
                                                    { 1609.339f,    1,   160933.9f,   63359.8f,   1759.99f},//mile
                                                    { 0.01f,    9.999969f,   1,   0.39369f,   0.01093f },   //cm
                                                    { 0.025399f,    1.5782f,   2.53999f,   1,   0.02777f},  //inch
                                                    { 0.914f,    0.000568f,   91.4397f,   35.999f,   1}};   //yd

//                                                    g       kg            yến             tạ         tấn
    private float[][] arrConvertWeightRate={        { 1,     0.001f ,       0.0001f  ,   0.00001f ,   0.000001f},   //g
                                                    { 1000,    1,            0.1f,           0.01f,      0.001f},   //kg
                                                    { 10000,    10,            1,              0.1f ,    0.01f },   //yến
                                                    { 100000,  100,            10,                1,        0.1f},  //tạ
                                                    { 1000000,    1000,        100,               10,          1}}; //tấn


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        edtInput=findViewById(R.id.edtInput);
        lblResult=findViewById(R.id.lblResult);
        spUnit=findViewById(R.id.spUnit);
        convertType=getIntent().getExtras().getInt("convert_type");


        //get value of arrUnit
        arrUnit=getResources().getStringArray(getCurrentUnit(convertType));
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,getCurrentUnit(convertType),R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spUnit.setAdapter(adapter);
        spUnit.setOnItemSelectedListener(this);

        edtInput.addTextChangedListener(this);

    }

    public int getCurrentUnit(int convertType) {
        if(convertType==MainActivity.DISTANCE_CONVERT)
        {
            return R.array.unitDistance;

        }
        else if(convertType==MainActivity.WEIGHT_CONVERT)
        {
           return R.array.unitWeight;

        }
        return 0;

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        convert(currentUnit,getArrConvertRateMatrix(convertType));

    }

    private void convert(int currentUnit, float[][] arrConvertDistancetRate)
    {
        //check input
        if(edtInput.getText().toString().trim().length()<=0)
        {
            lblResult.setText("Result");
            return;
        }

        //convert
        float input=Float.parseFloat(edtInput.getText().toString());
        String result="";

        for(int i=0;i<arrUnit.length;i++)
        {
            if(currentUnit!=i)
            {
                result+=arrUnit[i];
                result+=": ";
                result+=input*arrConvertDistancetRate[currentUnit][i];
                result+="\n";
            }
        }
        lblResult.setText(result);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        currentUnit=position;
        convert(currentUnit,getArrConvertRateMatrix(convertType));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private float[][] getArrConvertRateMatrix (int convertType)
    {
        if (convertType == MainActivity.DISTANCE_CONVERT) {
            return arrConvertDistancetRate;
        } else if (convertType == MainActivity.WEIGHT_CONVERT) {
            return arrConvertWeightRate;
        } else {
            return null;
        }
    }
}
