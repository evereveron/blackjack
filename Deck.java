import java.util.Random;

// This class represents the deck of cards from which cards are dealt to players.
public class Deck
{
  // define fields here
	Card[] d;
	int index;
	
		int ace=0;
		int two=0;
		int three=0;
		int four=0;
		int five=0;
		int six=0;
		int seven=0;
		int eight=0;
		int nine=0;
		int ten=0;
		int total=52;


		
	// This constructor builds a deck of 52 cards.
	public Deck()
	{
		d = new Card[52];
		int b=0;
		
			//for(int s=0; s==4; s++){
				
				//assigns face
				//for(int f=1; f==13; f++){
					//b++;
					//d[b-1]= new Card(s, f);
					
				//}
			//}
			
			for(int f=1; f<14; f++){
				d[b]= new Card(0, f);
				b++;
			}
			for(int f=1; f<14; f++){
				d[b]= new Card(1, f);
				b++;
			}
			for(int f=1; f<14; f++){
				d[b]= new Card(2, f);
				b++;
			}
			for(int f=1; f<14; f++){
				d[b]= new Card(3, f);
				b++;
			}
		index = 51;
		// complete this method
	}

	// This method shuffles the deck (randomizes the array of cards).
	// Hint: loop over the cards and swap each one with another card in a random position.
	public void shuffle()
	{
		Card[] temp= new Card[1];
		
		for(int i=0; i<300; i++){
			
			int n=(int)(Math.random()*52);
			
			temp[0]=d[0];
			d[0]=d[n];
			d[n]=temp[0];
		}
	}
	
	// This method takes the top card off the deck and returns it.
	public Card drawCard()
	{
		index--;
		
		int x= d[index+1].getValue();
		prob(x);
		total--;
		
		return d[index+1]; // replace this line with your code
	}
	
	// This method returns the number of cards left in the deck.
	public int getSize(Card[] cardArray)
	{
		int count=0;
		
		for(int i=0; i<52; i++){
		
			if(cardArray[i]==null){
				count++;
			}
		}
		
		return 52-count; // replace this line with your code
	}
	
	public double sprob(int v){
		
		int x=22-v;
		//x or higher will bust
		double y= (10-x+4)*4;
		
		if(y<0)
			return 0.00;
			
		else
			return y/52*100;
		
	}
	
	public void prob(int v){	

		if(v==1)
			ace++;
		if(v==2)
			two++;
		if(v==3)
			three++;
		if(v==4)
			four++;
		if(v==5)
			five++;
		if(v==6)
			six++;
		if(v==7)
			seven++;
		if(v==8)
			eight++;
		if(v==9)
			nine++;
		if(v==10 || v==11 || v==12 || v==13)
			ten++;
		if(v== -1){
			ace=0;
			two=0;
			three=0;
			four=0;
			five=0;
			six=0;
			seven=0;
			eight=0;
			nine=0;
			ten=0;
			total=52;
		}
		
	}
	
	public double ccprob(int v){
		int x=22-v;
		//x or higher will bust
		double y= (10-x+4)*4;
		
		if(x==10){
			y=y-ten;
			return y/total*100;
		}
		else if(x==9){
			y=y-nine-ten;
			return y/total*100;
		}
		else if(x==8){
			y=y-eight-nine-ten;
			return y/total*100;
		}
		else if(x==7){
			y=y-seven-eight-nine-ten;
			return y/total*100;
		}
		else if(x==6){
			y=y-six-seven-eight-nine-ten;
			return y/total*100;
		}
		else if(x==5){
			y=y-five-six-seven-eight-nine-ten;
			return y/total*100;
		}
		else if(x==4){
			y=y-four-five-six-seven-eight-nine-ten;
			return y/total*100;
		}
		else if(x==3){
			y=y-three-four-five-six-seven-eight-nine-ten;
			return y/total*100;
		}
		else if(x==2){
			y=y-two-three-four-five-six-seven-eight-nine-ten;
			return y/total*100;
		}
		else if(x==1){
			y=y-ace-two-three-four-five-six-seven-eight-nine-ten;
			return y/total*100;
		}
		else 
			return 0.00;
	
	}
	
	public double blackjackprob(){
		
		double bjace= 4-ace;
		double bjten= 16-ten;
		
		double ans= (bjace/total) * (bjten/total) *100;
		
		return ans;
	
	}
}

