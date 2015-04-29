/**
*Yassine Khaliqui
*109050245
*Homework Assignment Number 1
*CSE 214 Recitation Section 06
*Recitation TA: Kevin Flyangolts 
*Grading TA: Zheyuan (Jeffrey) Gao;
*@author Yassine
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Stackotaire {
	public static void main(String[] args){
		Card newCard = new Card();
		String values[] = {" ","A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	    char suits[]    = {' ', '\u2666', '\u2663','\u2665', '\u2660'};
		List<Card> list = new ArrayList<Card>(52);
		CardStack stock = new CardStack('s');
		CardStack waste = new CardStack('w');
		CardStack[] tableau = {null, new CardStack('t'), new CardStack('t'), new CardStack('t'), new CardStack('t'), new CardStack('t'), new CardStack('t'), new CardStack('t')};
		CardStack[] foundation = {null, new CardStack('f'), new CardStack('f'), new CardStack('f'), new CardStack('f')}; 
		Scanner s = new Scanner(System.in);
		String[] input;
		int index = 0;
		int index2 = 0;
		for(int i = 1; i < 5; i++){
			for(int j = 1; j < 14; j++){
				newCard = new Card(values[j], suits[i], false, j);
				list.add(newCard);
			}
		}
		Collections.shuffle(list);
		reset(newCard, list, foundation, waste, stock, tableau);
		display(foundation, waste, stock, tableau);
		input = s.nextLine().split(" ");
		while(!input[0].equalsIgnoreCase("quit")){
			if(foundation[1].size() + foundation[2].size() + foundation[3].size() + foundation[4].size() == 52)
				System.out.println("YOU WON!");
			if(input[0].equalsIgnoreCase("draw")){
				if(stock.size() == 0){
					int size = waste.size();
					for(int i = 0; i < size; i++){
						newCard = waste.pop();
						stock.push(newCard);
					}
				}
				newCard = stock.pop();
				newCard.setFaceUp(true);
				waste.push(newCard);
			}
			else if(input[0].equalsIgnoreCase("move")){
				index = input[1].charAt(1) - 48;
				index2 = input[2].charAt(1) - 48;
				
				if(input[1].toLowerCase().charAt(0) == 'w'){
					if(input[2].charAt(0) == 'f'){
						try{
							toFoundation(waste, foundation[index2]);
						}catch(IllegalMoveException ex){
							ex.getMessage();
						}
					}
					else if(input[2].toLowerCase().charAt(0) == 't'){
						try{
							toTableau(waste, tableau[index2]);
						}catch(IllegalMoveException ex){
							ex.getMessage();
						}
					}
				}
				else if(input[1].toLowerCase().charAt(0) == 'f'){
					if(input[2].toLowerCase().charAt(0) == 't'){
						try{
							toTableau(foundation[index], tableau[index2]);
						}catch(IllegalMoveException ex){
							ex.getMessage();
						}
					}
				}
				else if(input[1].toLowerCase().charAt(0) == 't'){
					if(input[2].toLowerCase().charAt(0) == 'f'){
						try{
							toFoundation(tableau[index], foundation[index2]);
						}catch(IllegalMoveException ex){
							ex.getMessage();
						}
					}
					else if(input[2].toLowerCase().charAt(0) == 't'){
						try{
							toTableau(tableau[index], tableau[index2]);
						}catch(IllegalMoveException ex){
							ex.getMessage();
						}
					}
				}
			}
			else if(input[0].equalsIgnoreCase("moveN")){
				index = input[1].charAt(1) - 48;
				index2 = input[2].charAt(1) - 48;
				int num = Integer.parseInt(input[3]);
				try{
					toMoveN(tableau[index], tableau[index2], num);
				}catch(IllegalMoveException ex){
					ex.getMessage();
				}
			}
			else if(input[0].equalsIgnoreCase("restart")){
				reset(newCard, list, foundation, waste, stock, tableau);
			}
			else
				System.out.println("Print a valid command.");
			display(foundation, waste, stock, tableau);
			input = s.nextLine().split(" ");
			}
		System.out.print("Thank you for playing Stackotaire!");
	}
	/**
	 * Displays the cards after each move.
	 * @param foundation: array of all of the foundation stacks.
	 * @param waste: the waste stack.
	 * @param stock: the stock stack.
	 * @param tableau: array of all of the tableau stacks.
	 */
	public static void display(CardStack[] foundation, CardStack waste, CardStack stock, CardStack[] tableau){
		foundation[1].printStack('f'); foundation[2].printStack('f'); foundation[3].printStack('f'); foundation[4].printStack('f'); 
		System.out.print("     "); waste.printStack('w');
		System.out.print("   "); stock.printStack('s'); 
		System.out.println();
		System.out.println();
		System.out.print("T7 "); tableau[7].printStack('t'); System.out.print("T6 ");tableau[6].printStack('t'); System.out.print("T5 "); tableau[5].printStack('t'); System.out.print("T4 "); tableau[4].printStack('t'); System.out.print("T3 "); tableau[3].printStack('t'); System.out.print("T2 "); tableau[2].printStack('t'); System.out.print("T1 "); tableau[1].printStack('t');
	}
	/**
	 * Places all of the cards back into the deck, shuffles the deck, and deals the cards to their respective stacks.
	 * @param newCard: information holder card.
	 * @param list: the deck.
	 * @param foundation: array of all of the foundation stacks.
	 * @param waste: the waste stack.
	 * @param stock: the stock stack.
	 * @param tableau: array of all of the tableau stacks.
	 */
	public static void reset(Card newCard, List<Card> list, CardStack[] foundation, CardStack waste, CardStack stock, CardStack[] tableau){
		int size = 0;
		if(list.size() != 52){
			size = stock.size();
			for(int i = 0; i < size; i++){
				newCard = stock.pop();
				list.add(newCard);
			}
			size = waste.size();
			for(int i = 0; i < size; i++){
					newCard = waste.pop();
					list.add(newCard);
			}
			for(int i = 1; i < 5; i++){
				size = foundation[i].size();
				for(int j = 0; j < size; j++){
					newCard = foundation[i].pop();
					list.add(newCard);
				}
			}
			for(int i = 1; i < 8; i++){
				size = tableau[i].size();
				for(int j = 0; j < size; j++){
					newCard = tableau[i].pop();
					list.add(newCard);
				}
			}
		}
		Collections.shuffle(list);
		for(int i = 0; i < 24; i++){
			stock.push((Card) list.remove(0));
		}
		for(int i = 1; i < 8; i++){
			for(int j = 0; j < i; j++){
				if(j == i - 1){
					newCard = (Card) list.remove(0);
					newCard.setFaceUp(true);
					tableau[i].push(newCard);
				}
				else{
					newCard = (Card) list.remove(0);
					newCard.setFaceUp(false);
					tableau[i].push(newCard);	
				}
			}
		}
	}
	/**
	 * Moves n cards from one stack to the next.
	 * @param from: the location of the first card.
	 * @param to: the destination stack.
	 * @param num: the value of n.
	 * @throws IllegalMoveException: triggers if the move does not abide by the rules of Solitaire.
	 */
	public static void toMoveN(CardStack from, CardStack to, int num) throws IllegalMoveException{
		Card[] store = new Card[num];
		if(from.size() == 0 || from.size() < num){
			throw new IllegalMoveException();
		}
		for(int i = 0; i < num; i++){
			if(from.peek().isFaceUp()){
				store[i] = from.pop();
			}
		}
		if(store[num - 1] != null || to.size() == 0){
			for(int i = num - 1; i >= 0; i--){
				if(store[i] != null){
					to.push(store[i]);
				}
			}
		}
		else if(store[num - 1] == null || store[num - 1].isRed() == to.peek().isRed() || store[num - 1].getIndex() + 1 != to.peek().getIndex()){
			for(int i = num - 1; i >= 0; i--){
				if(store[i] != null){
					from.push(store[i]);
				}
			}
			throw new IllegalMoveException();
		}
		else{	
			for(int i = num - 1; i >= 0; i--){
				if(store[i] != null){
					to.push(store[i]);
				}
			}
		}
	}
	/**
	 * Moves cards to the foundation stacks.
	 * @param from: the location of the card to be moves.
	 * @param to: the destination stack.
	 * @throws IllegalMoveException: triggers if the move does not abide by the rules of Solitaire.
	 */
	public static void toFoundation(CardStack from, CardStack to) throws IllegalMoveException{
		Card first;
		Card second;
		if(from.size() == 0)
			throw new IllegalMoveException();
		if(to.size() == 0){
			first = from.peek();
			if(first.getValue().equals("A"))
				to.push(from.pop());
			else{
				throw new IllegalMoveException();

			}
		}
		else{
			first = from.peek();
			second = to.peek();
			if(!(first.getSuit() == second.getSuit())){
				throw new IllegalMoveException();
			}
			else{
				int a = first.getIndex();
				int b = second.getIndex();
				if(b + 1 == a){
					to.push(from.pop());
				}
				else
					throw new IllegalMoveException();
			}
		}
	}
	/**
	 * Moves cards to the tableau stacks.
	 * @param from: the location of the card to be moved.
	 * @param to: the destination stack.
	 * @throws IllegalMoveException: triggers if the move does not abide by the rules of Solitaire.
	 */
	public static void toTableau(CardStack from, CardStack to) throws IllegalMoveException{
		Card first;
		Card second;
		if(from.size() == 0){
			throw new IllegalMoveException();
		}
		if(to.size() == 0){
			first = from.peek();
			if(first.getValue().equals("K"))
				to.push(from.pop());
			else{
				throw new IllegalMoveException();
			}
		}
		else{
			first = from.peek();
			second = to.peek();
			if(first.isRed() == second.isRed()){
				throw new IllegalMoveException();
			}
			else{
				int a = first.getIndex();
				int b = second.getIndex();
				if(a + 1 == b){
					to.push(from.pop());
				}
				else
					throw new IllegalMoveException();
			}
		}	
	}
}
