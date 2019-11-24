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
        newWord.word = wordEditText.getText().toString();
        newWord.definition = definitionEditText.getText().toString();
        newWord.synonyms = synonymsEditText.getText().toString();
        newWord.antonyms = antonymsEditText.getText().toString();
        newWord.collocations = collocationsEditText.getText().toString();
        newWord.examples = examplesEditText.getText().toString();

        newWord.verb = verbCheckBox.isChecked();
        newWord.noun = nounCheckBox.isChecked();
        newWord.adj = adjCheckBox.isChecked();
        newWord.adverb = adverbCheckBox.isChecked();

        newWord.formal = formalSwitch.isChecked();

        newWord.saveWord(sharedPreferences, numberOfWords);
        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();

        if (!saveButtonClicked) {
            numberOfWords++;
            sharedPreferences.edit().putInt("NumberOfWords", numberOfWords).apply();
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
