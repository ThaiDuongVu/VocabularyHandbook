package com.thaiduong.vocabularyhandbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    public void onSaveButtonClicked(View view)
    {
        variableAssigments();
    }

    private void initialize()
    {
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
    }

    private void variableAssigments()
    {
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
    }
}
