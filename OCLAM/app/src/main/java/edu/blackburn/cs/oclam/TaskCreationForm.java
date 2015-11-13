package edu.blackburn.cs.oclam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

//This is a class that allows users to enter data into a form. The data passed into the form is
//created as a task

public class TaskCreationForm extends AppCompatActivity {

    /**
     * Standard onCreate method for the creation of the application
     * Initializes the starting layout of the application
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /*
     * Initiates the addTask() method and returns the user to MainActivity
     * @param view: the context of the click
     */
    public void create(View view){
        addTask(view);
        //Creates intent from this activity to MainActivity
        Intent lintent = new Intent(TaskCreationForm.this, MainActivity.class);
        //Starts the intent, taking the user back to MainActivity
        TaskCreationForm.this.startActivity(lintent);
    }

    /*
     * Returns the user to MainActivity
     * @param view: the context of the click
     */
    public void cancel(View view){
        //Creates intent from this activity to MainActivity
        Intent mintent = new Intent(TaskCreationForm.this, MainActivity.class);
        //Starts the intent, taking the user back to MainActivity
        TaskCreationForm.this.startActivity(mintent);
    }

    /*
     * Takes the text from each textview and uses the information to create a new task
     */
    public void addTask(View view){
        EditText taskName = (EditText)findViewById(R.id.name);
        EditText taskTag = (EditText)findViewById(R.id.tag);
        EditText taskDescription = (EditText)findViewById(R.id.description);
        //Creates new task using the name, description, and tag
        Task task = new Task(taskName.getText().toString(), taskDescription.getText().toString(),
                taskTag.getText().toString());
    }

}
