package jp.yasay;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ModeSelect extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        setListeners();
    }
    
    protected void onStart(){
    	super.onStart();
    	// TODO BGM !!!!!!!!!!!!!!!
    }
    
    private void setListeners(){
    	
    	Button single = (Button) findViewById(R.id.single);
    	Button muitl  = (Button) findViewById(R.id.multi);
    	Button scores = (Button) findViewById(R.id.scores);
    	
    	single.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
    	
    	muitl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
    	
    	scores.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
    }
}