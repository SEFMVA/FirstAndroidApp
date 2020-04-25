package com.example.proj1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proj1.persons.PersonsListContent;

public class MainActivity extends AppCompatActivity implements PersonFragment.OnListFragmentInteractionListener,
CallDialog.OnCallDialogInteractionListener,
DeleteDialog.OnDeleteDialogInteractionListener{

    public final static String detailsActivityMessage="personData";
    public final static int newPersonRequest=1;
    private int currentPersonPosition=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addPerson(View view) {
        Intent intent =new Intent(this,AddPersonActivity.class);
        startActivityForResult(intent,newPersonRequest);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==newPersonRequest){
                PersonsListContent.addItem(new PersonsListContent.Person("Person."+PersonsListContent.ITEMS.size()+1,
                        data.getStringExtra("name"),
                        data.getStringExtra("surname"),
                        data.getStringExtra("date"),
                        data.getStringExtra("phoneNumber")));

                ((PersonFragment)getSupportFragmentManager().findFragmentById(R.id.PersonFragment)).notifyDataChange();

            }
        }
    }


    @Override
    public void onListFragmentClickInteraction(PersonsListContent.Person person, int position) {
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            Intent intent =new Intent(this,DetailsActivity.class);
            intent.putExtra(detailsActivityMessage,person);
            startActivity(intent);
        }else{
            DetailsFragment detailsFragment=(DetailsFragment)getSupportFragmentManager().findFragmentById(R.id.mainActivityDetailsFragment);
            if(detailsFragment!=null){
                showPerson(person);

            }

        }


    }

    public void showPerson(PersonsListContent.Person person){
        if(person!=null){
            TextView nameDetail=findViewById(R.id.nameDetail);
            ImageView avatarDetail=findViewById(R.id.avatarDetail);
            TextView numberDetail=findViewById(R.id.numberDetail);
            TextView dateDetail=findViewById(R.id.dateDetail);


            Drawable avatar= getDrawable(getResources().getIdentifier(person.picPath, "drawable", getPackageName()));
            avatarDetail.setImageDrawable(avatar);
            nameDetail.setText(String.format("%s %s", person.name, person.surname));
            numberDetail.setText(person.phoneNumber);
            dateDetail.setText(person.date);

        }
    }

    @Override
    public void onListFragmentLongClickInteraction(int position) {
        CallDialog.newInstance(PersonsListContent.ITEMS.get(position).name).show(getSupportFragmentManager(),getString(R.string.call_dialog_tag));

    }

    @Override
    public void onDeleteButtonClickInteraction(PersonsListContent.Person mItem, int position) {
        currentPersonPosition=position;
        DeleteDialog.newInstance().show(getSupportFragmentManager(),getString(R.string.delete_dialog_tag));
    }

    @Override
    public void onCallDialogPositiveClick(DialogFragment dialog) {

    }

    @Override
    public void onCallDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onDeleteDialogPositiveClick(DialogFragment dialog) {
        if(currentPersonPosition!=-1 && currentPersonPosition<PersonsListContent.ITEMS.size()){
            PersonsListContent.removeItem(currentPersonPosition);
            ((PersonFragment)getSupportFragmentManager().findFragmentById(R.id.PersonFragment)).notifyDataChange();
        }
    }

    @Override
    public void onDeleteDialogNegativeClick(DialogFragment dialog) {
        currentPersonPosition=-1;
        Toast.makeText(this,"Delete was cancelled.",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
}
