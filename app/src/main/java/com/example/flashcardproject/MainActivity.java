package com.example.flashcardproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView solution = findViewById(R.id.solution);

        TextView flashcard_content = findViewById(R.id.flashcard_content);

        findViewById(R.id.flashcard_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solution.setVisibility(View.VISIBLE);
                flashcard_content.setVisibility(View.INVISIBLE);

            }
        });


        findViewById(R.id.solution).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solution.setVisibility(View.INVISIBLE);
                flashcard_content.setVisibility(View.VISIBLE);

            }
        });



    }

}