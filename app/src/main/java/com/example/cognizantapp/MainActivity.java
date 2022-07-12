package com.example.cognizantapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    EditText nameEditText;
    TextView contactTextView;
    ListView languagesListView;

    String[] languages = {"english", "spanish", "french"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = findViewById(R.id.etName);
        nameEditText.setOnFocusChangeListener(this);

        contactTextView = findViewById(R.id.tvContact);

        languagesListView = findViewById(R.id.lvLanguages);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1,languages);
        languagesListView.setAdapter(adapter);
    }

    public void clickHandler(View view) {
        switch(view.getId()){
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnCancel:
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456"));
                startActivity(dialIntent);
                break;
        }

    }

    private void login() {
        String name = nameEditText.getText().toString();
        //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        Intent homeIntent = new Intent(this,HomeActivity.class);
        homeIntent.putExtra("name",name);
        startActivityForResult(homeIntent,123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            contactTextView.setText(data.getExtras().get("conkey").toString());
        }
    }

    @Override
    public void onFocusChange(View view, boolean isFocused) {
        if(isFocused)
            Toast.makeText(this,"Got Focus",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Lost Focus",Toast.LENGTH_SHORT).show();
    }
}