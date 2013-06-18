// This is the main program for the blackjack game.
public class Blackjack
{
  // This method should:
	//	- Ask the user how many people want to play (up to 3, not including the dealer).
	//	- Create an array of players.
	//	- Create a Blackjack window.
	// 	- Play rounds until the players want to quit the game.
	//	- Close the window.
	public static void main(String[] args)
	{
		System.out.println("How many players are there, including the dealer?");
			int numpl=IO.readInt();
			while(numpl>4 || numpl<2){
				System.out.println("There must be 2-4 players, including the dealer.");
				System.out.println("How many players are there, including the dealer?");
				numpl=IO.readInt();
			}
		Player[] players= new Player[numpl];  //+1 for dealer?
		
		System.out.println("This is the dealer. What is the dealer's name?");
		String name=IO.readString();
		players[0]= new Player(name, true);
		
		for(int i=1; i<numpl; i++){
			System.out.println("What is the player's name?");
			String pname=IO.readString();
			
			players[i]= new Player(pname, false);
		}
		
		//create blackjack window....
		
		boolean play=true;
		//BlackjackWindow window= new BlackjackWindow(players);
		
		while(play==true){
			playRound(players); //, window);
			System.out.println("-----------");
			System.out.println("Play again?");
			play=IO.readBoolean();
		}
		
	}

	// This method executes an single round of play (for all players).  It should:
	//	- Create and shuffle a deck of cards.
	//	- Start the round (deal cards) for each player, then the dealer.
	//	- Allow each player to play, then the dealer.
	//	- Finish the round (announce results) for each player.
	public static void playRound(Player[] players) //, BlackjackWindow window)
	{
				
		Deck playdeck= new Deck();
		playdeck.prob(-1);
		playdeck.shuffle();
		
		for(int i=1; i<players.length; i++){
			players[i].startRound(playdeck); //, window);
		}
		
		players[0].startRound(playdeck); //, window);
			Hand dhand=players[0].getHand();
			Card temp=dhand.getCard(0);
			int dfaceup= temp.getValue();
			
		for(int i=1; i<players.length; i++){
			players[i].playRound(playdeck, dfaceup); //, window);
		}
		players[0].playRound(playdeck, dfaceup); //, window);
		
		dhand=players[0].getHand();
		
		System.out.println("");
		System.out.println("Results of the Round");
		System.out.println("--------------------");
		
		int dscore=dhand.getScore();
		boolean dblackjack;
		if(dhand.getCard(0).getValue()==1 && dhand.getCard(1).getValue()==10 ||
		   dhand.getCard(0).getValue()==10 && dhand.getCard(1).getValue()==1){
		   dblackjack=true;
		   }else 
		   dblackjack=false;
		
		for(int i=1; i<players.length; i++){
			players[i].finishRound(dscore, dblackjack); //, window);
		}
		players[0].finishRound(dscore, dblackjack); //, window);
		
		playdeck.prob(-1);
		// complete this method
	}
}
