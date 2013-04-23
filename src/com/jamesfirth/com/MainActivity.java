package com.jamesfirth.com;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.jamesfirth.bgtm.MESSAGE";
	AlertDialog.Builder alert;
	public static final int MAX_PLAYERS = 8;
	protected NumberPicker np;
	public Activity ma;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Create and display the alert dialog
        alert = new AlertDialog.Builder(MainActivity.this);

        alert.setTitle(R.string.enter_player_numbers);

        final NumberPicker np = new NumberPicker(MainActivity.this);
        String[] nums = new String[MAX_PLAYERS+1];
        for(int i=0; i<nums.length; i++)
               nums[i] = Integer.toString(i+1);

        np.setMinValue(1);
        np.setMaxValue(nums.length-1);
        np.setWrapSelectorWheel(true);
        np.setDisplayedValues(nums);
        np.setValue(4);

        //Set listeners
        
        //save num players
        alert.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
          Intent intent = new Intent(getApplicationContext(), EnterNamesActivity.class);
          String message = Integer.toString(np.getValue());
          intent.putExtra(EXTRA_MESSAGE, message);
          startActivity(intent);
          }
        });

        //Display cancel message.
        alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int whichButton) {
        	  Toast.makeText(getApplicationContext(), R.string.canceled, Toast.LENGTH_SHORT).show();
          }
        });
        
        
        //displays alert dialog
        alert.setView(np);
    }

    public void pnAlert(View view)
    {
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
