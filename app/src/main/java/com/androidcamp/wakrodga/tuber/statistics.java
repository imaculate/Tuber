package com.androidcamp.wakrodga.tuber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        String msg = "";
        switch (menuItem.getItemId()) {
            case R.id.edit:
                Toast.makeText(this, "EDITED", Toast.LENGTH_SHORT).show();
                break;
            case R.id.log_out:
                Toast.makeText(this, "LOGED_OUT", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
