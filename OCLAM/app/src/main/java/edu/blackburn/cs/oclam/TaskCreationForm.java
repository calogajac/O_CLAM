package edu.blackburn.cs.oclam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class TaskCreationForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void create(View view){
        Intent lintent = new Intent(TaskCreationForm.this, MainActivity.class);
        TaskCreationForm.this.startActivity(lintent);
    }

    public void cancel(View view){
        Intent mintent = new Intent(TaskCreationForm.this, MainActivity.class);
        TaskCreationForm.this.startActivity(mintent);
    }

    public void addTask(View view){
        EditText taskName = (EditText)findViewById(R.id.name);
        EditText taskTag = (EditText)findViewById(R.id.tag);
        EditText taskDescription = (EditText)findViewById(R.id.description);

        long id = MainActivity.OCLAMDB.insertTask(taskName.getText().toString(), taskDescription.getText().toString(),
                taskTag.getText().toString(), 0);
    }

}
