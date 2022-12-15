package com.example.photos09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class EditTags extends AppCompatActivity {
    private ListView listView;
    private Button addbutton;
    private EditText personOrLocation;
    private EditText value;
    ArrayList<Tag> listItems=new ArrayList<Tag>();
    ArrayAdapter<Tag> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tags);
        value = (EditText) findViewById(R.id.editTextTextPersonName);
        personOrLocation = (EditText) findViewById(R.id.editTextTextPersonName2);
        addbutton=(Button)findViewById(R.id.button);
        listView=(ListView)findViewById(R.id.listView);
        listItems=OpenAlbum.passPhoto.getTags();
        adapter=new ArrayAdapter<Tag>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        listView.setAdapter(adapter);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(personOrLocation.getText().toString().trim().equalsIgnoreCase("person") ||personOrLocation.getText().toString().trim().equalsIgnoreCase("person")){
                        OpenAlbum.passPhoto.addTag(personOrLocation.getText().toString().trim(),value.getText().toString().trim());
                    }else{
                        Toast.makeText(EditTags.this, "Incorrect tag name, must be Person or Location", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent switchActivityIntent = new Intent(EditTags.this,OpenAlbum.class);
                startActivity(switchActivityIntent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Tag toDelete = (Tag) listView.getItemAtPosition(i);
                try {
                    OpenAlbum.passPhoto.deleteTag(toDelete);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent switchActivityIntent = new Intent(EditTags.this,OpenAlbum.class);
                startActivity(switchActivityIntent);



            }
        });
    }
}