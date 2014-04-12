import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.Naming;
import java.net.MalformedURLException;
import java.io.Serializable;

public class PlayerClientImpl implements Serializable, PlayerClient{
	
	public PlayerClientImpl(){

	}

//launch
private QuizGame launch() throws NotBoundException, MalformedURLException, RemoteException{
	if (System.getSecurityManager() == null){
		System.setSecurityManager(new RMISecurityManager());
	}
	Remote service = Naming.lookup("//127.0.0.1:1099/QuizMaster");		
	QuizGame quizGame = (QuizGame) service;
	String receivedEcho = quizGame.echo("*****Welcome to Quiz Master!*****");
	System.out.println(receivedEcho);
	return quizGame;
}

@Override
public void Options(PlayerClient newPlayerClient, QuizGame quizGame) throws RemoteException{
		String userName = "", choice = "";
		int playerId = 0;
		boolean correctId;
		System.out.println("");
		System.out.println("Are you a returning player? Y or N.");
		choice = System.console().readLine();
		switch (choice){
			case "Y":
			System.out.println("");
			System.out.println("Please key in your player ID number.");
			playerId = Integer.parseInt(System.console().readLine());
			correctId = quizGame.checkPlayerId(playerId);
			if(!correctId){
				System.out.println("This ID number does not exist. Please try again.");
				newPlayerClient.Options(newPlayerClient, quizGame);
			}
			break;

			case "N":
			System.out.println("");
			System.out.println("To set up a player account please enter your username, followed by the return key.");
			userName = System.console().readLine();
			playerId = quizGame.addPlayer(userName);
			System.out.println("");
			System.out.println("Your username is " + userName + " and your player ID number = " + playerId + " (Please keep this safe as you will need it to play future quizzes.)");
			break;

			default:
			System.out.println("Sorry I didn't understand that. Please try again. Make sure you use upper case. ");
			newPlayerClient.Options(newPlayerClient, quizGame);
			break;
		}
			//method to get a list of quizzes
			Quiz[] quizzes = quizGame.getQuizList();
			System.out.println("Please choose a quiz to play by keying in its Quiz Number;");
			System.out.println("");
			for(Quiz quiz : quizzes){
				System.out.println("Quiz Number " + quiz.getId() + "; " + quiz.getQuizName() + " (Total number of questions = " + quiz.getNumOfQuestions()")");
			}
			

			//choose quiz
			//play quiz
			//submit quiz
			//score returned
			//?play another quiz/quit
		
	}

public static void main (String[] args) throws NotBoundException, MalformedURLException, RemoteException{
		PlayerClientImpl newPlayerClient = new PlayerClientImpl();
		QuizGame quizGame = newPlayerClient.launch();
		newPlayerClient.Options(newPlayerClient, quizGame);
		
	}


}