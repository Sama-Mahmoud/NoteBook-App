package com.example.project_iti_note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_iti_note.DATABASE.DBAdapter;
import com.example.project_iti_note.DATABASE.MODEL;

public class ShowDetails extends AppCompatActivity {

    TextView namenoteinfo , detailinfo ;
    Button edit , delete ;//, save;
    SharedPreferences sharedPreferences ;
    EditText editname , editdetails ;
    DBAdapter dbAdapter = new DBAdapter(ShowDetails.this);
    //DBAdapter dbAdapter = new DBAdapter(ShowDetails.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        namenoteinfo = findViewById(R.id.nameinfo_tv);
        detailinfo = findViewById(R.id.detailsinfo_tv);
        editname = findViewById(R.id.edit_note_name);
        editdetails = findViewById(R.id.edit_note_details);
        edit = findViewById(R.id.showdetals_edit_button);
        delete = findViewById(R.id.showdetals_delete_button);
       // save = findViewById(R.id.save_edit_show_details);
        sharedPreferences = getSharedPreferences("MY_FIRST_SHARED", Context.MODE_PRIVATE);
        namenoteinfo.setText(sharedPreferences.getString("NOTENAME","un known"));
        detailinfo.setText(sharedPreferences.getString("NOTDETAILS ","un known"));


       /* edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //  FragmentHandle(new EditFragment() , false , "editview");

            }
        });*/
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editname.getText().toString().isEmpty()&&!editdetails.getText().toString().isEmpty()){
                    // change note name and details
                    dbAdapter.edit(namenoteinfo.getText().toString() ,new MODEL( editname.getText().toString(), editdetails.getText().toString()));
                }
                else if(editname.getText().toString().isEmpty()&&!editdetails.getText().toString().isEmpty()){
                    //change note details only put the origenal name twice
                    dbAdapter.edit(namenoteinfo.getText().toString() ,new MODEL(namenoteinfo.getText().toString() ,editdetails.getText().toString()));
                }else if(!editname.getText().toString().isEmpty()&&editdetails.getText().toString().isEmpty()){
                    // edit note name only put the original not details wiyhout chang
                    dbAdapter.edit(namenoteinfo.getText().toString() ,new MODEL( editname.getText().toString() ,detailinfo.getText().toString()));
                }
                else{
                    Toast.makeText(ShowDetails.this, "PLEASE, enter note name or details to edit", Toast.LENGTH_SHORT).show();

                }
                Toast.makeText(ShowDetails.this, "updated successfully", Toast.LENGTH_SHORT).show();
                finish();
                //startActivity(new Intent(ShowDetails.this , MainActivity.class));
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAdapter.delete(namenoteinfo.getText().toString());
                Toast.makeText(ShowDetails.this, "deleted successfully", Toast.LENGTH_SHORT).show();
               // startActivity(new Intent(ShowDetails.this , MainActivity.class));
                finish();
               // dbAdapter.databaseHelper.deleteCourse(namenoteinfo.toString());
            }
        });


    }
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater menuInflater = getMenuInflater() ;
        menuInflater.inflate(R.menu.custumize_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.close_item:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
    /*public void FragmentHandle(Fragment fragment , Boolean addtobackstack , String tag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(fragmentManager.findFragmentByTag(tag)==null){

            fragmentTransaction.add(R.id.fragment_container,fragment,tag);
            if(addtobackstack) {
                fragmentTransaction.addToBackStack("ddd");
            }
            fragmentTransaction.commit();
        }else{
            fragmentTransaction.show(fragmentManager.findFragmentByTag(tag));
        }

    }*/
}