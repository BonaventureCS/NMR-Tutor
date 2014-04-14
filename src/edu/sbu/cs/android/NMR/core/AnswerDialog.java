package edu.sbu.cs.android.NMR.core;



import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.sbu.cs.android.R;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.text.InputType;

import android.view.Menu;

import android.view.View;
import android.view.View.OnClickListener;


import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

public class AnswerDialog extends FragmentActivity implements OnClickListener {
EditText mEditText;
String qAns, qTitle;
TextView qData, feedback;
Button bt;
Intent intent;
private ActionBar actionBar;
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.question_dialog);
	qData = (TextView)findViewById(R.id.tvQuestion);
	 mEditText = (EditText)findViewById(R.id.etAnswer);
	 mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
	feedback = (TextView)findViewById(R.id.tvFeedback);
	bt=(Button)findViewById(R.id.btAnsSubmit);
	bt.setOnClickListener(this);
	intent = getIntent();
	qData.setText(intent.getExtras().getString("body"));
	qAns=intent.getExtras().getString("ans");
	actionBar = getActionBar();
	actionBar.setTitle(intent.getExtras().getString("title"));
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {

	return super.onCreateOptionsMenu(menu);
}
@Override
public void onClick(View v) {	
 	if(qAns.equals(mEditText.getText().toString())){
 		if(mEditText.getText().toString().isEmpty()){
 	 		//q.setValid("false");
 			Toast.makeText(this, "Empty Answer ",Toast.LENGTH_LONG).show();
 	 		feedback.setText("Feedback: "+intent.getExtras().getString("feedback"));
 	 		feedback.setTextColor(Color.RED);
 		}
 		else{
 	 		//q.setValid("true");
 			Toast.makeText(this, "Your Answer is Correct",Toast.LENGTH_LONG).show();
 			feedback.setText("Feedback: "+intent.getExtras().getString("feedback"));
 			feedback.setTextColor(Color.GREEN);
 			
 		}
 	} 
 	else{	
 		Toast.makeText(this, "Your Answer is Incorrect",Toast.LENGTH_LONG).show();
 		feedback.setText("Feedback: "+intent.getExtras().getString("feedback"));
 		feedback.setTextColor(Color.RED);
 	}
	
	
}

private class QuestionTask extends AsyncTask<Void, Void,Void>{

String jsondata;
public String loadJSONFromAsset() {
    String json = null;
    try {
    
        InputStream is = getAssets().open("jsondata.json");

        int size = is.available();

        byte[] buffer = new byte[size];

        is.read(buffer);

        is.close();

        json = new String(buffer, "UTF-8");


    } catch (IOException ex) {
        ex.printStackTrace();
        return null;
    }
    return json;

}
	@Override
	protected void onPreExecute() {
		jsondata= loadJSONFromAsset();
		super.onPreExecute();
	}
	@Override
	protected Void doInBackground(Void... params) {

		
	
		JSONArray ja;
		try{
			ja=new JSONArray(jsondata);
		        for(int i=0;i<ja.length();i++){
		          JSONObject json_data = ja.getJSONObject(i);
		         
		        }
		}catch (JSONException e) {
		        e.printStackTrace();
		    }
		return null;
	}
	@Override
	protected void onPostExecute(Void result) {
		
		super.onPostExecute(result);
	} 
}
}
