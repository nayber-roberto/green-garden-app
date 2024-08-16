package com.example.greengarden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayDataActivity extends AppCompatActivity {

    private TableLayout myTableLayout;
    private Button buttonClear;
    private ImageButton buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        myTableLayout = findViewById(R.id.myTableLayout);
        buttonClear = findViewById(R.id.buttonClear);
        buttonBack = findViewById(R.id.buttonBack);
        loadData();

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearData();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadData() {
        SharedPreferences waterPrefs = getSharedPreferences("WaterData",MODE_PRIVATE);
        SharedPreferences electricityPrefs = getSharedPreferences("ElectricityData", MODE_PRIVATE);

        int waterIndex = waterPrefs.getInt("index",0);
        int electricityIndex = electricityPrefs.getInt("index", 0);

        for (int i = 0; i < waterIndex; i++) {
            String month = waterPrefs.getString("month" + i,"");
            String service = "Agua";
            String consumption = waterPrefs.getString("volume" + i, "");
            String price = waterPrefs.getString("price" + i, "");

            //Generar datos dentro de la tabla
            TableRow tableRow = new TableRow(this);

            TextView textViewMonth = new TextView(this);
            textViewMonth.setText(month);
            textViewMonth.setTextColor(Color.WHITE);
            textViewMonth.setPadding(50, 10, 10, 10);
            textViewMonth.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textViewMonth.setTypeface(ResourcesCompat.getFont(this, R.font.poppins_light), Typeface.NORMAL);
            textViewMonth.setBackgroundResource(R.color.colorPrimary);
            tableRow.addView(textViewMonth);

            TextView textViewService = new TextView(this);
            textViewService.setText(service);
            textViewService.setTextColor(Color.WHITE);
            textViewService.setPadding(50, 10, 10, 10);
            textViewService.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textViewService.setTypeface(ResourcesCompat.getFont(this, R.font.poppins_light), Typeface.NORMAL);
            textViewService.setBackgroundResource(R.color.colorPrimary);
            tableRow.addView(textViewService);

            TextView textViewConsumption = new TextView(this);
            textViewConsumption.setText(consumption);
            textViewConsumption.setTextColor(Color.WHITE);
            textViewConsumption.setPadding(10, 10, 10, 10);
            textViewConsumption.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textViewConsumption.setTypeface(ResourcesCompat.getFont(this, R.font.poppins_light), Typeface.NORMAL);
            textViewConsumption.setBackgroundResource(R.color.colorPrimary);
            tableRow.addView(textViewConsumption);

            TextView textViewPrice = new TextView(this);
            textViewPrice.setText(price);
            textViewPrice.setTextColor(Color.WHITE);
            textViewPrice.setPadding(10, 10, 10, 10);
            textViewPrice.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textViewPrice.setTypeface(ResourcesCompat.getFont(this, R.font.poppins_light), Typeface.NORMAL);
            textViewPrice.setBackgroundResource(R.color.colorPrimary);
            tableRow.addView(textViewPrice);

            myTableLayout.addView(tableRow);
        }

        for (int i = 0; i < electricityIndex; i++) {
            String month = electricityPrefs.getString("month" + i, "");
            String service = "Electricidad";
            String consumption = electricityPrefs.getString("kilowatt" + i, "");
            String price = electricityPrefs.getString("price" + i, "");

            TableRow tableRow = new TableRow(this);

            TextView textViewMonth = new TextView(this);
            textViewMonth.setText(month);
            textViewMonth.setTextColor(Color.WHITE);
            textViewMonth.setPadding(50, 10, 10, 10);
            textViewMonth.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textViewMonth.setTypeface(ResourcesCompat.getFont(this, R.font.poppins_light), Typeface.NORMAL);
            textViewMonth.setBackgroundResource(R.color.colorPrimary);
            tableRow.addView(textViewMonth);

            TextView textViewService = new TextView(this);
            textViewService.setText(service);
            textViewService.setTextColor(Color.WHITE);
            textViewService.setPadding(50, 10, 10, 10);
            textViewService.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textViewService.setTypeface(ResourcesCompat.getFont(this, R.font.poppins_light), Typeface.NORMAL);
            textViewService.setBackgroundResource(R.color.colorPrimary);
            tableRow.addView(textViewService);

            TextView textViewConsumption = new TextView(this);
            textViewConsumption.setText(consumption);
            textViewConsumption.setTextColor(Color.WHITE);
            textViewConsumption.setPadding(10, 10, 10, 10);
            textViewConsumption.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textViewConsumption.setTypeface(ResourcesCompat.getFont(this, R.font.poppins_light), Typeface.NORMAL);
            textViewConsumption.setBackgroundResource(R.color.colorPrimary);
            tableRow.addView(textViewConsumption);

            TextView textViewPrice = new TextView(this);
            textViewPrice.setText(price);
            textViewPrice.setTextColor(Color.WHITE);
            textViewPrice.setPadding(10, 10, 10, 10);
            textViewPrice.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textViewPrice.setTypeface(ResourcesCompat.getFont(this, R.font.poppins_light), Typeface.NORMAL);
            textViewPrice.setBackgroundResource(R.color.colorPrimary);
            tableRow.addView(textViewPrice);

            myTableLayout.addView(tableRow);
        }

    }

    //limpiar datos de la tabla
    private void clearData() {
        SharedPreferences waterPrefs = getSharedPreferences("WaterData",MODE_PRIVATE);
        SharedPreferences electricityPrefs = getSharedPreferences("ElectricityData", MODE_PRIVATE);

        SharedPreferences.Editor waterEditor = waterPrefs.edit();
        SharedPreferences.Editor electricityEditor = electricityPrefs.edit();

        waterEditor.clear();
        electricityEditor.clear();

        waterEditor.apply();
        electricityEditor.apply();

        myTableLayout.removeAllViews();
        Toast.makeText(this,"Datos Borrados",Toast.LENGTH_SHORT).show();

    }
}
