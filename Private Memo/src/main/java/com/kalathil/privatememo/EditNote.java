package com.kalathil.privatememo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

import static com.kalathil.privatememo.MyNotes.arrayAdapter;

public class EditNote extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        final EditText editText = (EditText) findViewById(R.id.editText);
        Intent intent = getIntent();
        int noteId = intent.getIntExtra("noteId",-1);

        if(noteId!=-1) {
            editText.setText(MyNotes.notes.get(noteId));
        }else{
            MyNotes.notes.add("");
            noteId=MyNotes.notes.size()-1;
            arrayAdapter.notifyDataSetChanged();

        }

        final int finalNoteId = noteId;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MyNotes.notes.set(finalNoteId,charSequence.toString());
                MyNotes.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.privatememo", Context.MODE_PRIVATE);

                HashSet<String> set = new HashSet(MyNotes.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        }



    }

