package com.example.photos09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class CreateAlbum extends AppCompatActivity {
    private EditText enterAlbumNameText;
    private Button createAlbumButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_album);
        enterAlbumNameText = (EditText) findViewById(R.id.enterAlbumNameText);
        createAlbumButton = (Button) findViewById(R.id.createAlbumButton);
        createAlbumButton.setText("Create Album");
        createAlbumButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Album newAlbum=  new Album(enterAlbumNameText.getText().toString().trim());
                try {
                    Photos.user.addAlbum(newAlbum);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent switchActivityIntent = new Intent(CreateAlbum.this,Photos.class);
                startActivity(switchActivityIntent);
            }
        });
    }
    private void goBack() throws IOException {
        Album newAlbum=  new Album(enterAlbumNameText.getText().toString().trim());
       Photos.user.addAlbum(newAlbum);
        Intent switchActivityIntent = new Intent(this,Photos.class);
        startActivity(switchActivityIntent);
    }
}