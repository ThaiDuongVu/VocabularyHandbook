package com.thaiduong.vocabularyhandbook;

import android.content.SharedPreferences;

public class NewWord {
    public String word;
    public String definition;
    public String synonyms;
    public String antonyms;
    public String collocations;
    public String examples;

    public boolean verb;
    public boolean noun;
    public boolean adj;
    public boolean adverb;

    public boolean formal;

    public void saveWord(SharedPreferences sharedPreferences, int index) {
        // Save the properties of the word and the word itself to the system

        sharedPreferences.edit().putString("Word" + index, word).apply();
        sharedPreferences.edit().putString("Definition" + index, definition).apply();
        sharedPreferences.edit().putString("Synonyms" + index, synonyms).apply();
        sharedPreferences.edit().putString("Antonyms" + index, antonyms).apply();
        sharedPreferences.edit().putString("Collocations" + index, collocations).apply();
        sharedPreferences.edit().putString("Example" + index, examples).apply();

        sharedPreferences.edit().putBoolean("Verb" + index, verb).apply();
        sharedPreferences.edit().putBoolean("Noun" + index, noun).apply();
        sharedPreferences.edit().putBoolean("Adj" + index, adj).apply();
        sharedPreferences.edit().putBoolean("Adverb" + index, adverb).apply();

        sharedPreferences.edit().putBoolean("Formal" + index, formal).apply();
    }
}
