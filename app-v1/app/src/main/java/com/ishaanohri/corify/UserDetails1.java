package com.ishaanohri.corify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.Calendar;

public class UserDetails1 extends AppCompatActivity {

    private MaterialButton nextButton;
    private EditText nameEditText;
    private TextView ageEditText;
    public static String name = "", dob = "" , age = "", gender = "",  bloodGroup = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details1);

        nextButton = findViewById(R.id.nextButton);
        ageEditText = findViewById(R.id.ageEditText);
        nameEditText = findViewById(R.id.nameEditText);
        final Spinner dropdown = findViewById(R.id.bloodSpinner);
        final Spinner dropdown2 = findViewById(R.id.genderSpinner);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nameEditText.getText().toString().trim();
                dob = ageEditText.getText().toString().trim();
                gender = dropdown.getSelectedItem().toString();
                bloodGroup = dropdown2.getSelectedItem().toString();
                
                if(!name.equals("") && !dob.equals("DD/MM/YYYY") && !gender.equals("") && !bloodGroup.equals(""))
                {
                    String x[] = dob.split("/");

                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
                    int currentDate = Calendar.getInstance().get(Calendar.DATE);

                    int year = Integer.parseInt(x[2]);
                    int month = Integer.parseInt(x[1]);
                    int date = Integer.parseInt(x[0]);

                    int month_days[] = { 31, 28, 31, 30, 31, 30, 31,
                            31, 30, 31, 30, 31 };

                    if (date > currentDate) {
                        currentMonth = currentMonth - 1;
                        currentDate = currentDate + month_days[month - 1];
                    }

                    if (month > currentMonth) {
                        currentYear = currentYear - 1;
                        currentMonth = currentMonth + 12;
                    }

                    int calculated_year = currentYear - year;

                    age = String.valueOf(calculated_year);

                    Log.i("INFO",name);
                    Log.i("INFO",dob);
                    Log.i("INFO",age);
                    Log.i("INFO",gender);
                    Log.i("INFO",bloodGroup);

                    Intent intent = new Intent(UserDetails1.this, UserDetails2.class);
                    startActivity(intent);
                }
                else 
                {
                    Toast.makeText(UserDetails1.this, "Enter all details", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ageEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                View view = getCurrentFocus();
                if (view == null) {
                    view = new View(getApplicationContext());
                }
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                DatePickerDialog dpd = new DatePickerDialog(UserDetails1.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String date = dayOfMonth + "/" + (monthOfYear+1) + "/" + year;
                        ageEditText.setText(date);
                    }
                },      Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                dpd.show();

                Button ok = dpd.getButton(DialogInterface.BUTTON_POSITIVE);
                ok.setBackgroundColor(Color.parseColor("#FFFFFF"));
                ok.setTextColor(getColor(R.color.colorAccent));

                Button cancel = dpd.getButton(DialogInterface.BUTTON_NEGATIVE);
                cancel.setBackgroundColor(Color.parseColor("#FFFFFF"));
                cancel.setTextColor(getColor(R.color.colorAccent));

            }
        });

        String[] items = new String[]{"A +", "A -", "B +", "B -", "O +", "O -", "AB +", "AB -"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        String[] items2 = new String[]{"MALE", "FEMALE", "OTHER"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
    }
}
