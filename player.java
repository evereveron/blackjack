// This class represents one blackjack player (or the dealer)
public class Player
{
  // define fields here
	String name;
	boolean dealer;
	Hand p_hand;
	Hand p_hand2;
	boolean split=false;
	int hindex=1; // =1 because dealer starts with 2 cards already dealt.
	int bankroll=1000;
	int wager;
	boolean blackjack=false;
	int score=0;
	double statprob;
	boolean in=true;
	boolean insurance=false;
	int ins;
	
	// This constructor creates a player.
	// If isDealer is true, this Player object represents the dealer.
	public Player(String playerName, boolean isDealer)
	{
		name=playerName;
		dealer=isDealer;
	}

	// This method retrieves the player's name.
	public String getName(String name)
	{
		this.name=name;
		return name; // ????
	}

	// This method retrieves the player's hand of cards.
	public Hand getHand()
	{
		return p_hand; // replace this line with your code
	}
	
	public int getMoney()
	{
		return bankroll;
	}
	
	// This method deals two cards to the player (one face down if this is the dealer).
	// The window input should be used to redraw the window whenever a card is dealt.
	//if the player is the dealer,
	//deal one card FACE UP and one card FACE DOWN to the player
	//else
	//deal two cards FACE UP to the player
	public void startRound(Deck deck) //, BlackjackWindow window) 
	{
		
		p_hand= new Hand();
		Card temp;
		if(dealer==true){
			p_hand.addCard(deck.drawCard());
			p_hand.getCard(0).turnFaceUp();
			temp=p_hand.getCard(0);
			System.out.println("The dealer's face up card is: " + temp.toString());
			p_hand.addCard(deck.drawCard());
			//System.out.println(p_hand.getScore());
		
		}else{
			
			System.out.println("");
			System.out.println(name + "'s turn.");
			System.out.println(name + " has $" + bankroll);
			System.out.println("What is your wager?");
				wager=IO.readInt();
				while(wager>bankroll || wager<0){
					System.out.println("That is not a valid wager. Enter a new wager:");
					wager=IO.readInt();
				}
			
			temp=deck.drawCard();
			p_hand.addCard(temp);
			System.out.println("The card is: " + temp.toString());
			p_hand.getCard(0).turnFaceUp();//
			
			temp= deck.drawCard();
			p_hand.addCard(temp);
			System.out.println("The card is: " + temp.toString());
			p_hand.getCard(1).turnFaceUp();//
			hindex=1;
			
			if(p_hand.getCard(0).getFace()==1 && p_hand.getCard(1).getValue()==10 ||
				p_hand.getCard(0).getValue()==10 && p_hand.getCard(1).getFace()==1){
					
					System.out.println("Blackjack!");
					blackjack=true;
			}
		}
		
	}

	// This method executes gameplay for one player.
	// If this player is the dealer:
	//	- hits until score is at least 17
	// If this is an ordinary player:
	//	- repeatedly asks the user if they want to hit (draw another card)
	//	  until either the player wants to stand (not take any more cards) or
	//	  his/her score exceeds 21 (busts).
	// The window input should be used to redraw the window whenever a card is dealt or turned over.
	public void playRound(Deck deck, int dealercard) //, BlackjackWindow window)
	{
		Card temp;
		if(dealer==true){
			
			System.out.println("");
			System.out.println("Dealer's turn.");
			System.out.println("The dealer's face down card is: " + p_hand.getCard(1).toString());
			System.out.println("The dealer's score is: " + p_hand.getScore() + ".");
			
			while( p_hand.getScore()<17){
				System.out.println("Hit.");
				p_hand.addCard(deck.drawCard());
				hindex++;
				//System.out.println("DEBUGGING: Player.playround: hindex= " + hindex);
				temp= p_hand.getCard(hindex);
				System.out.println("The card is: " + temp.toString());
				System.out.println("The dealer's score is: " + p_hand.getScore() + ".");
			}
			
			if(p_hand.getScore()>21)
				System.out.println("Dealer busts.");
			else
			System.out.println("Dealer stands.");
			
		}else if(blackjack==true){
			System.out.println("");
			System.out.println(name + "'s turn.");
			System.out.println(name + " has Blackjack.");
				if(dealercard==1 || dealercard==11){
					System.out.println("Do you want to take insurance?");
					insurance=IO.readBoolean();
						if(insurance==true){
							System.out.println("How much would you like to wager for insurance?");
							ins=IO.readInt();
								while(ins>bankroll-wager ||ins<0){
									System.out.println("Invalid wager. Enter again.");
									ins=IO.readInt();
								
								}
						}
				}
			return;
			
		}else if(in==false){
			System.out.println(name+ " has no more money.");
			return;
		}
		else{
			
			boolean hit=true;
			System.out.println("");
			System.out.println(name+ "'s turn.");
			System.out.println(name+ "'s score is: " + p_hand.getScore() + ".");
			score=p_hand.getScore();
			
			if(dealercard==1 || dealercard==11){
					System.out.println("Do you want to take insurance?");
					insurance=IO.readBoolean();
						if(insurance==true){
							System.out.println("How much would you like to wager for insurance?");
							ins=IO.readInt();
								while(ins>bankroll-wager ||ins<0){
									System.out.println("Invalid wager. Enter again.");
									ins=IO.readInt();
								
								}
						}
				}
			//System.out.println("Do you want to hit?");
				
				//if(dealercard<=6){
					//System.out.println("HINT: Stand when Dealer has 6 or less");
				//}
			
			//hit=IO.readBoolean();
			System.out.println("");
			System.out.println("0: Stand.");
			System.out.println("1: Hit.");
			System.out.println("2: Double.");
			System.out.println("3: Split.");
				if(dealercard<=6){
					System.out.println("HINT: Stand when Dealer has 6 or less");
				}
				
				System.out.println("Static Hint: You have a " + deck.sprob(p_hand.getScore()) + "% chance of busting if you hit.");
				System.out.println("Card Counting Hint: You have a " + deck.ccprob(p_hand.getScore()) + "% chance of busting if you hit.");
				System.out.println("You have a " + deck.blackjackprob() + "% chance of getting a Blackjack.");
				int pick=IO.readInt();
				while(pick != 0 && pick != 1 && pick!=2 && pick!=3){
					System.out.println("That is not a choice.");
					System.out.println("0: Stand.");
					System.out.println("1: Hit.");	
					System.out.println("2: Double.");
					System.out.println("3: Split.");
						pick=IO.readInt();
				}
				
			
			//while(hit==true){
			while(pick != 0){
				if(pick==1){
					p_hand.addCard(deck.drawCard());
					hindex++;
					temp= p_hand.getCard(hindex);
					System.out.println("The card is: " + temp.toString());
					System.out.println(name + "'s score is: " + p_hand.getScore() + ".");
						score=p_hand.getScore();
						if (p_hand.getScore()>21){
							System.out.println("Bust!");
							break;
						}
				}
				if(pick==2){
					wager=wager*2;
					p_hand.addCard(deck.drawCard());
					hindex++;
					temp= p_hand.getCard(hindex);
					System.out.println("The card is: " + temp.toString());
					System.out.println("Your score is: " + p_hand.getScore() + ".");
						score=p_hand.getScore();
						if (p_hand.getScore()>21){
							System.out.println("Bust!");
						}
					return;
				}
				
				if(pick==3){
					if(p_hand.getCard(0).getFace() != p_hand.getCard(1).getFace()){
						System.out.println("You can only split a pair.");
						System.out.println(" ");
						System.out.println("0: Stand.");
						System.out.println("1: Hit.");
						System.out.println("2: Double.");
						
						System.out.println("Static Hint: You have a " + deck.sprob(p_hand.getScore()) + "% chance of busting if you hit.");
						System.out.println("Card Counting Hint: You have a " + deck.ccprob(p_hand.getScore()) + "% chance of busting if you hit.");
						System.out.println("You have a " + deck.blackjackprob() + "% chance of getting a Blackjack.");
						
							pick=IO.readInt();							
							while(pick != 0 && pick != 1 && pick!=2){
								System.out.println("That is not a choice.");
								System.out.println("0: Stand.");
								System.out.println("1: Hit.");	
								System.out.println("2: Double.");
								pick=IO.readInt();
							}
						continue;
					}
					temp= p_hand.getCard(0);
					p_hand2.addCard(temp);
					temp= p_hand.getCard(1);
					p_hand.discardAll();
					p_hand.addCard(temp);
					split=true;
					
					hindex=0;
					int hindex2=0;
					
					System.out.println("");
					System.out.println("Hand 1's score is " + p_hand.getScore());
					System.out.println("");
					System.out.println("0: Stand.");
					System.out.println("1: Hit.");
					
					System.out.println("Static Hint: You have a " + deck.sprob(p_hand.getScore()) + "% chance of busting if you hit.");
					System.out.println("Card Counting Hint: You have a " + deck.ccprob(p_hand.getScore()) + "% chance of busting if you hit.");
					System.out.println("You have a " + deck.blackjackprob() + "% chance of getting a Blackjack.");
					
						pick=IO.readInt();
						while(pick != 0 && pick != 1){
							System.out.println("That is not a choice.");
							System.out.println("0: Stand.");
							System.out.println("1: Hit.");					
							pick=IO.readInt();
						}
					
					while (pick != 0){ //hand 1
						p_hand.addCard(deck.drawCard());
						hindex++;
						temp= p_hand.getCard(hindex);
						System.out.println("The card is: " + temp.toString());
						System.out.println(name + "'s score is: " + p_hand.getScore() + ".");
						score=p_hand.getScore();
							if (p_hand.getScore()>21){
								System.out.println("Bust!");
								break;
							}
							
						System.out.println("");
						System.out.println("0: Stand.");
						System.out.println("1: Hit.");
						
						System.out.println("Static Hint: You have a " + deck.sprob(p_hand.getScore()) + "% chance of busting if you hit.");
						System.out.println("Card Counting Hint: You have a " + deck.ccprob(p_hand.getScore()) + "% chance of busting if you hit.");
						System.out.println("You have a " + deck.blackjackprob() + "% chance of getting a Blackjack.");
						pick=IO.readInt();
					}
					
					System.out.println("");
					System.out.println("Hand 2's score is" + p_hand2.getScore());
					System.out.println("");
					System.out.println("0: Stand.");
					System.out.println("1: Hit.");
					
					System.out.println("Static Hint: You have a " + deck.sprob(p_hand2.getScore()) + "% chance of busting if you hit.");
					System.out.println("Card Counting Hint: You have a " + deck.ccprob(p_hand.getScore()) + "% chance of busting if you hit.");
					System.out.println("You have a " + deck.blackjackprob() + "% chance of getting a Blackjack.");
					
						pick=IO.readInt();
						while(pick != 0 && pick != 1){
							System.out.println("That is not a choice.");
							System.out.println("0: Stand.");
							System.out.println("1: Hit.");					
							pick=IO.readInt();
						}
					
					while (pick != 0){ //hand 2
						p_hand2.addCard(deck.drawCard());
						hindex++;
						temp= p_hand2.getCard(hindex);
						System.out.println("The card is: " + temp.toString());
						System.out.println(name + "'s score is: " + p_hand2.getScore() + ".");
						score=p_hand2.getScore();
							if (p_hand2.getScore()>21){
								System.out.println("Bust!");
								break;
							}
							
						System.out.println("");
						System.out.println("0: Stand.");
						System.out.println("1: Hit.");
							pick=IO.readInt();
							while(pick != 0 && pick != 1){
								System.out.println("That is not a choice.");
								System.out.println("0: Stand.");
								System.out.println("1: Hit.");					
								pick=IO.readInt();
							}
					}
					
				}
				//System.out.println("Do you want to hit?");
				//hit=IO.readBoolean();
				System.out.println("");
				System.out.println("0: Stand.");
				System.out.println("1: Hit.");
				
				System.out.println("Static Hint: You have a " + deck.sprob(p_hand.getScore()) + "% chance of busting if you hit.");
				System.out.println("Card Counting Hint: You have a " + deck.ccprob(p_hand.getScore()) + "% chance of busting if you hit.");
				System.out.println("You have a " + deck.blackjackprob() + "% chance of getting a Blackjack.");
				//can only double or split on first move.
					pick=IO.readInt();
					while(pick != 0 && pick != 1){
						System.out.println("That is not a choice.");
						System.out.println("0: Stand.");
						System.out.println("1: Hit.");					
						pick=IO.readInt();
					}
			}
		}
		// complete this method
	}


	// This method informs the player about whether they won, lost, or pushed.
	// It also discards the player's cards to prepare for the next round.
	// The window input should be used to redraw the window after cards are discarded.
	public void finishRound(int dealerScore, boolean dblackjack) //, BlackjackWindow window)
	{
		if(dealer==true){
			if(dealerScore>21){
				System.out.println("Dealer loses.");
			}
		}else if(blackjack==true){
			if(dblackjack==false){
				if(insurance==false){
					bankroll=bankroll+(wager/2)+wager;
					System.out.println(name+ " has Blackjack.");
					System.out.println(name+ " has $" + bankroll);
				}
				else{
					bankroll=bankroll+(wager/2)+wager-ins;
					System.out.println(name+ " has Blackjack, but took insurance.");
					System.out.println(name+ " has $" + bankroll);
				}				
			}
			else{
				if(insurance==false){
					System.out.println(name+ " pushed.");
					System.out.println(name+ " has $" + bankroll);
				}
				else{
					System.out.println(name+ " pushed, but took insurance.");
					bankroll=bankroll+ins;
					System.out.println(name+ " has $" + bankroll);
				}
			}
				
		}else if(split==true){
		
			if(dealerScore>21 && p_hand.getScore()<=21 
			   && p_hand2.getScore() <=21){
				bankroll= bankroll+ wager*2;
				System.out.println(name + " won both hands.");
				System.out.println(name+ " has $" + bankroll);
			}
			
			else if(dealerScore>21 && p_hand.getScore()>21 
			        && p_hand2.getScore()>21){
				bankroll= bankroll- wager*2;
				System.out.println(name + " lost both hands.");
					if(bankroll<=0){
						System.out.println(name+ " has no more money.");
						in=false;
					}
					else
					System.out.println(name+ " has $" + bankroll);
			}
			
			else if(dealerScore<=21 && p_hand.getScore()<=21 
			        && p_hand2.getScore() <=21){
				
				if(p_hand.getScore()>dealerScore){
					if(p_hand2.getScore()>dealerScore){
						bankroll=bankroll+wager*2;
						System.out.println(name + " won both hands.");
						System.out.println(name+ " has $" + bankroll);
					}
					bankroll=bankroll+wager;
					System.out.println(name + " won one hand.");
					System.out.println(name+ " has $" + bankroll);
				}
				else if(p_hand2.getScore()>dealerScore){
					bankroll=bankroll+wager;
					System.out.println(name + " won one hand.");
					System.out.println(name+ " has $" + bankroll);
				}
				
			}
			
		}
		else if(insurance==true){
			if(p_hand.getScore()>21){
				bankroll=bankroll-wager-ins;
				System.out.println(name+ " lost.");
					if(bankroll<=0){
						System.out.println(name+ " has no more money.");
						in=false;
					}else
				System.out.println(name+ " has $" + bankroll);
			}
			else if(p_hand.getScore()<21 && dblackjack==true){
				bankroll=bankroll-wager+ins;
				System.out.println(name+ " lost, but took insurance.");
					if(bankroll<=0){
						System.out.println(name+ " has no more money.");
						in=false;
					}else
				System.out.println(name+ " has $" + bankroll);
			
			}
			else if(p_hand.getScore()==21 && dblackjack==true){
				bankroll=bankroll+ins;
				System.out.println(name+ " pushed, but took insurance.");
				System.out.println(name+ " has $" + bankroll);
			}
			else if(p_hand.getScore()<21 && dblackjack==false && dealerScore>21){
				bankroll=bankroll+wager-ins;
				System.out.println(name+ " won, but took insurance.");
					if(bankroll<=0){
						System.out.println(name+ " has no more money.");
						in=false;
					}else
				System.out.println(name+ " has $" + bankroll);
			}
			else if(p_hand.getScore()<21 && dblackjack==false && dealerScore<21){
				if(p_hand.getScore()==dealerScore){
					bankroll=bankroll-ins;
					System.out.println(name+ " pushed, but took insurance.");
					if(bankroll<=0){
						System.out.println(name+ " has no more money.");
						in=false;
					}else
					System.out.println(name+ " has $" + bankroll);
				}
				if(p_hand.getScore()<dealerScore){
					bankroll=bankroll-wager-ins;
					System.out.println(name+ " lost, and took insurance.");
					if(bankroll<=0){
						System.out.println(name+ " has no more money.");
						in=false;
					}
					else
					System.out.println(name+ " has $" + bankroll);
				}
				if(p_hand.getScore()>dealerScore){
					bankroll=bankroll+wager-ins;
					System.out.println(name+ " won, but took insurance.");
					if(bankroll<=0){
						System.out.println(name+ " has no more money.");
						in=false;
					}else
					System.out.println(name+ " has $" + bankroll);
				}
			}
		}
		else{
		if(dealerScore>21 && blackjack==false){
			if(p_hand.getScore()<=21){
				System.out.println(name + " won.");
				bankroll=bankroll+wager;
				System.out.println(name+ " has $" + bankroll);
			}
			else{
				System.out.println(name + " lost.");
				bankroll=bankroll-wager;
					if(bankroll<=0){
						System.out.println(name+ " has no more money.");
						in=false;
					}
					else
				System.out.println(name+ " has $" + bankroll);
			}
		}
		else{
			if(p_hand.getScore()>21 || p_hand.getScore()<dealerScore && blackjack==false){
				System.out.println(name+ " lost.");
				bankroll=bankroll-wager;
					if(bankroll<=0){
						System.out.println(name+ " has no more money.");
						in=false;
					}
					else
				System.out.println(name+ " has $" + bankroll);
			}
			else if(p_hand.getScore()==dealerScore){
				System.out.println(name + " pushed.");
				System.out.println(name+ " has $" + bankroll);
			}
			else{
				System.out.println(name + " won.");
				bankroll=bankroll+wager;
				System.out.println(name+ " has $" + bankroll);
			}
		}
		}
		p_hand.discardAll();
		hindex=1;
		blackjack=false;
		split=false;
		//added this to fix npe error.
		// complete this method
	}
}
