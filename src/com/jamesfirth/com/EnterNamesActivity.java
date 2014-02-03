package com.jamesfirth.com;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class EnterNamesActivity extends Activity {

	public static final String EXTRA_MESSAGE = "com.jamesfirth.bgtm.startturns";
	private int num_players;
	private Button btn;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_names);
		Intent incIntent = getIntent();
		num_players = Integer.parseInt(incIntent.getStringExtra(MainActivity.EXTRA_MESSAGE));
		
		
		//setup
	    btn = new Button(this); 
    	intent = new Intent(this, TurnActivity.class);

        
	    btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Go to next activity
            	String names = "";
            	//int childcount = ((LinearLayout)findViewById(R.id.activity_enter_names)).getChildCount();
            	LinearLayout thelayout = (LinearLayout)findViewById(R.id.activity_enter_names);
            	
            	for(int i=0; i < num_players; i++)
            	{
            		names = ((EditText)(thelayout).getChildAt(i+1)).getText().toString().trim()+";"+names;
            		startGame(names);
            	}
            	
            	
            }
        });
	    btn.setText("Continue");
		
	    setContentView(addAll());
	}

	public void startGame(String message)
	{
        intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}
	
	
	public LinearLayout addAll()
	{
		LinearLayout linearLayout = (LinearLayout)findViewById(R.id.activity_enter_names);
		//Create text fields and add them to the layout
	    EditText[] enterName = new EditText[num_players];
	    for(int i=0; i < num_players; i ++)
	    {
	    	enterName[i] = new EditText(this);
	    	enterName[i].setHint("Enter Player "+(i+1)+ "'s name");
	    	enterName[i].setInputType(0x00000061);
	    	enterName[i].setId(i+1);
		    linearLayout.addView(enterName[i]);
	    }

	    // add button to layout
	    linearLayout.addView(btn); 
	    return linearLayout;
	}
	

}
