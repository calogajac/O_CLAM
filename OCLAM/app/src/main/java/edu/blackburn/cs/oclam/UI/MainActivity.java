package edu.blackburn.cs.oclam.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.blackburn.cs.oclam.Database.TaskList;
import edu.blackburn.cs.oclam.R;

/**
 * This class is the start of the application where the program begins when first launched
 */
public class MainActivity extends AppCompatActivity {

    //The context on the project
    public static Context oclamContext;
    //The list of tasks for the ListView
    public static TaskList taskList;
    //The ListView we will reference
    public ListView tasksListing;
    //The adapter that will take in whatever ArrayList it's given
    public ArrayAdapter<String> adapter;

    /**
     * Standard onCreate method for the creation of the application
     * Initializes the starting layout of the application
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        oclamContext = getApplicationContext();
        //Creates new TaskList
        taskList = new TaskList();
        //Referencing the ListView that is on MainActivity
        tasksListing = (ListView)findViewById(R.id.listView);
        //Creates our adapter that takes in the taskNames list
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                TaskList.taskNames);
        //Sets the recently created adapter to the ListView and populates it
        tasksListing.setAdapter(adapter);
        tasksListing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = (String)parent.getItemAtPosition(position);
                Intent toView = new Intent(MainActivity.this, ViewTask.class);
                toView.putExtra("data", data);
                MainActivity.this.startActivity(toView);
            }
        });
    }

    /**
     * Initializes the Options Menu for the application
     * Came with the project when created
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Initializes the Item Selected options
     * Came with the project when created
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * The method for the TaskCreationForm creation button
     * When the button is pressed this button starts the creation of the form
     * @param view: the context of the click
     */
    public void onClickCreateButton(View view){
        Intent ourIntent = new Intent(MainActivity.this, TaskCreationForm.class);
        MainActivity.this.startActivity(ourIntent);
        //Clears the adapter that is set to our ListView so that it can be repopulated when the
        //user returns to MainActivity. This has to be done. Otherwise, the same task will appear
        //in the ListView multiple times
        adapter.clear();
    }
}
