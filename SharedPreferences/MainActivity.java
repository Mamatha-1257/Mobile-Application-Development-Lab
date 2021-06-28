package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;


public class MainActivity extends AppCompatActivity {
    private EditText name, age;
    private RadioButton radiomale, radiofemale;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.edit1);
        age = findViewById(R.id.edit2);
        radiofemale = findViewById(R.id.radioFemale);
        radiomale = findViewById(R.id.radioMale);
        prefs = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        radiofemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean female_isChecked) {
                genderSaveToSharedPreferences("gender_female", female_isChecked);
            }
        });

        radiomale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean male_isChecked) {
                genderSaveToSharedPreferences("gender_male", male_isChecked);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        String s1 = prefs.getString("name", "");
        int a = prefs.getInt("age", 0);
        name.setText(s1);
        age.setText(String.valueOf(a));
        radiofemale.setChecked(genderUpdate("gender_female"));
        radiomale.setChecked(genderUpdate("gender_male"));
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor myEdit = prefs.edit();
        myEdit.putString("name", name.getText().toString());
        myEdit.putInt("age", Integer.parseInt(age.getText().toString()));

        myEdit.apply();
    }
    private void genderSaveToSharedPreferences(String key, boolean value){
        SharedPreferences.Editor myEdit = prefs.edit();
        myEdit.putBoolean(key,value);
        myEdit.apply();
    }
    private boolean genderUpdate(String key){
        return prefs.getBoolean(key,false);
    }
}
