package com.example.photos09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class Search extends AppCompatActivity {
    private CheckBox checkBox;
    private CheckBox checkBox2;
    AutoCompleteTextView autoComplete;
    private Button buttonSearch;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        checkBox=(CheckBox) findViewById(R.id.checkBox);
        checkBox2=(CheckBox) findViewById(R.id.checkBox2);
        autoComplete=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        buttonSearch=(Button)findViewById(R.id.buttonSearch);
        listView=(ListView)findViewById(R.id.listView);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()&&checkBox2.isChecked()){
                    Toast.makeText(Search.this, "Cannot have both checked at once", Toast.LENGTH_SHORT).show();
                    checkBox2.setChecked(false);
                }
            }

        });
        //person
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()&&checkBox2.isChecked()){
                    Toast.makeText(Search.this, "Cannot have both checked at once", Toast.LENGTH_SHORT).show();
                    checkBox.setChecked(false);
                }
            }
        });
        ArrayAdapter<String> adapter = null;
        try {
            adapter = new ArrayAdapter<String>
                    (this,android.R.layout.select_dialog_item, User.readApp().getPossibleTags(true,true));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        autoComplete.setThreshold(1);
        autoComplete.setAdapter(adapter);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()){
                    if(autoComplete.getText().toString().trim().length()==0) {
                        Toast.makeText(Search.this, "No Value entered", Toast.LENGTH_SHORT).show();
                    return;
                    }else{
                        try {
                            ArrayList<Photo> ret = User.readApp().getSearchResults(false,true,autoComplete.getText().toString().trim());
                            ListViewAdapter adapter = new ListViewAdapter(Search.this, ret);
                            listView.setAdapter(adapter);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                }else if(checkBox2.isChecked()){
                    if(autoComplete.getText().toString().trim().length()==0) {
                        Toast.makeText(Search.this, "No Value entered", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        try {
                            ArrayList<Photo> ret = User.readApp().getSearchResults(true,false,autoComplete.getText().toString().trim());
                            ListViewAdapter adapter = new ListViewAdapter(Search.this, ret);
                            listView.setAdapter(adapter);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    Toast.makeText(Search.this, "No tag selected", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });

    }
}