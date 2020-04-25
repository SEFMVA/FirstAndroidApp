package com.example.proj1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proj1.persons.PersonsListContent;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent=getIntent();
        if(intent!=null){
            PersonsListContent.Person person=intent.getParcelableExtra(MainActivity.detailsActivityMessage);

            if(person!=null){
                TextView nameDetail=findViewById(R.id.nameDetail);
                ImageView avatarDetail=findViewById(R.id.avatarDetail);
                TextView numberDetail=findViewById(R.id.numberDetail);
                TextView dateDetail=findViewById(R.id.dateDetail);


                Drawable avatar= getDrawable(getResources().getIdentifier(person.picPath, "drawable", getPackageName()));
                avatarDetail.setImageDrawable(avatar);
                nameDetail.setText(String.format("%s %s", person.name, person.surname));
                numberDetail.setText(String.format("Phone number: %s", person.phoneNumber));
                dateDetail.setText(String.format("Birthday: %s", person.date));

            }
        }
    }
}
