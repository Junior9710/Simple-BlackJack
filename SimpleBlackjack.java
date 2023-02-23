import java.io.*;
public class SimpleBlackjack
{
    // Integer arrays to contain each of the players' drawn card values.
    static Card[] computerCards = new Card[12];  
    static Card[] userCards = new Card[12];
    
    static int computerCardsCount = 0;
    static int userCardsCount = 0;
    
    // Create a new deck of cards.
    static Deck deck = new Deck();

    // Variables to keep the score of each player.
    static int computerSum;
    static int userSum;
    
    public static void main(String[] args)
    {
        // Deal two cards to each player.
        computerCards[0] = deck.drawCard();
        computerCards[1] = deck.drawCard(); 
        computerCardsCount = 2;
        userCards[0] = deck.drawCard();
        userCards[1] = deck.drawCard();
        userCardsCount = 2;
        
        // Variables to keep the score of each player.
        computerSum = computerCards[0].value + computerCards[1].value;
        userSum = userCards[0].value + userCards[1].value;

        // Construction site...
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        String Computer = "Game Start" ;
        String Player1 = "";

            System.out.println("\n\n");
            
           while((userSum < 21) || (Computer.equals("Begin") )){
               
           // shows your cards not computers
                System.out.println("Your Turn!");
                displayCards(false);
           
           // option to draw another card
               System.out.println("Would you like to take another card (Y/N): ");
               try{
               Player1 = in.readLine();
               Player1 = Player1.toUpperCase();
               }catch(IOException e){
                   System.out.print("Please input either Y or N ");
               }
           
           //if you take a new card 
                if(Player1.equalsIgnoreCase("Y")){
                // increase cards
                userCardsCount++;
                // add a new card to deck
                userCards[userCardsCount-1] = deck.drawCard();
                //adss up usersum 
                userSum = userSum + userCards[userCardsCount-1].value;
                if(userSum > 21){
                   // exits the program if the score is above 18 it will not draw a card
                   
                   displayCards(true);
                   System.out.println("Game ending you lose, your score is over 21");
                   System.exit(0);
                   //losing message
               }
                System.out.println("Draw: "+ userCards[userCardsCount-1].name);
                displayCards(false);
                System.out.println("\n");
                
                }if(Player1.equalsIgnoreCase("N")){
                    //if no it is the end of the computers turn
                    Computer = "stop";
                    System.out.println("End of your turn");
                                    
                    System.out.println("\n");
                    break;
                    
                                        
                }
                
                
           
           }
           Computer = "play";   
           System.out.println("Computers turn");
           displayCards(true);
           while((computerSum < userSum) || (computerSum < 21) || (Computer.equals("play") )){
               if((computerSum > 18)|| (computerSum > userSum)){
                   // exits the program if the score is above 18 or computer score is more than users score, it will not draw a card
                   Computer = "stop";
                   break;
               }else{          
               // increase the computercardcount
               computerCardsCount++;
               // add new card to the deck 
               computerCards[computerCardsCount-1] = deck.drawCard();
               //increase computersum 
               computerSum = computerSum + computerCards[computerCardsCount-1].value;
               
               if (computerSum > 21){
                   
                   System.out.println("The Computer has: "+computerCards[computerCardsCount-1].name );
                   displayCards(true);
                   System.out.println("Winner, the computers score is over 21");
                   System.exit(0);
               
               }
               if((Computer.equalsIgnoreCase("play"))){
               System.out.println("The Computer has: "+computerCards[computerCardsCount-1].name );
               displayCards(true);
               System.out.println("\n");
               }
               }
               
           
           }
           
            if((computerSum >= userSum) ){
                System.out.println("Game ending, you lose, your score was not high enough");
                
    
            }
            
            if(computerSum < userSum){
                System.out.println("You win");
            }
    }
    
    public static void displayCards(boolean showHidden){
        if(showHidden) {
            System.out.print("Computer's cards: [" + computerCards[0].name + "]");
        } else {
            System.out.print("Computer's cards: [X]");
        }
        for(int i = 1; i < computerCardsCount; i++) {
            System.out.print("[" + computerCards[i].name + "]");
        }
        if(showHidden) {
            System.out.print(" (sum: " + computerSum + ")");
        }
        
        System.out.print("\nUser's cards: ");
        for(int i = 0; i < userCardsCount; i++) {
            System.out.print("[" + userCards[i].name + "]");
        }
        System.out.print(" (sum: " + userSum + ")");

        System.out.print("\n");
    }
}


