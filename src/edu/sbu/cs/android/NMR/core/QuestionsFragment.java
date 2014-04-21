package edu.sbu.cs.android.NMR.core;





import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import android.widget.Toast;

public class QuestionsFragment extends Fragment implements OnItemClickListener, OnItemSelectedListener {
	  private ListView lv;
	  AnswerDialog answerDialog;
	  ArrayList<String> qlist;
	  ArrayList<Question> questions;
	 ArrayAdapter<String> arrayAdapter;
	 String jsondata, isCorrect, qData,qAns, qTitle, feedback, selectedPeak,path;
	 static Question temp;
	 QuestionTask task;
	 Spinner spinner;
	 File file;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.fragment_questions, container, false);
			lv = (ListView) rootView.findViewById(R.id.lvQuestions);
			spinner = (Spinner) rootView.findViewById(R.id.filter_spinner);
			// Create an ArrayAdapter using the string array and a default spinner layout
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
			        R.array.Peaks, android.R.layout.simple_spinner_item);
			// Specify the layout to use when the list of choices appears
			path = getActivity().getFilesDir().getAbsolutePath();
			file = new File(path + "/question.txt");
			if (!file.exists()) {
				writeToString(file,loadJSONFromAsset());
				Toast.makeText(getActivity(), readFromFile(),Toast.LENGTH_SHORT).show();
			}else{

				Toast.makeText(getActivity(), "does  exist",Toast.LENGTH_SHORT).show();
			}

			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
			spinner.setAdapter(adapter);
			spinner.setOnItemSelectedListener(this);
			
		    
	         lv.setOnItemClickListener(this);
	         
		return rootView;
	}
//	public static String jsonToStringFromAssetFolder(String fileName,Context context) throws IOException {
//      //  AssetManager manager = context.getAssets();
//     // InputStream file = manager.open(fileName);
//		InputStream file= context.getResources().openRawResource(R.raw.peak);
//        byte[] data = new byte[file.available()];
//        file.read(data);
//        file.close();
//        return new String(data);
//    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
       if(!qlist.isEmpty()){
    	 temp=questions.get(arg2);
     	 Intent i = new Intent(getActivity(), AnswerDialog.class);
     	i.putExtra("title", questions.get(arg2).getqTitle());
     	i.putExtra("body", questions.get(arg2).getqData());
     	i.putExtra("ans", questions.get(arg2).getqAns());
     	i.putExtra("isCorrect", questions.get(arg2).getValid());
     	i.putExtra("feedback", questions.get(arg2).getFeedback());
		//Toast.makeText(getActivity(), "Peak: ",Toast.LENGTH_LONG).show();
     	 startActivity(i);
       }
	}
	 public void onItemSelected(AdapterView<?> parent, View view, 
	            int pos, long id) {
		 		questions = new ArrayList<Question>();
		 		qlist=new ArrayList<String>();
	        // An item was selected. You can retrieve the selected item using
	        selectedPeak= parent.getItemAtPosition(pos).toString();
	        task=new QuestionTask(selectedPeak);
	        task.execute();
	         
	    }

		public void writeToString(File file,String str){
			FileOutputStream stream = null;
			try {
				stream = new FileOutputStream(file);
				stream.write(str.getBytes());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public String readFromFile(){
			int length = (int) file.length();
			byte[] bytes = new byte[length];
			FileInputStream in =null;
			try {
				in= new FileInputStream(file);
				in.read(bytes);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			String contents = new String(bytes); 
			return contents;

		}

		public String loadJSONFromAsset() {
			String json = null;
			try {

				InputStream is = getActivity().getAssets().open("peak.json");
				//InputStream is= getApplicationContext().getResources().openRawResource(R.raw.peak);
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

	private class QuestionTask extends AsyncTask<Void, Void,Void>{
		String sentpeak,JSONpeak;
	public QuestionTask(String p){
		sentpeak=p;
		
	}
		@Override
		protected void onPreExecute() {
			//Toast.makeText(getActivity(),"preExcute: "+sentpeak ,Toast.LENGTH_SHORT).show();
			super.onPreExecute();
		}
		@Override
		protected Void doInBackground(Void... params) {
//			try {
//				//jsondata=readFromFile(); //jsonToStringFromAssetFolder("peak.json", getActivity());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			jsondata=readFromFile();
			JSONArray ja;
				
			try{
				ja=new JSONArray(jsondata);
				
			        for(int i=0;i<ja.length();i++){
			        
			          JSONObject json_data = ja.getJSONObject(i);
			          qTitle=json_data.getString("QuestionTitle");
			          qData=json_data.getString("Question");
			          qAns=json_data.getString("Answer");
			          isCorrect=json_data.getString("isCorrect");
			          feedback=json_data.getString("Feedback");
			          JSONpeak=json_data.getString("Peak");
			          if(sentpeak.equals(JSONpeak)){
			        	  questions.add(new Question(qTitle,qData,qAns,isCorrect,feedback));
			        	  qlist.add(qTitle);
			          }
			         
			          
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

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		
	}
}
