import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainGame {

	public static void main(String[] args) throws NumberFormatException, IOException {
	
		 BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
		 Print p1 = new Print();
	     p1.printObject("Enter the number of players: ");
	     
	     int num_p = Integer.parseInt(reader.readLine());   // Taking input
	     
	     final GameData game  = new GameData(num_p );      // constructor called
	     final Moderator moderator = Moderator.getInstance(game); //moderator being initialized, constructor called
	     
	     final Player player[]= new Player[num_p ];   //players being initialized according to input
	     
	     for(int i=0; i < num_p  ; i++)
	     {
	    	 player[i]=new Player(game,i);                 //constructor called
	     }
	
	     //threads generated
	     
	     Thread moderatorThread = new Thread(moderator);
	     
	     Thread playerThread[] = new Thread[num_p ];
	     
	     for(int i=0; i < num_p  ; i++)
	     {
	    	 playerThread[i]=new Thread(player[i]);
	     }
	
	     moderatorThread.start();			 //moderator class thread starts here
	     
	     for(int i=0; i < num_p  ; i++) // player's threads start here
	     {
	    	 playerThread[i].start();
	     
	     }
	     
	     for(int i=0; i < num_p  ; i++) // player's threads start here
	     {
	    	 try{playerThread[i].join();}catch(InterruptedException e) {System.out.println("Player thread interrupted");}  // Run until execution  completes
	     }
	     
	     try{moderatorThread.join();}catch(InterruptedException e) {System.out.println("Moderator thread interrupted");}  // Run until execution  completes
	  }
	   
	

}



