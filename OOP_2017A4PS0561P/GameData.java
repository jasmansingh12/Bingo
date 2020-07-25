

class GameData{
	
	public int announcedNumber = -1;
	public int current_iteration=0;
	public int moderator_iteration=-1;
	public boolean gameCompleteFlag = false;
	public int[] playerSuccessFlag ;
	public boolean[] each_iteration ;

	
	
	public GameData(int num)
	{
		playerSuccessFlag = new int[num];
		
		for(int i=0; i<num;i++)
		{
			playerSuccessFlag[i]=0;
		}
		
	}
		
	public Object locks = new Object();

}