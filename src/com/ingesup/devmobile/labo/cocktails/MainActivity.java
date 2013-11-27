package com.ingesup.devmobile.labo.cocktails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    private Button buttonElisa;
	private Button buttonNicolas;
	private Button buttonPA;


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        buttonElisa = (Button) findViewById(R.id.button1);
        buttonNicolas = (Button) findViewById(R.id.button2);
        buttonPA = (Button) findViewById(R.id.button3);
     
        buttonElisa.setOnClickListener(this);
        buttonNicolas.setOnClickListener(this);
        buttonPA.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View arg0) {
		if(arg0.getId() == buttonElisa.getId()) {
			startActivity(new Intent(getApplicationContext(), MainActivityElisa.class));
		}
		else if(arg0.getId() == buttonNicolas.getId()) {
			startActivity(new Intent(getApplicationContext(), MainActivityNicolas.class));
		}
		else if(arg0.getId() == buttonPA.getId()) {
			startActivity(new Intent(getApplicationContext(), MainActivityPierreAntoine.class));
		}
	}
    
}
