package com.example.myapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "extra_message";
    public Integer backButtonCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MySingleton singleton = MySingleton.getInstance();

        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                if (extras.containsKey(EXTRA_MESSAGE)) {
                    String message = extras.getString(EXTRA_MESSAGE);
                    singleton.putMainMessage(message);
                }
            }
        }

        List<String> mDataset = singleton.getMainMessages();

        MessagesAdapter mAdapter = new MessagesAdapter(mDataset);
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, NextActivity.class);
        EditText editText = findViewById(R.id.message_input);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (backButtonCount >= 1) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }
}