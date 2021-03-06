package com.jamesfirth.com;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class TurnActivity extends Activity {
	AlertDialog.Builder alert;
	private static int currentStep;
	private static String[] names;
	private static int num_players;
	private static int currentPlayer;
	private static String[] steps;
	private static boolean first_time;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_turn);
		Intent incIntent = getIntent();
		String temp = incIntent.getStringExtra(EnterNamesActivity.EXTRA_MESSAGE);
		names = temp.split(";");		
		
		num_players = names.length;
		currentStep = 0;
		currentPlayer = num_players-1;
		this.setTitle(names[currentPlayer]);
		steps = getResources().getStringArray(R.array.arkham_steps);
		TextView textViewTemp = (TextView)findViewById(R.id.current_instructions);
		textViewTemp.setText(steps[currentStep]);
		
		
		for(int i=0; i < names.length; i++)
			System.out.println(names[i]);
		
        alert = new AlertDialog.Builder(TurnActivity.this);

        alert.setTitle(getString(R.string.instructions_title));
        
        alert.setPositiveButton(R.string.continuemsg, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
          //do something
        	first_time = true;
          }
        });
        
        TextView instructs = new TextView(this);
        instructs.setText(R.string.instructions);
          
        //displays alert dialog
        alert.setView(instructs);
        if(!first_time)
        	alert.show();
	}
	
	public void showHelp()
	{
		if(alert != null)
		{
			alert.show();
		}
	}
	
	public void onBackPressed()
	{
		first_time = false;
		super.onBackPressed();
	}
	
	public void previousTurn(View view)
	{
		TextView temp = (TextView)findViewById(R.id.current_instructions);
		currentStep--;
		if(currentStep < 0)
		{
			currentStep = steps.length-1;
			currentPlayer++;
			if(currentPlayer >= num_players)
				currentPlayer = 0;
		}
		temp.setText(steps[currentStep]);
		this.setTitle(names[currentPlayer]);
	}
	
	public void nextTurn(View view)
	{
		TextView temp = (TextView)findViewById(R.id.current_instructions);
		currentStep++;
		if(currentStep >= steps.length)
		{
			currentStep = 0;
			currentPlayer--;
			if(currentPlayer < 0)
			{
				currentPlayer = num_players-1;
			}
			if(num_players > 1)
			{
				Toast.makeText(getApplicationContext(), "It is now "+names[currentPlayer]+"'s turn.", Toast.LENGTH_SHORT).show();
			}
		}
		temp.setText(steps[currentStep]);
		this.setTitle(names[currentPlayer]);
	}
	
	

}
