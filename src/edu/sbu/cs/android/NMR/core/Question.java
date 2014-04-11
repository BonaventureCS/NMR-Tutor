package edu.sbu.cs.android.NMR.core;

public class Question {
	String qTitle;
	String qData;
	String qAns;
	int qnum;
	String isCorrect;
	String feedback;
	public Question(String qTitle, String qData, String qAns,String c, String f ) {
		super();
		this.qTitle = qTitle;
		this.qData = qData;
		this.qAns = qAns;
		this.isCorrect=c;
		this.feedback=f;
	}
	
	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getqData() {
		return qData;
	}
	public void setqData(String qData) {
		this.qData = qData;
	}
	public String getqAns() {
		return qAns;
	}
	public void setqAns(String qAns) {
		this.qAns = qAns;
	}
	public int getQnum() {
		return qnum;
	}
	public void setQnum(int qnum) {
		this.qnum = qnum;
	}
	public String getValid() {
		return isCorrect;
	}
	public void setValid(String ct) {
		this.isCorrect = ct;
	}
	

}
