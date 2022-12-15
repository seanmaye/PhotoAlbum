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
    private AutoCompleteTextView autoComplete;
    private Button buttonSearch;
    private ListView listView;
    private AutoCompleteTextView autoComplete2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        checkBox=(CheckBox) findViewById(R.id.checkBox);
        checkBox2=(CheckBox) findViewById(R.id.checkBox2);
        autoComplete=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        buttonSearch=(Button)findViewById(R.id.buttonSearch);
        listView=(ListView)findViewById(R.id.listView);
        autoComplete2=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView2);
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
                    (this,android.R.layout.select_dialog_item, User.readApp().getPossibleTags(true,false));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        autoComplete.setThreshold(1);
        autoComplete.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = null;
        try {
            adapter2 = new ArrayAdapter<String>
                    (this,android.R.layout.select_dialog_item, User.readApp().getPossibleTags(false,true));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        autoComplete2.setThreshold(1);
        autoComplete2.setAdapter(adapter2);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //and
                 if(checkBox.isChecked()){
                    if(autoComplete.getText().toString().trim().length()==0) {
                        Toast.makeText(Search.this, "No Value entered", Toast.LENGTH_SHORT).show();
                    return;
                    }else{
                            ArrayList<Photo>matches=new ArrayList<>();
                            String tagName1="location";
                            String tagValue1=autoComplete2.getText().toString().trim();
                            String tagName2="person";
                            String tagValue2=autoComplete.getText().toString().trim();
                            for (Album a : Photos.user.getAlbumList()) {
                                for (Photo p : a.getPhotos()) {
                                    boolean flag1 = false;
                                    boolean flag2 = false;
                                    for (Tag t : p.getTags()) {
                                        if (t.getTagName().equalsIgnoreCase(tagName1) && t.getTagValue().equalsIgnoreCase(tagValue1)) {
                                            flag1 = true;
                                        } else if (t.getTagName().equals(tagName2) && t.getTagValue().equals(tagValue2)) {
                                            flag2 = true;
                                        }
                                    }
                                    if (flag1 && flag2) {
                                        if (matches.contains(p)) {
                                            continue;
                                        } else {
                                            matches.add(p);
                                        }
                                    }
                                }
                            }
                            ListViewAdapter adapter = new ListViewAdapter(Search.this, matches);
                            listView.setAdapter(adapter);

                    }
                    //or
                }else if(checkBox2.isChecked()){
                    if(autoComplete.getText().toString().trim().length()==0) {
                        Toast.makeText(Search.this, "No Value entered", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        ArrayList<Photo>matches=new ArrayList<>();
                        String tagName1="location";
                        String tagValue1=autoComplete2.getText().toString().trim();
                        String tagName2="person";
                        String tagValue2=autoComplete.getText().toString().trim();
                        for (Album a : Photos.user.getAlbumList()) {
                            for (Photo p : a.getPhotos()) {
                                boolean flag1 = false;
                                boolean flag2 = false;
                                for (Tag t : p.getTags()) {
                                    if (t.getTagName().equalsIgnoreCase(tagName1) && t.getTagValue().equalsIgnoreCase(tagValue1)) {
                                        flag1 = true;
                                    } else if (t.getTagName().equals(tagName2) && t.getTagValue().equals(tagValue2)) {
                                        flag2 = true;
                                    }
                                }
                                if (flag1 || flag2) {
                                    if (matches.contains(p)) {
                                        continue;
                                    } else {
                                        matches.add(p);
                                    }
                                }
                            }
                        }
                        ListViewAdapter adapter = new ListViewAdapter(Search.this, matches);
                        listView.setAdapter(adapter);
                    }
                    //one
                }else{
                    if((autoComplete.getText().toString().trim().length())!=0 && (autoComplete2.getText().toString().trim().length()!=0)){
                         Toast.makeText(Search.this, "Only Search for one tag if AND or OR is not selected", Toast.LENGTH_SHORT).show();
                         return;
                     }else if(autoComplete.getText().toString().trim().length()!=0){
                        ArrayList<Photo> ret = null;
                        try {
                            ret = User.readApp().getSearchResults(true,false,autoComplete.getText().toString().trim());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        ListViewAdapter adapter = new ListViewAdapter(Search.this, ret);
                        listView.setAdapter(adapter);
                     }else if(autoComplete2.getText().toString().trim().length()!=0){
                        ArrayList<Photo> ret = null;
                        try {
                            ret = User.readApp().getSearchResults(false,true,autoComplete2.getText().toString().trim());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        ListViewAdapter adapter = new ListViewAdapter(Search.this, ret);
                        listView.setAdapter(adapter);
                    }
                }

            }
        });

    }
}