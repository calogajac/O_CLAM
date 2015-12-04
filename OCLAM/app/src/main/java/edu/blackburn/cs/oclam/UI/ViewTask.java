package edu.blackburn.cs.oclam.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import edu.blackburn.cs.oclam.Database.DBOperations;
import edu.blackburn.cs.oclam.Database.Task;
import edu.blackburn.cs.oclam.R;

public class ViewTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String data = intent.getExtras().getString("data");
        TextView name = (TextView)findViewById(R.id.Name);
        TextView description = (TextView)findViewById(R.id.Description);
        TextView tag = (TextView)findViewById(R.id.Tag);
        name.setText(data);
        description.setText(MainActivity.taskList.getTaskDescription(data));
        tag.setText(MainActivity.taskList.getTaskTag(data));
    }

    public void edit(View view){
        //Creates intent from this activity to EditTask
        Intent tintent = new Intent(ViewTask.this, EditTask.class);
        //Starts the intent, taking the user back to EditTask
        ViewTask.this.startActivity(tintent);
    }

    public void delete(View view){
        //Creates intent from this activity to MainActivity
        Intent bintent = new Intent(ViewTask.this, MainActivity.class);
        //Starts the intent, taking the user back to MainActivity
        ViewTask.this.startActivity(bintent);
    }

    public void returnToMain(View view){
        //Creates intent from this activity to MainActivity
        Intent mintent = new Intent(ViewTask.this, MainActivity.class);
        //Starts the intent, taking the user back to MainActivity
        ViewTask.this.startActivity(mintent);
    }
}
