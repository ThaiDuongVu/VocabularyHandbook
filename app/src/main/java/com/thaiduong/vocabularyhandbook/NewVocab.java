package com.thaiduong.vocabularyhandbook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class NewVocab extends AppCompatActivity {

    private EditText wordEditText;
    private EditText definitionEditText;
    private EditText synonymsEditText;
    private EditText antonymsEditText;
    private EditText collocationsEditText;
    private EditText examplesEditText;

    private CheckBox verbCheckBox;
    private CheckBox nounCheckBox;
    private CheckBox adjCheckBox;
    private CheckBox adverbCheckBox;

    private Switch formalSwitch;

    private int numberOfWords;

    private SharedPreferences sharedPreferences;

    private NewWord newWord;

    private boolean saveButtonClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_vocab);

        saveButtonClicked = false;
        newWord = new NewWord();
        initialize();
    }

    public void onSaveButtonClicked(View view) {
        String word = wordEditText.getText().toString();
        String definition = definitionEditText.getText().toString();
        String synonyms = synonymsEditText.getText().toString();
        String antonyms = antonymsEditText.getText().toString();
        String collocations = collocationsEditText.getText().toString();
        String examples = examplesEditText.getText().toString();

        boolean verb = verbCheckBox.isChecked();
        boolean noun = nounCheckBox.isChecked();
        boolean adj = adjCheckBox.isChecked();
        boolean adverb = adverbCheckBox.isChecked();

        boolean formal = formalSwitch.isChecked();

        newWord.word = word;
        newWord.definition = definition;
        newWord.synonyms = synonyms;
        newWord.antonyms = antonyms;
        newWord.collocations = collocations;
        newWord.examples = examples;

        newWord.verb = verb;
        newWord.noun = noun;
        newWord.adj = adj;
        newWord.adverb = adverb;

        newWord.formal = formal;

        if (!saveButtonClicked) {
            newWord.saveWord(sharedPreferences, numberOfWords);

            numberOfWords++;
            sharedPreferences.edit().putInt("NumberOfWords", numberOfWords).apply();

            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();

            saveButtonClicked = true;
        }
    }

    private void initialize() {
        wordEditText = findViewById(R.id.wordEditText);
        definitionEditText = findViewById(R.id.definitionEditText);
        synonymsEditText = findViewById(R.id.synonymsEditText);
        antonymsEditText = findViewById(R.id.antonymsEditText);
        collocationsEditText = findViewById(R.id.collocationsEditText);
        examplesEditText = findViewById(R.id.examplesEditText);

        verbCheckBox = findViewById(R.id.verbCheckBox);
        nounCheckBox = findViewById(R.id.nounCheckBox);
        adjCheckBox = findViewById(R.id.adjCheckBox);
        adverbCheckBox = findViewById(R.id.adverbCheckBox);

        formalSwitch = findViewById(R.id.formalSwitch);

        sharedPreferences = this.getSharedPreferences(getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        numberOfWords = sharedPreferences.getInt("NumberOfWords", 0);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
