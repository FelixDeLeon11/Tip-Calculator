package com.example.tipcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    TextView tip;
    EditText bill;
    Slider sliderPercent;
    Slider sliderPeople;
    TextView numPercent;
    TextView numPeople;
    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderPercent = findViewById(R.id.sliderPercent);
        sliderPeople = findViewById(R.id.sliderPeople);
        numPercent = findViewById(R.id.numPercent);
        numPeople = findViewById(R.id.numPeople);
        tip = findViewById(R.id.tip);
        bill = findViewById(R.id.bill);

        tip.setText(String.format("$%s","0.00"));
        numPercent.setText(df.format(sliderPercent.getValue())  );
        numPeople.setText(String.format("%d", Math.round(sliderPeople.getValue() )));

        sliderPeople.addOnChangeListener((Slider.OnChangeListener) (slider, value, fromUser) -> {
            numPeople.setText(String.format("%d", Math.round(sliderPeople.getValue() )));
            if (TextUtils.isEmpty(bill.getText())){
                return;
            }
            double tbill = (double) Float.parseFloat(bill.getText().toString());
            double tpercent = ((double) sliderPercent.getValue()/100 );
            double tpeopple = (double) sliderPeople.getValue();
            Log.i("calculation", String.format("%f", (tbill * tpercent) / tpeopple) );
            tip.setText(String.format("$%s",df.format( (tbill * tpercent) / tpeopple)));
        });

        sliderPercent.addOnChangeListener((Slider.OnChangeListener) (slider, value, fromUser) -> {
            numPercent.setText(df.format(sliderPercent.getValue())  );
            if (TextUtils.isEmpty(bill.getText())){
                return;
            }
            double tbill = (double) Float.parseFloat(bill.getText().toString());
            double tpercent = ((double) sliderPercent.getValue()/100 );
            double tpeopple = (double) sliderPeople.getValue();
            Log.i("calculation", String.format("%f", (tbill * tpercent) / tpeopple) );
            tip.setText(String.format("$%s",df.format( (tbill * tpercent) / tpeopple)));
        });

        bill.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() == 0){return;}
                double tbill = (double) Float.parseFloat(s.toString());
                double tpercent = ((double) sliderPercent.getValue()/100 );
                double tpeopple = (double) sliderPeople.getValue();
                Log.i("calculation", String.format("%f", (tbill * tpercent) / tpeopple) );
                tip.setText(String.format("$%s",df.format( (tbill * tpercent) / tpeopple)));
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }
}