package com.example.photos09;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenAlbum extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListView listView;
    private ImageView imageView;
    private TextView textViewTags;
    private Button addPhotoButton;
    private Button editTagsButton;
    private Button deletePhotoButton;
    private Button goLeftButton;
    private Button goRightButton;
    private Button movePhotoButton;
    private Button button3;
    private ArrayList<ImageView> listImages = new ArrayList<ImageView>();
    private Button button4;
    static int passIndex=0;
    static Photo passPhoto;
    private TextView textView9;
    private String m_Text = "";
    ArrayAdapter<ImageView> adapter;
    static int displayPhoto=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_album);
        imageView = (ImageView) findViewById(R.id.imageView);
        addPhotoButton = (Button) findViewById(R.id.addPhotoButton);
        textViewTags = (TextView) findViewById(R.id.textViewTags);
        textView9 = (TextView)findViewById(R.id.textView9);
        goLeftButton = (Button) findViewById(R.id.goLeftButton);
        goRightButton = (Button) findViewById(R.id.goRightButton);
        listView = (ListView) findViewById(R.id.listView);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        ListViewAdapter adapter = new ListViewAdapter(this, Photos.passAlbum.getPhotos());
        listView.setAdapter(adapter);
        textView9.setText(Photos.passAlbum.getName());
        if (Photos.passAlbum.getPhotos().size() != 0) {
            imageView.setImageURI(Photos.passAlbum.getPhotos().get(displayPhoto).getUri());
            String tags = "";
            for (Tag tag : Photos.passAlbum.getPhotos().get(displayPhoto).getTags()) {
                tags += tag.getTagValue() + ",";
            }
            if (tags.length() > 1) {
                tags = tags.substring(0, tags.length() - 1);
                textViewTags.setText(tags);
            } else {
                textViewTags.setText("");
            }

        }

        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(OpenAlbum.this, AddPhoto.class);
                startActivity(switchActivityIntent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(OpenAlbum.this, Photos.class);
                startActivity(switchActivityIntent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OpenAlbum.this);
                builder.setTitle("Enter New Title");


                final EditText input = new EditText(OpenAlbum.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                builder.setView(input);


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        try {
                            Photos.passAlbum.setName(m_Text);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        textView9.setText(Photos.passAlbum.getName());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                passPhoto = (Photo) listView.getItemAtPosition(i);
                passIndex=i;
                Intent switchActivityIntent = new Intent(OpenAlbum.this,Popup.class);
                startActivity(switchActivityIntent);



            }
        });
        goLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(displayPhoto>0){
                    displayPhoto--;
                    if (Photos.passAlbum.getPhotos().size() != 0) {
                        imageView.setImageURI(Photos.passAlbum.getPhotos().get(displayPhoto).getUri());
                        String tags = "";
                        for (Tag tag : Photos.passAlbum.getPhotos().get(displayPhoto).getTags()) {
                            tags += tag.getTagValue() + ",";
                        }
                        if (tags.length() > 1) {
                            tags = tags.substring(0, tags.length() - 1);
                            textViewTags.setText(tags);
                        } else {
                            textViewTags.setText("");
                        }

                    }
                }
            }
        });
        goRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(displayPhoto<Photos.passAlbum.getPhotos().size()-1){
                    displayPhoto++;
                    if (Photos.passAlbum.getPhotos().size() != 0) {
                        imageView.setImageURI(Photos.passAlbum.getPhotos().get(displayPhoto).getUri());
                        String tags = "";
                        for (Tag tag : Photos.passAlbum.getPhotos().get(displayPhoto).getTags()) {
                            tags += tag.getTagValue() + ",";
                        }
                        if (tags.length() > 1) {
                            tags = tags.substring(0, tags.length() - 1);
                            textViewTags.setText(tags);
                        } else {
                            textViewTags.setText("");
                        }

                    }
                }
            }
        });
    }
}