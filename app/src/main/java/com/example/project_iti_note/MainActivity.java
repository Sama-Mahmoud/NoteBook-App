package com.example.project_iti_note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_iti_note.DATABASE.DBAdapter;
import com.example.project_iti_note.DATABASE.MODEL;
import com.example.project_iti_note.DATABASE.RVAdapter;

public class MainActivity extends AppCompatActivity {
    EditText notenametxt , notedetailstxt ;
    Button ADDbtn ;
    RVAdapter rvadapter ;
    RecyclerView recyclerView ;
    DBAdapter dbadabter = new DBAdapter(this) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notenametxt = findViewById(R.id.notename_et);
        notedetailstxt = findViewById(R.id.notedetails_et);
        recyclerView = findViewById(R.id.recyclerviewcontainer);
        ADDbtn = findViewById(R.id.addbtn);

        ADDbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!notenametxt.getText().toString().isEmpty()&&!notedetailstxt.getText().toString().isEmpty()) {

                    dbadabter.insertentery(new MODEL(notenametxt.getText().toString(),notedetailstxt.getText().toString())) ;
                    rvadapter = new RVAdapter(dbadabter.getallNotsname() , MainActivity.this) ;
                    recyclerView.setAdapter(rvadapter);
                    notenametxt.setText("");
                    notedetailstxt.setText("");

                }
                else {
                    Toast.makeText(MainActivity.this, "PLEASE, enter note name and details", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        rvadapter = new RVAdapter(dbadabter.getallNotsname(),MainActivity.this) ;
        recyclerView.setAdapter(rvadapter);
    }




}