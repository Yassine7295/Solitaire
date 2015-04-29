/**
*Yassine Khaliqui
*109050245
*Homework Assignment Number 1
*CSE 214 Recitation Section 06
*Recitation TA: Kevin Flyangolts 
*Grading TA: Zheyuan (Jeffrey) Gao;
*@author Yassine
*/
public class Card {
	
	String value;
	char suit;
	boolean isFaceUp;
	int index;
	/**
	 * Creates a new Card object with no parameters.
	 * <dt><b>Postcondition:</b><dd> This card has been initialized.
	 */
	public Card(){}
	/**
	 * Creates a new Card object with parameters.
	 * @param value: the value of that card that will be displayed.
	 * @param suit: the suit of the card.
	 * @param isFaceUp: whether or not the card is face up.
	 * @param index: the point value of the card.
	 * <dt><b>Postcondition:</b><dd> This card has been initialized.
	 */
	public Card(String value, char suit, boolean isFaceUp, int index){
		this.value = value;
		this.suit = suit;
		this.isFaceUp = isFaceUp;
		this.index = index;
	}
	/**
	 * Sets the index of a card object.
	 * @param index: the value to be set for the point value.
	 */
	public void setIndex(int index){
		this.index = index;
	}
	/**
	 * Returns the index of the card object.
	 * @return the value of the index.
	 */
	public int getIndex(){
		return index;
	}
	/**
	 * Sets the value of the card object.
	 * @param value: the string that will be set for value.
	 */
	public void setValue(String value){
		this.value = value;
	}
	/**
	 * Sets the suit of the card object.
	 * @param suit: the character value that will be set for suit.
	 */
	public void setSuit(char suit){
		this.suit = suit;
	}
	/**
	 * Sets the face up value of the card object.
	 * @param isFaceUp: the value to be set to isFaceUp.
	 */
	public void setFaceUp(boolean isFaceUp){
		this.isFaceUp = isFaceUp;
	}
	/**
	 * Returns the string notation of the value of a card object.
	 * @return the string notation of the value.
	 */
	public String getValue(){
		return value;
	}
	/**
	 * Returns the suit value of the card object.
	 * @return the suit value.
	 */
	public char getSuit(){
		return suit;
	}
	/**
	 * Returns the face up value of the card object.
	 * @return the face up value.
	 */
	public boolean isFaceUp(){
		return isFaceUp;
	}
	/**
	 * Returns whether or not the card is a red card.
	 * @return a boolean value of whether or not the card is red.
	 */
	public boolean isRed(){
		if(suit == '\u2666' || suit == '\u2665')
			return true;
		else 
			return false;
	}
	/**
	 * Returns the visual representation of the card object.
	 * @return formatted visual of the card object.
	 */
	public String toString(){
		return "[" + getValue() + getSuit() + "]";
	}
	
}
