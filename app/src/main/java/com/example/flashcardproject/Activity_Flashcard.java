package com.example.flashcardproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DirectAction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Activity_Flashcard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

        ImageView saveButton = findViewById(R.id.save_this);
        ImageView quit = findViewById(R.id.times_button);

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent data = new Intent();

                String inputQuestion = ((EditText) findViewById(R.id.add_question)).getText().toString();
                String inputAnswer = ((EditText) findViewById(R.id.add_answer)).getText().toString();
                data.putExtra("QUESTION_KEY", inputQuestion);
                data.putExtra("ANSWER_KEY", inputAnswer);
                setResult(RESULT_OK, data);
                finish();


            }
        });

    }
}