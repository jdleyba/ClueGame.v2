package clueGame;

import java.util.List;
import java.util.Map;

import clueGame.CardType;
//Will Hu Josh Leyba
public class Cards {
	
	private String name;
	
	private CardType cardType;
	
	protected Cards[] weapons;
	
	protected Cards[] players;
	
	protected Cards[] rooms;
	
	private Map<Integer, List<Cards>> playerHands;
	
	
	private void weaponsMake() {
		int cardCount = 0;			//The current amount of cards in the array Weapons.
		this.name = "Frying Pan";
		weapons[cardCount] = this;
		cardCount++;
		this.name = "Knife";
		weapons[cardCount] = this;
		cardCount++;
		this.name = "Spoon";
		weapons[cardCount] = this;
		cardCount++;
		this.name = "Fists";
		weapons[cardCount] = this;
		cardCount++;
		this.name = "The Sun";
		weapons[cardCount] = this;
		cardCount++;
		this.name = "Moustrap";
		weapons[cardCount] = this;
		cardCount++;
		while(cardCount >= 0) {
			weapons[cardCount].cardType = CardType.WEAPON;
			cardCount--;
		}
	}
	private void roomsMake() {
		
	}
	
	private void playerMake() {
		int cardCount = 0;			//The current amount of cards in the array Weapons.
		this.name = "Mr. Green Center";
		players[cardCount] = this;
		cardCount++;
		this.name = "Colonel Mines Mustard";
		players[cardCount] = this;
		cardCount++;
		this.name = "Josh";
		players[cardCount] = this;
		cardCount++;
		this.name = "Will";
		players[cardCount] = this;
		cardCount++;
		this.name = "Baldwin";
		players[cardCount] = this;
		cardCount++;
		this.name = "Ben";
		players[cardCount] = this;
		cardCount++;
		while(cardCount >= 0) {
			players[cardCount].cardType = CardType.PERSON;
			cardCount--;
	}
}
}