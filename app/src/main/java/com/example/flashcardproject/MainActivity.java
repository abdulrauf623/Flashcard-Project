package com.example.flashcardproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    TextView flashcard_content;
    TextView solution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        solution = findViewById(R.id.solution);
        ImageView plusButton = findViewById(R.id.add_button);


        flashcard_content = findViewById(R.id.flashcard_content);

        findViewById(R.id.flashcard_content).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                solution.setVisibility(View.VISIBLE);
                flashcard_content.setVisibility(View.INVISIBLE);


                Toast.makeText(MainActivity.this, "I clicked this question", Toast.LENGTH_SHORT).show();
                Log.i("abdul ", "entered question on click method");

            }
        });


        findViewById(R.id.solution).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solution.setVisibility(View.INVISIBLE);
                flashcard_content.setVisibility(View.VISIBLE);

            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_Flashcard.class);
                startActivityForResult(intent, 100);
//                MainActivity.this.startActivity(intent);
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode,data);

        if (requestCode == 100){
            if (data != null){

                String newQuestion = data.getExtras().getString("QUESTION_KEY");
                String newAnswer = data.getExtras().getString("ANSWER_KEY");
                flashcard_content.setText(newQuestion);
                solution.setText(newAnswer);


            }
        }
    }

}