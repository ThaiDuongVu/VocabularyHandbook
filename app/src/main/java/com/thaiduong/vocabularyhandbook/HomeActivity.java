package com.thaiduong.vocabularyhandbook;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = this.getSharedPreferences(getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        int numberOfWords = sharedPreferences.getInt("NumberOfWords", 0);

        for (int i = 0; i < numberOfWords; i++)
        {
            addExistingWords(i);
        }
    }

    private void addExistingWords(int index)
    {
        LinearLayout linearLayout = findViewById(R.id.linearLayoutUp);

        TextView word = new TextView(this);
        TextView definition = new TextView(this);

        Button editButton = new Button(this, null, 0, R.style.Widget_AppCompat_Button_Colored);

        word.setTextAppearance(this, R.style.TextAppearance_AppCompat);
        word.setText(sharedPreferences.getString("Word" + index, ""));
        word.setTextSize(20);
        word.setTypeface(null, Typeface.BOLD);

        definition.setTextAppearance(this, R.style.TextAppearance_AppCompat);
        definition.setText(sharedPreferences.getString("Definition" + index, ""));
        definition.setTextSize(18);

        editButton.setText(getResources().getString(R.string.edit_button));
        editButton.setTextSize(18);

        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent editIntent = new Intent(view.getContext(), EditText.class);
                startActivityForResult(editIntent, 0);
            }
        });

        linearLayout.addView(word);
        linearLayout.addView(definition);
        linearLayout.addView(editButton);
    }

    public void onNewButtonClicked(View view)
    {
        Intent newIntent = new Intent(view.getContext(), NewVocab.class);
        startActivityForResult(newIntent, 0);
    }
}
