NMR-Tutor
=========

Kyle Braham's indy study, Spring 2014. 

App Description: 

Discover the powerful tool of NMR by using H1 NMR, C13NMR and IR graphs to solve for the structure of an unknown molecule. 
The app will provide NMR chemical shift tables, sketch pad and table to keep track of your findings when solving for the unknown structure.  After all data is collected, your sketch table and pad will be available for user to console once drawing the final structure. This allows the solving process to be paper free. 

Help buttons will be available throughout the solving process for beginners but in no time those help buttons will be obsolete. 

Status 5/7/14
=============
							
nmrSolveIt –status as of 5/7/14

Overviews of fragments of app:

Overview of Spectrum fragment (class: HomeFragment):

•	The way that the graphic image works is that an HTML file in the assets folder is loaded into a Web View. 
•	The HTML file contains a link to a graph image in assets folder 
•	By doing this the zoom feature of the image is enable. For future reference the height and width can be changed in the HTML file
•	For each spectrum there are two HTML files (Zoomed in  and Zoomed out). This is used to toggle between two files (on touch).

Overview of Question fragment (class: QuestionFragment):

•	The questions are loaded into a simple list view from an internal file, this is used to save the questions that were answered correctly. 
•	This fragment also contains a spinner. The values of the spinner are in an xml file called spinner_items.xml in the values folder 
•	On install a JSON file in the assets folder is loaded into an internal storage file because assets files cannot be changed (they are static). This can be used for a “reset Questions” function as well (not yet correctly implemented).
•	The JSON file (peak.json) as of now, contains one array of objects. Each object is a question in the list view .

Overview SolveIt fragment (class: DrawFragment):

•	The drawing section of the app allows the user to draw structures. This fragment contains a custom view that contains a canvas (this is where the drawing actually happens). A path created from a list of points is used to draw lines on the canvas.
•	When the user touches a point a Region is created around where the user touches on the screen, the app checks to see if a point in the list of point is contained in the region. If a point is in the region the path’s focus is moved to the point contained in the region. If no points are in the region the focus is the last point created. Then the user drags their finger across the screen. This is determining the coordinates of a new point. When the user removes their finger from the screen the point is drawn from the focused point to the new point.   
•	Also in this fragment is a horizontal scroll view that contains buttons; this is the drawing pad’s toolbox. When a button is clicked (touched) a drawing mode is selected and when the user touches the canvas what is actually drawn depends on what the mode is.
 
Modifying the JSON

When the app was initially designed it was created with only one problem to deal with. The current format however is easy to customize to allow multiple problems that each has its own set of questions.
This is how I would customize it:
1.	Create a JSON array of problem objects
2.	Each object should contain two parts: a title (“problem”:”problem 1”) and an array. This array would contain all of the questions in the question fragment for that particular problem.
3.	Reading the JSON would also have to be modified .  One would need to go though the array of problems and select a problem. Then you need to select the questions array within the problem object. Then read the JSON as normal.

** NOTE: JSON data is loaded into an internal file (in the method: onCreateView in QuestionFragment). Data is read from the internal file (doInBackground in the QuestionTask subclass of QuestionFragment – Reading done asynchronous relative to the UI thread). 

Proposed structure of JSON for handling multiple problems:
[
{
“problem”:”problem 1”
[
... same structure as shown below
]
}
…
]

Understanding JSON structure

Example question object 
{
		"QuestionTitle": "Integration Ratio",	
		"Question": "What is the integration ratio for peak A? ",
		"Answer": "1",
		"isCorrect": "false",
		"Peak": "Peak A",
		"Feedback": "The ratio is the number associated with the area under the peak"
	},
1.	Question title is the broad title of the question 
2.	Question is the text of the question asked 
3.	Answer is the correct answer 
4.	isCorrect indicates if the user answered the question correctly 
5.	Peak is used for filtering this question based on the peak of the spectrum it is in reference to.
6.	Feedback is the information that is presented to the user after answering the question 


Adding another Spectrum (graph image)
1.	To add a new spectrum image place image in assets folder and create a new html file in the assets file (use already existing one as a template).
2.	Change the link in the src of the image to the name of the image you added to the assets folder  


A list of things that need to be added to the app:

•	Functionality for more than one problem
o	 As it stands now the app can only handle one problem. It is easy to implement more than one problem by editing the JSON file to have more array objects (an array of questions) than just one.
o	A web server could be created that displays just the image. Then the web view in the spectra fragment could just load the image from the website. 
o	Alternatively, all the images could be in different folders in the res folder like images folder and HTML folder. 
•	Finish drawing section of app
o	As of now the drawing section is not finished. 
o	What needs to be added is the addition of other structures besides the single bond 
•	Start structure validation
o	Validating the structure was something we had in mind for the app but did not start this part because we ran out of time
•	May want to consider changing the file type of the graph image to SVG
o	By doing this zooming in and out may look and feel better since SVG images are vector based.
•	Extending it for more than one spectrum 
o	There needs to be another filter that filters the question list based on the selected spectrum. 
o	 This can be implemented by adding the  “spectrum”:”nmr” field to all of the question objects in the JSON file



