package com.thaiduong.vocabularyhandbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Vibrator vibrator;
    private int vibratingDuration = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = this.getSharedPreferences(getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        int numberOfWords = sharedPreferences.getInt("NumberOfWords", 0);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        for (int i = 0; i < numberOfWords; i++) {
            addExistingWords(i);
        }
    }

    private void addExistingWords(final int index) {
        LinearLayout linearLayout = findViewById(R.id.linearLayoutUp);

        TextView[] textViews = new TextView[7];

        TextView word = new TextView(this);
        Button editButton = new Button(this, null, 0, R.style.Widget_AppCompat_Button_Colored);

        word.setTextAppearance(this, R.style.TextAppearance_AppCompat);
        word.setText(sharedPreferences.getString("Word" + index, ""));
        word.setTextSize(20);
        word.setTypeface(null, Typeface.BOLD);

        for (int i = 0; i < textViews.length; i++) {
            textViews[i] = new TextView(this);
            textViews[i].setTextAppearance(this, R.style.TextAppearance_AppCompat);
            textViews[i].setTextSize(18);
        }

        String definition = "Definition: " + sharedPreferences.getString("Definition" + index, "");
        String synonyms = "Synonyms: " + sharedPreferences.getString("Synonyms" + index, "");
        String antonyms = "Antonyms: " + sharedPreferences.getString("Antonyms" + index, "");
        String collocations = "Collocations: " + sharedPreferences.getString("Collocations" + index, "");
        String examples = "Examples: " + sharedPreferences.getString("Example" + index, "");

        String type = "Type: ";
        if (sharedPreferences.getBoolean("Verb" + index, false)) {
            type += "Verb ";
        }
        if (sharedPreferences.getBoolean("Noun" + index, false)) {
            type += "Noun ";
        }
        if (sharedPreferences.getBoolean("Adj" + index, false)) {
            type += "Adj ";
        }
        if (sharedPreferences.getBoolean("Adverb" + index, false)) {
            type += "Adverb ";
        }
        String formality = "Formality: ";
        if (sharedPreferences.getBoolean("Formal" + index, false)) {
            formality += "Formal";
        } else {
            formality += "Informal";
        }

        textViews[0].setText(definition);
        textViews[1].setText(synonyms);
        textViews[2].setText(antonyms);
        textViews[3].setText(collocations);
        textViews[4].setText(examples);
        textViews[5].setText(type);
        textViews[6].setText(formality);

        editButton.setText(getResources().getString(R.string.edit_button));
        editButton.setTextSize(18);

        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                vibrator.vibrate(vibratingDuration);
                sharedPreferences.edit().putInt("EditIndex", index).apply();
                Intent editIntent = new Intent(view.getContext(), EditVocab.class);
                startActivityForResult(editIntent, 0);
            }
        });

        linearLayout.addView(word);
        for (int i = 0; i < textViews.length; i++) {
            linearLayout.addView(textViews[i]);
        }
        linearLayout.addView(editButton);
    }

    public void onNewButtonClicked(View view) {
        vibrator.vibrate(vibratingDuration);
        Intent newIntent = new Intent(view.getContext(), NewVocab.class);
        startActivityForResult(newIntent, 0);
    }
}
