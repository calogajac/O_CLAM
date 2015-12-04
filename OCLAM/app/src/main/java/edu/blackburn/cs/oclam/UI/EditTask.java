package edu.blackburn.cs.oclam.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import edu.blackburn.cs.oclam.R;

public class EditTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void saveEdit(View view){
        //Reference to the EditText that the user puts their name in
        EditText taskName = (EditText)findViewById(R.id.name);
        //EditText that the user puts the tag in
        EditText taskTag = (EditText)findViewById(R.id.tag);
        //EditText that the user puts the task description in
        EditText taskDescription = (EditText)findViewById(R.id.description);
        //MainActivity.taskList.updateTask(taskName.getText().toString(),
                //taskDescription.getText().toString(), taskTag.getText().toString());
    }

    public void cancel(View view){
        //Creates intent from this activity to MainActivity
        Intent mintent = new Intent(EditTask.this, MainActivity.class);
        //Starts the intent, taking the user back to MainActivity
        EditTask.this.startActivity(mintent);
    }
}
