package com.thaiduong.vocabularyhandbook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditVocabActivity extends AppCompatActivity {

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

    private String[] wordPropertiesString = new String[6];
    private boolean[] wordPropertiesBool = new boolean[5];

    private int index;

    private boolean saveButtonClicked;

    private Word editWord;

    private Vibrator vibrator;
    private int vibratingDuration = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vocab);

        sharedPreferences = this.getSharedPreferences(getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        editWord = new Word();

        initialize();
        loadWord();
    }

    private void loadWord() {
        // Set the text views to display the word that is being edited

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

    public void onBackPressed() {
        // If the user exit without saving the word, there will be a warning message
        // If user says yes then exit, otherwise nothing happen

        if (!saveButtonClicked) {
            new AlertDialog.Builder(this)
                    .setMessage("Your word has not been saved, are you sure you want to go back?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                            vibrator.vibrate(vibratingDuration);
                            startActivityForResult(homeIntent, 0);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            vibrator.vibrate(vibratingDuration);
                            Toast.makeText(getApplicationContext(), "Nothing happened", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
        } else {
            Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivityForResult(homeIntent, 0);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        // When user press up button on action bar, behave the same as if they press the back button

        onBackPressed();
        return true;
    }

    public void onSaveButtonClicked(View view) {
        // Save the word to memory if save button is clicked

        // Haptic feedback
        vibrator.vibrate(vibratingDuration);

        wordPropertiesString[0] = wordEditText.getText().toString();
        wordPropertiesString[1] = definitionEditText.getText().toString();
        wordPropertiesString[2] = synonymsEditText.getText().toString();
        wordPropertiesString[3] = antonymsEditText.getText().toString();
        wordPropertiesString[4] = collocationsEditText.getText().toString();
        wordPropertiesString[5] = examplesEditText.getText().toString();

        wordPropertiesBool[0] = verbCheckBox.isChecked();
        wordPropertiesBool[1] = nounCheckBox.isChecked();
        wordPropertiesBool[2] = adjCheckBox.isChecked();
        wordPropertiesBool[3] = adverbCheckBox.isChecked();
        wordPropertiesBool[4] = formalSwitch.isChecked();

        editWord.setString(wordPropertiesString);
        editWord.setBool(wordPropertiesBool);

        editWord.save(sharedPreferences, index);
        // Display a message to let user know that word is saved successfully
        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();

        saveButtonClicked = true;
    }

    private void initialize() {
        // Link the variables to their widgets in the layout

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

        // Set display action bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
