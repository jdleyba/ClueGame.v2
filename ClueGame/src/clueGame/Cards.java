package clueGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Map;

import clueGame.CardType;
//Will Hu Josh Leyba
public class Cards {
	
	private String name;
	
	private CardType cardType;
	
	protected Cards[] weapons = new Cards[6]; //Holds all weapon cards
	
	protected Cards[] players = new Cards[6]; //Holds all player cards
	
	private ArrayList<Cards> temp = null; //A temporary arraylist of cards
	
	protected Cards[] rooms = new Cards[9]; //Holds all room cards
	
	private Map<Integer, ArrayList<Cards>> playerHands; //Used later to hold sorted cards in player hands 
	
	private ArrayList<Cards> closetHand; //Used as a solution hand
	
	private Cards holdCard = null;
	
	private void weaponsMake() {
		int cardCount = 0;			//The current amount of cards in the array Weapons.
		holdCard.name = "Frying Pan";
		weapons[cardCount] = holdCard;
		cardCount++;			    //Used in while loop later on
		holdCard.name = "Knife";
		weapons[cardCount] = holdCard;
		cardCount++;
		holdCard.name = "Spoon";
		weapons[cardCount] = holdCard;
		cardCount++;
		holdCard.name = "Fists";
		weapons[cardCount] = holdCard;
		cardCount++;
		holdCard.name = "The Sun";
		weapons[cardCount] = holdCard;
		cardCount++;
		holdCard.name = "Moustrap";
		weapons[cardCount] = holdCard;
		cardCount++;
		while(cardCount >= 0) {
			weapons[cardCount].cardType = CardType.WEAPON;
			cardCount--;
		}
	}
	private void roomsMake() {
		
		int cardCount = 0;			//The current amount of cards in the array rooms.
		
		holdCard.name = "Conservatory";
		rooms[cardCount] = holdCard;
		cardCount++;
		
		holdCard.name = "Kitchen";
		rooms[cardCount] = holdCard;
		cardCount++;
		
		holdCard.name = "Ballroom";
		rooms[cardCount] = holdCard;
		cardCount++;
		
		holdCard.name = "Billiard";
		rooms[cardCount] = holdCard;
		cardCount++;
		
		holdCard.name = "Library";
		rooms[cardCount] = holdCard;
		cardCount++;
		
		holdCard.name = "Study";
		rooms[cardCount] = holdCard;
		cardCount++;
		
		holdCard.name = "Dining Room";
		rooms[cardCount] = holdCard;
		cardCount++;
		
		holdCard.name = "Lounge";
		rooms[cardCount] = holdCard;
		cardCount++;
		
		holdCard.name = "Hall";
		rooms[cardCount] = holdCard;
		cardCount++;
		
		while(cardCount >= 0) {
			players[cardCount].cardType = CardType.ROOM;
			cardCount--;
		}
	}
	
	private void playerMake() {
		
		int cardCount = 0;			//The current amount of cards in the array player.
		
		holdCard.name = "Mr. Green Center";
		players[cardCount] = holdCard;
		cardCount++;
		
		holdCard.name = "Colonel Mines Mustard";
		players[cardCount] = holdCard;
		cardCount++;
		
		holdCard.name = "Josh";
		players[cardCount] = holdCard;
		cardCount++;
		
		holdCard.name = "Will";
		players[cardCount] = holdCard;
		cardCount++;
		
		holdCard.name = "Baldwin";
		players[cardCount] = holdCard;
		cardCount++;
		
		holdCard.name = "Ben";
		players[cardCount] = holdCard;
		cardCount++;
		
		while(cardCount >= 0) {
			players[cardCount].cardType = CardType.PERSON;
			cardCount--;
		}
	}
	
	public void deckMake() { //Functions for creating the deck
		weaponsMake();
		roomsMake();
		playerMake();
		
		String[] x = {"a","a","a","a","a","a"}; //Used to track which cards have been dealt, avoiding repeats
		String hold = "a";
		int cardNum = 0; //Used to avoid repeating the cards. The random element is which player gets the card
		int handNum = 0;
		
		while(cardNum != 5) { //Each while loop is responsible for a card type (ie Weapons, Rooms, and Players)
		
			int random = (int)(Math.random()*6);
		
			if(x[random]== hold) {
				temp = null;
				holdCard = weapons[cardNum];
				x[random] = weapons[cardNum].name;
				temp.add(holdCard);
				getPlayerHands().put(handNum, temp);
				handNum += 1;
				cardNum++;
			}
		
		}
		
		String[] y = {"a", "a", "a", "a", "a", "a"};
		cardNum = 0;
		handNum = 0;
		
		while(cardNum != 5) {
			
			int random = (int)(Math.random()*6);
			
			if(y[random]== hold) {
				temp = null;
				holdCard = null;
				holdCard = weapons[cardNum];
				y[random] = weapons[cardNum].name;
				temp.add(holdCard);
				temp.addAll(getPlayerHands().get(handNum));
				getPlayerHands().put(handNum, temp);
				handNum += 1;
				cardNum++;
			
			}
		
		}
		
		String[] z = {"a", "a", "a", "a", "a", "a", "a", "a", "a"};
		cardNum = 0;
		handNum = 0;
		boolean allCard = false;
		
		while(cardNum != 8) {
			
			int random = (int)(Math.random()*9);
			
			if(allCard) {
				if(getPlayerHands().get(0).size() == 3) { //Checks player hand size of the first player's hand. If it is equal to 3 and all hands have been dealt a card, it pops the hand at 0 and
					closetHand = getPlayerHands().get(0);
					getPlayerHands().remove(0);
					z[0] = "CLOSET HAND";
					allCard = false;
			
				}
				
				else if(getPlayerHands().get(6).size() == 3) { //Same as above
					closetHand = getPlayerHands().get(6);
					getPlayerHands().remove(6);
					z[6] = "CLOSET HAND";
					allCard = false;
						
				}
				
				else if(getPlayerHands().get(1).size() == 3) { //Same as above
					closetHand = getPlayerHands().get(1);
					getPlayerHands().remove(1);
					z[1] = "CLOSET HAND";
					allCard = false;
						
				}
			
			}
		
			if(z[random]== hold && getPlayerHands().get(random) != null) {
				temp = null;
				holdCard = null;
				holdCard = weapons[cardNum];
				z[random] = weapons[cardNum].name;
				temp.add(holdCard);
				temp.addAll(getPlayerHands().get(handNum));
				getPlayerHands().put(handNum, temp);
				handNum += 1;
				cardNum++;
			}
			
			if(handNum == 6) { //Tests for if all players have been dealt one card
				allCard = true;
				handNum = 2;
				for(int i = 0; i < 6; i++) {
					z[i] = "a";
				}
			
			}
		
		}
	}
	
	public Object getHands() {
		return getPlayerHands();
	}
	
	public void shuffle() {
		weapons = null;
		players = null;
		rooms = null;
		setPlayerHands(null);
		closetHand = null;
		deckMake();
	}
	public Map<Integer, ArrayList<Cards>> getPlayerHands() {
		return playerHands;
	}
	public void setPlayerHands(Map<Integer, ArrayList<Cards>> playerHands) {
		this.playerHands = playerHands;
	}
}