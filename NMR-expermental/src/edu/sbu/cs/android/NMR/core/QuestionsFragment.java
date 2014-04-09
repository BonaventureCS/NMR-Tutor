package edu.sbu.cs.android.NMR.core;



import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import edu.sbu.cs.android.R;
import android.content.Context;
import android.content.Intent;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.Toast;

public class QuestionsFragment extends Fragment implements OnItemClickListener{
	  private ListView lv;
	  AnswerDialog answerDialog;
	  ArrayList<String> qlist;
	  ArrayList<Question> questions;
	 ArrayAdapter<String> arrayAdapter;
	 String jsondata, isCorrect, qData,qAns, qTitle, feedback;
	 int f;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
			View rootView = inflater.inflate(R.layout.fragment_questions, container, false);
			lv = (ListView) rootView.findViewById(R.id.lvQuestions);
			qlist=new ArrayList<String>();
			questions = new ArrayList<Question>();
			QuestionTask task=new QuestionTask();
		    task.execute();

	         lv.setOnItemClickListener(this);
	         
		return rootView;
	}
	public static String jsonToStringFromAssetFolder(String fileName,Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(fileName);

        byte[] data = new byte[file.available()];
        file.read(data);
        file.close();
        return new String(data);
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        //FragmentManager fm = getActivity().getSupportFragmentManager();
        //answerDialog = new AnswerDialog();
       if(!qlist.isEmpty()){
     	 Intent i = new Intent(getActivity(), AnswerDialog.class);
     	i.putExtra("title", questions.get(arg2).getqTitle());
     	i.putExtra("body", questions.get(arg2).getqData());
     	i.putExtra("ans", questions.get(arg2).getqAns());
     	i.putExtra("isCorrect", questions.get(arg2).getValid());
     	i.putExtra("feedback", questions.get(arg2).getFeedback());
		
     	 startActivity(i);
       }
      
        //answerDialog.show(fm, "fragment_edit_name");
        
       // Toast.makeText(getActivity(), "jsonarraylenght "+f+" list length"+qlist.size()+"question lenght"+questions.size(),Toast.LENGTH_LONG).show();
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
				f=ja.length();
			        for(int i=0;i<ja.length();i++){
			        	f=i;
			          JSONObject json_data = ja.getJSONObject(i);
			          qTitle=json_data.getString("QuestionTitle");
			          qData=json_data.getString("Question");
			          qAns=json_data.getString("Answer");
			          isCorrect=json_data.getString("isCorrect");
			          feedback=json_data.getString("Feedback");
			          questions.add(new Question(qTitle,qData,qAns,isCorrect,feedback));
			          qlist.add(qTitle); 
			        }

			}catch (JSONException e) {
			        e.printStackTrace();
			    }
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			
			if(qlist.isEmpty()){
				qlist.add("no Questions");
			}
		     arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.question_item,R.id.tvQTitle,qlist );
	         lv.setAdapter(arrayAdapter);
			super.onPostExecute(result);
		} 
	
	}
}
