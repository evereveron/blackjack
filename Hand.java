// This class represents the set of cards held by one player (or the dealer).
public class Hand
{
  // define fields here
	Card[] hand= new Card[10];
	int h_index= -1;
	//int score;
	
	// This constructor builds a hand (with no cards, initially).
	public Hand()
	{
		hand=hand; 
		// complete this method
	}
	
	// This method retrieves the size of this hand.
	public int getNumberOfCards()
	{
		int num=hand.length;
		return num; // replace this line with your code
	}

	// This method retrieves a particular card in this hand.  The card number is zero-based.
	public Card getCard(int index)
	{
		return hand[index]; // replace this line with your code
	}

	// This method takes a card and places it into this hand.
	public void addCard(Card newcard)
	{
		
		h_index++;
		hand[h_index]=newcard; 
		// complete this method
	}

	// This method computes the score of this hand.
	public int getScore()
	{
		int score=0;
		int mark=hand.length;
		
		for(int f=0; f<hand.length; f++){
			if(hand[f]==null){
				mark=f;
				break;
			}
		}
		for(int n=0; n<mark; n++){
	
			if (hand[n].getFace()==1){ //check for ace
				if(11+score>21){
					score=score+1;
				}
				else{
					score=score+11;
				}
			}else
			score=score+hand[n].getValue();
		}
		return score; // replace this line with your code
	}

	// This methods discards all cards in this hand.
	public void discardAll()
	{
		for(int n=0; n<hand.length; n++){
			hand[n]=null;
		}
		// complete this method
	}
}
