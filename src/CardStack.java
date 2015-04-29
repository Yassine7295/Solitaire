/**
*Yassine Khaliqui
*109050245
*Homework Assignment Number 1
*CSE 214 Recitation Section 06
*Recitation TA: Kevin Flyangolts 
*Grading TA: Zheyuan (Jeffrey) Gao;
*@author Yassine
*/
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class CardStack extends Stack{

	char type;
	Deque<Card> stack;
	/**
	 * Creates a new CardStack object with no parameters.
	 * <dt><b>Postcondition:</b><dd> This card stack has been initialized.
	 */
	public CardStack(){
		stack = new ArrayDeque<Card>();
	}
	/**
	 * Creates a new CardStack object with a parameter.
	 * @param 
	 * <dt><b>Postcondition:</b><dd> This card stack has been initialized.
	 */
	public CardStack(char type){
		this.type = type;
		stack = new ArrayDeque<Card>();
	}
	/**
	 * Pushes a card object into the card stack.
	 * @param newCard: the card object to be pushed.
	 * <dt><b>Precondition:</b><dd> This CardStack object has been initialized.
	 * <dt><b>Postcondition:</b><dd> The card has been push into the card stack.
	 */
	public void push(Card newCard){
		stack.push(newCard);
	}
	/**
	 * Pops a card object from the card stack.
	 * <dt><b>Precondition:</b><dd> This CardStack object has been initialized.
	 * <dt><b>Postcondition:</b><dd> The card has been popped from the card stack.
	 * @return the top object is returned.
	 */
	public Card pop(){
		return stack.pop();
	}
	/**
	 * Peek the top card of the card stack.
	 * <dt><b>Precondition:</b><dd> This CardStack object has been initialized.
	 * @return a copy of the top card is returned.
	 */
	public Card peek(){
		return stack.peek();
	}
	/**
	 * Determines whether or not the card stack is empty.
	 * <dt><b>Precondition:</b><dd> This CardStack object has been initialized.
	 * @return a boolean value of where the stack is empty.
	 */
	public boolean isEmpty(){
		return stack.isEmpty();
	}
	/**
	 * Determines the size of the card stack.
	 * <dt><b>Precondition:</b><dd> This CardStack object has been initialized.
	 * @return an integer value of the number of cards in the card stack.
	 */
	public int size(){
		return stack.size();
	}
	/**
	 * Returns the type associated with the card stack.
	 * <dt><b>Precondition:</b><dd> This CardStack object has been initialized.
	 * @return a char value of the type.
	 */
	public char getType(){
		return type;
	}
	/**
	 * Depending on the type of stack, this method will print out the stack.
	 * @param type: the type of the stack.
	 * @return returns the formatted visual display of the stack.
	 */
	public void printStack(char type){
		if(type == 's'){
			System.out.print("[XX] " + size());
		}
		else if(type == 'w'){
			if(size() == 0)
				System.out.print("W [  ]");
			else
				System.out.print("W " + peek().toString());
		}
		else if(type == 'f'){
			if(size() != 0)
				System.out.print(peek().toString());
			else
				System.out.print("[XX]");
		}
		else if(type == 't'){
			if(size() != 0){
				Card[] array = new Card[size()];
				if(peek().isFaceUp() == false){
					Card newCard = pop();
					newCard.setFaceUp(true);
					push(newCard);
				}
				for(int i = 0; i < array.length; i++){
					array[i] = pop();
				}
				for(int i = array.length - 1; i >= 0; i--){
					if(array[i].isFaceUp()){
						System.out.print(array[i].toString() + " ");
					}	
					else
						System.out.print("[XX] ");
					push(array[i]);
				}
			}
			else
				System.out.print("[  ]");
			System.out.println();
		}
	}
}
