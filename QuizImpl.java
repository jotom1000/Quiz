import java.io.Serializable;

public class QuizImpl implements Quiz, Serializable{
	private int id = 0;
	private String quizName = ""; 
	//private List<Question> questions = new ArrayList<Question>();
	//private List<Score> scores = new ArrayList<Score>();


public QuizImpl(int id, String quizName){
	this.id = id;
	this.quizName = quizName;
}

//get Id
public int getId(){
	return this.id;
}

//add question
//public void addQuestion(Question question){
//	questions.add(question);
//}



//calculate highest score

}