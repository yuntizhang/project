import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.Random;

//score marks
public class Crazy8Game{

	public static int handValue(ArrayList<Card> hand) {
        int value = 0;
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getRankString().equals("Jack") || hand.get(i).getRankString().equals("Queen") || hand.get(i).getRankString().equals("King")) {
                value = value + 10;
            } else if (hand.get(i).getRankString().equals("Ace")) {
                value = value + 1;
            } else if (hand.get(i).getRankString().equals("8")) {
                value = value + 50;
            } else if (hand.get(i).getRankString().equals("2") || hand.get(i).getRankString().equals("4")) {
                value = value + 25;
            } else if (hand.get(i).getRankString().equals("7")) {
                value = value + 20;
            } else if (hand.get(i).getRankString().equals("3")) {
                value = value + 3;
            } else if (hand.get(i).getRankString().equals("5")) {
                value = value + 5;
            } else if (hand.get(i).getRankString().equals("6")) {
                value = value + 6;
            } else if (hand.get(i).getRankString().equals("9")) {
                value = value + 9;
            } else if (hand.get(i).getRankString().equals("10")) {
                value = value + 10;
            }
        }
        return value;
    }
	
	
	public static void main(String[] args){
		
		/* create the deck */
		Card[] deck = new Card[52];
		int index = 0;
		for(int r=2; r<=14; r+=1){
			for(int s=0; s<4; s+=1){
				deck[index++] = new Card(Card.SUITS[s], Card.RANKS[r]);
			}
		}
		
		/* shuffle the deck */
		Random rnd = new Random();
		Card swap;
		for(int i = deck.length-1; i>=0; i=i-1){
			int pos = rnd.nextInt(i+1);
			swap = deck[pos];
			deck[pos] = deck[i];
			deck[i] = swap;
		}		
		
		/* players in the game */
		Player[] players = new Player[3];
		players[0] = new BadPlayer( Arrays.copyOfRange(deck, 0, 5) );
		System.out.println("0 : " + Arrays.toString( Arrays.copyOfRange(deck, 0, 5))); 
		players[1] = new BadPlayer( Arrays.copyOfRange(deck, 5, 10) );
		System.out.println("0 : " + Arrays.toString( Arrays.copyOfRange(deck, 5, 10))); 
		players[2] = new BadPlayer( Arrays.copyOfRange(deck, 10, 15) );
		System.out.println("0 : " + Arrays.toString( Arrays.copyOfRange(deck, 10, 15))); 
		
		
		/* discard and draw piles */
		DiscardPile discardPile = new DiscardPile();
		Stack<Card> drawPile = new Stack<Card>();
		for(int i=15; i<deck.length; i++){
			drawPile.push(deck[i]);
		}
		
		System.out.println("draw pile is : " + Arrays.toString( Arrays.copyOfRange(deck, 15, deck.length) ));
		
		deck = null;  
		
		boolean win = false;
		int player = -1;    // start game play with player 0
		
		ArrayList<Player> people = new ArrayList<Player>(Arrays.asList(players));
		discardPile.add( drawPile.pop() );
		
		while( !win ){
			player = (player + 1) % players.length;
			System.out.println("player " + player);
			System.out.println("draw pile    : " + drawPile.peek() );
			System.out.println("discard pile : " + discardPile.top() );

			win = people.get(player).play(discardPile, drawPile, people);

			System.out.println("draw pile   : " + drawPile.peek() );
			System.out.println("discard pile : " + discardPile.top() );
			
		}
		System.out.println("winner is player " + player);
		
	}
}
