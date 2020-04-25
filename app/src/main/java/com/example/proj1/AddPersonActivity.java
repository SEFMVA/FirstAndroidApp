package com.example.proj1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.proj1.persons.PersonsListContent;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddPersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
    }

    private String validate(String name, String surname, String date, String phoneNumber){
        if(name.isEmpty()){
            return "Invalid name";
        }
        if(surname.isEmpty()){
            return "Invalid surname";
        }

        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        try {
            dateFormat.setLenient(false);
            dateFormat.parse(date);
        }catch (ParseException pe){
            return "Invalid date";
        }

        if(phoneNumber.length()!=9 || phoneNumber.matches(".*\\D.*")){
            return "Invalid phone number";
        }

        return "valid";
    }

    public void addPerson(View view){
        EditText nameInput=findViewById(R.id.nameInput);
        EditText surnameInput=findViewById(R.id.surnameInput);
        EditText dateInput=findViewById(R.id.birthdayInput);
        EditText phoneNumberInput=findViewById(R.id.phoneNumberInput);


        String name=nameInput.getText().toString();
        String surname=surnameInput.getText().toString();
        String date=dateInput.getText().toString();
        String phoneNumber=phoneNumberInput.getText().toString();

        date=date.trim();
        phoneNumber=phoneNumber.trim();

        String valid=validate(name, surname, date, phoneNumber);
        if(valid.equals("valid")){
            Intent intent=new Intent();
            intent.putExtra("name",name);
            intent.putExtra("surname",surname);
            intent.putExtra("date",date);
            intent.putExtra("phoneNumber",phoneNumber);
            setResult(RESULT_OK,intent);
            finish();
        }else{
            Log.w("Adding error",valid);
        }

    }
}
