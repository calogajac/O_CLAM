package edu.blackburn.cs.oclam;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

/**
 * This class is the start of the application where the program begins when first launched
 */
public class MainActivity extends AppCompatActivity {

    //This variable is our DB helper that will have the methods for using our DB
    //Protected so the project can see it but not the world
    protected static DBOperations OCLAMDB;

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

        //Opening the DB method call
        openDB();
    }

    /**
     * This method keeps our onCreate method clear of unneeded code
     * Creates the DB helper and opens up our database for the application's use
     */
    private void openDB(){
        OCLAMDB = new DBOperations(this);
        OCLAMDB.open();
    }


    /**
     * onDestroy method is for any clean up work that needs to be done when we close the app
     * The database is closed in this step
     */
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //Calls the close DB method
        closeDB();
    }

    /**
     * This method keeps our onDestroy from having unneeded code
     * Calls the DB's close method
     */
    private void closeDB(){
        OCLAMDB.close();
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
    }
}
