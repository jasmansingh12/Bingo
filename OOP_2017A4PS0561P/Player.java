import java.util.ArrayList;
import java.util.Random; 

class Player implements Runnable {   // Runnable is factory design pattern
		
	Random rand = new Random();   
	private int id; // player id [0 to num_players]
	private GameData gameData; //shared data
	private ArrayList<Integer> array_player = new ArrayList<Integer>();
	private int num_matches = 0;
	
	public Player(GameData gameData,int id) {
		this.id = id;
		this.gameData = gameData;
	
		for(int i=0;i<10;i++)
		{
			int rand_num = rand.nextInt(50);
			array_player.add(rand_num);
		 
		}
		
		System.out.println();
		System.out.println("The player" + this.id + " arrayList is :  ");
		
		for(int i=0;i<10;i++)
		{
			System.out.print(" " + array_player.get(i));
		}
	
	}
	Print p1 = new Print();
	public void run() {
	
	synchronized(gameData.locks){
	
		while(!gameData.gameCompleteFlag ) {
			
		if(gameData.playerSuccessFlag[this.id]== 0) {
			
		for(int i=0;i<10;i++)
		{
			
		if(array_player.get(i)==gameData.announcedNumber) {
			
			System.out.println("Matched for player " + this.id +" at position "+ i);
			
			num_matches++;
			array_player.set(i,-1);
			break;
		}
		
		}
		
		if(num_matches==3)
		{
			gameData.gameCompleteFlag=true;
			System.out.print("Winner is Player ");
			 p1.printObject(this.id);
		}
		gameData.playerSuccessFlag[this.id] = 1;
	    }
		
		try{gameData.locks.wait(10);}catch(InterruptedException e ) {e.printStackTrace();}
		
		}
	
	  }
   }
}

