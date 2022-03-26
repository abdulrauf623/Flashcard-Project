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

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    TextView flashcard_content;
    TextView solution;

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_content)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.solution)).setText(allFlashcards.get(0).getAnswer());
        }

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



        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allFlashcards.size() == 0)
                    return;
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if(currentCardDisplayedIndex >= allFlashcards.size()) {
                    Snackbar.make(flashcard_content,
                            "You've exhausted all the questions saved. We are moving to the very first question.",
                            Snackbar.LENGTH_SHORT)
                            .show();
                    currentCardDisplayedIndex = 0;
                }

                // set the question and answer TextViews with data from the database
                allFlashcards = flashcardDatabase.getAllCards();
                Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);

                ((TextView) findViewById(R.id.flashcard_content)).setText(flashcard.getAnswer());
                ((TextView) findViewById(R.id.solution)).setText(flashcard.getQuestion());
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
                flashcardDatabase.insertCard(new Flashcard(newQuestion, newAnswer));
                allFlashcards = flashcardDatabase.getAllCards();


            }
        }
    }


}