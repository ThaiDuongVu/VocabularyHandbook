package com.thaiduong.vocabularyhandbook;

import android.content.SharedPreferences;

class Word {
    private String word;
    private String definition;
    private String synonyms;
    private String antonyms;
    private String collocations;
    private String examples;

    private boolean verb;
    private boolean noun;
    private boolean adj;
    private boolean adverb;
    private boolean formal;

    void setString(String[] wordProperties) {
        word = wordProperties[0];
        definition = wordProperties[1];
        synonyms = wordProperties[2];
        antonyms = wordProperties[3];
        collocations = wordProperties[4];
        examples = wordProperties[5];
    }

    void setBool(boolean[] wordProperties) {
        verb = wordProperties[0];
        noun = wordProperties[1];
        adj = wordProperties[2];
        adverb = wordProperties[3];
        formal = wordProperties[4];
    }

    void save(SharedPreferences sharedPreferences, int index) {
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
