package com.thaiduong.vocabularyhandbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditVocab extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

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

    private int index;

    private NewWord editWord;

    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vocab);

        sharedPreferences = this.getSharedPreferences(getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        editWord = new NewWord();

        initialize();
        loadWord();
    }

    private void loadWord() {
        wordEditText.setText(sharedPreferences.getString("Word" + index, ""));
        definitionEditText.setText(sharedPreferences.getString("Definition" + index, ""));
        synonymsEditText.setText(sharedPreferences.getString("Synonyms" + index, ""));
        antonymsEditText.setText(sharedPreferences.getString("Antonyms" + index, ""));
        collocationsEditText.setText(sharedPreferences.getString("Collocations" + index, ""));
        examplesEditText.setText(sharedPreferences.getString("Example" + index, ""));

        if (sharedPreferences.getBoolean("Verb" + index, false)) {
            verbCheckBox.setChecked(true);
        }
        if (sharedPreferences.getBoolean("Noun" + index, false)) {
            nounCheckBox.setChecked(true);
        }
        if (sharedPreferences.getBoolean("Adj" + index, false)) {
            adjCheckBox.setChecked(true);
        }
        if (sharedPreferences.getBoolean("Adverb" + index, false)) {
            adverbCheckBox.setChecked(true);
        }

        if (sharedPreferences.getBoolean("Formal" + index, false)) {
            formalSwitch.setChecked(true);
        }
    }

    public void saveWord(View view) {
        int vibratingDuration = 50;
        vibrator.vibrate(vibratingDuration);

        editWord.word = wordEditText.getText().toString();
        editWord.definition = definitionEditText.getText().toString();
        editWord.synonyms = synonymsEditText.getText().toString();
        editWord.antonyms = antonymsEditText.getText().toString();
        editWord.collocations = collocationsEditText.getText().toString();
        editWord.examples = examplesEditText.getText().toString();

        editWord.verb = verbCheckBox.isChecked();
        editWord.noun = nounCheckBox.isChecked();
        editWord.adj = adjCheckBox.isChecked();
        editWord.adverb = adverbCheckBox.isChecked();

        editWord.formal = formalSwitch.isChecked();

        editWord.saveWord(sharedPreferences, index);
        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
    }

    private void initialize() {
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

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

        index = sharedPreferences.getInt("EditIndex", 0);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
