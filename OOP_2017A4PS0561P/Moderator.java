import java.util.ArrayList;
import java.util.Random; 

class Moderator implements Runnable {
	                                                             
	private GameData gameData; 							
	private ArrayList<Integer> array_moderator = new ArrayList<Integer>();
	
	//public Moderator(GameData gameData) {           
	//this.gameData = gameData;
	// }         
	
	
	// S I N G L E T O N
	
	private static Moderator mod;
	
	private Moderator (GameData gameData) {
		this.gameData = gameData;
	}
	
	public static synchronized Moderator getInstance(GameData gameData) {
		    if(mod == null) {
		    	mod = new Moderator(gameData);
		    }
		    return mod;
	}
	
	Random rand = new Random();
	
	public void run() {
		
		System.out.println();
		Print p1 = new Print();
		p1.printObject(" Let's Play !!");    // String object passed to Generic function 
		
		
		synchronized(gameData.locks) {
			
			for(int i=0;i<10;i++)
			{
				
				while(!gameData.gameCompleteFlag) {
					
					for(int j=0; j<gameData.playerSuccessFlag.length; j++)
					{
						gameData.playerSuccessFlag[j]=0;
					}
					
					int rand_num = rand.nextInt(50);
					array_moderator.add(rand_num);
					System.out.print("Moderator has generated : ");
					p1.printObject(  rand_num);                  // Integer object is passed to generic function
					gameData.announcedNumber=rand_num;
					gameData.moderator_iteration++;
					
				   if(gameData.moderator_iteration>8)
					{
					p1.printObject(" Nobody has won ");           // String object is passed to generic function
					gameData.gameCompleteFlag=true;
					}
				   
			  try{gameData.locks.wait(500);}catch(InterruptedException e ) {e.printStackTrace();}
				}
		
		 }
	  }
	}
}