package edu.sbu.cs.android.NMR.core;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import edu.sbu.cs.android.R;

import android.content.Context;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CopyOfAnswerDialog extends DialogFragment implements OnClickListener {
EditText mEditText;
String qData="", ansData ,dTitle;
String jsondata, qDatas,qAns, qTitle;
TextView tv;
Button bt;
View view;
Question q;


public CopyOfAnswerDialog() {
    // Empty constructor required for DialogFragment
}
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

     view = inflater.inflate(R.layout.question_dialog, container);
      tv = (TextView) view.findViewById(R.id.tvQuestion);
     mEditText = (EditText) view.findViewById(R.id.etAnswer);
     bt=(Button)view.findViewById(R.id.btAnsSubmit);
     getDialog().setTitle(dTitle);
     mEditText.requestFocus();
     getDialog().getWindow().setSoftInputMode(
             LayoutParams.SOFT_INPUT_STATE_VISIBLE);

     mEditText.setHint("Answer here");
     bt.setOnClickListener(this);
     tv.setText(qData); 
     QuestionTask task=new QuestionTask();
     task.execute();

     return view;
 }
 
 /***
  * Method that populates the dialog
  * @param qS
  */
public void setQuestion(Question qS){
	q=qS;
	if(q.getValid().equals("true")){
		qData=q.getqAns();
	}else
	{
		qData=q.getqData();
	}
	
	ansData=q.getqAns();
	dTitle=q.getqTitle();
	//Toast.makeText(getActivity(), "isCorect"+q.getValid(),Toast.LENGTH_LONG).show();
}
@Override
public void onClick(View v) {	
 	if(ansData.equals(mEditText.getText().toString())){
 		if(mEditText.getText().toString().isEmpty()){
 	 		q.setValid("false");
 	 		Toast.makeText(getActivity(), "Your answer is: "+q.getValid(),Toast.LENGTH_LONG).show();
 		}
 		else{
 	 		q.setValid("true");
 	 		Toast.makeText(getActivity(), "Your answer is: "+q.getValid(),Toast.LENGTH_LONG).show();
 		}
 	} 
 	else{	
 		q.setValid("false");
 		Toast.makeText(getActivity(), "Your answer is: "+q.getValid(),Toast.LENGTH_LONG).show();
 	}
	dismiss();
	
}
public static String jsonToStringFromAssetFolder(String fileName,Context context) throws IOException {
    AssetManager manager = context.getAssets();
    InputStream file = manager.open(fileName);
    byte[] data = new byte[file.available()];
    file.read(data);
    file.close();
    return new String(data);
}

private class QuestionTask extends AsyncTask<Void, Void,Void>{
	@Override
	protected void onPreExecute() {

		super.onPreExecute();
	}
	@Override
	protected Void doInBackground(Void... params) {
		try {
			jsondata= jsonToStringFromAssetFolder("jsondata.json", getActivity());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONArray ja;
		try{
			ja=new JSONArray(jsondata);
		        for(int i=0;i<ja.length();i++){
		          JSONObject json_data = ja.getJSONObject(i);
	        	  if(q.getValid().equals("true")){
	        		  
	        		  json_data.put("isCorrect", "true");
	        	  }
		        //  if(json_data.has("isCorrect")){}	          
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
