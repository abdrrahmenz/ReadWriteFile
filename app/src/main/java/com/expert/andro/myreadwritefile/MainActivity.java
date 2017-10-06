package com.expert.andro.myreadwritefile;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnNew, btnOpen, btnSave;
    EditText editText, editTitle;

    File path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNew = (Button) findViewById(R.id.button_new);
        btnOpen = (Button) findViewById(R.id.button_open);
        btnSave = (Button) findViewById(R.id.button_save);
        editText = (EditText) findViewById(R.id.editFile);
        editTitle = (EditText) findViewById(R.id.editTitle);

        btnNew.setOnClickListener(this);
        btnOpen.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        path = getFilesDir();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.button_new:
                newFile();
                break;
            case R.id.button_open:
                openFile();
                break;
            case R.id.button_save:
                saveFile();
                break;
        }
    }

    private void newFile() {
        editTitle.setText("");
        editText.setText("");
        Toast.makeText(this, "Clearing File", Toast.LENGTH_SHORT).show();
    }

    private void openFile() {
        showList();
    }

    private void saveFile() {
     if (editTitle.getText().toString().isEmpty()){
         Toast.makeText(this, "Title harus diisi terlebih dahulu", Toast.LENGTH_SHORT).show();
     } else {
         String title = editTitle.getText().toString();
         String text = editText.getText().toString();

         FileHelper.writeToFile(title,text,this);

         Toast.makeText(this, "Saving "+editTitle.getText().toString()+" file", Toast.LENGTH_SHORT).show();
     }
    }

    private void showList() {
        final ArrayList<String> arrayList = new ArrayList<>();

        for (String file : path.list()) {
            arrayList.add(file);
        }

        final CharSequence[] items = arrayList.toArray(new CharSequence[arrayList.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilih file yg diinginkan");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int item) {
                loadData(items[item].toString());
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void loadData(String title) {
        String text = FileHelper.readFromFile(this,title);
        editTitle.setText(title);
        editText.setText(text);

        Toast.makeText(this, "Loading "+title+" data", Toast.LENGTH_SHORT).show();
    }
}
