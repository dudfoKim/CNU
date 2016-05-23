 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Table here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Table extends World
{

    // Deck Object
    public Deck mainDeck;
    
    //Button used when player ready to check hand against dealer
    public Button playButton;
    
    //Cards used within Gamplay
    public Card card,blandCard,dealer1,dealer2,placeCard,tempCard;
    
    //Players Cards Array
    public Card[] cardGrid = new Card[5];
    
    //Card Array used to hide dealers Cards
    public Card[] blankGrid = new Card [5];
    
    //Dealers Cards Array
    public Card[] dealerCard = new Card[5];
    
    //Various counts used within game
    public int dealerCount = 2;
    public int playerCount = 0;
    public int dealerTotal = 0;
    public int playerTotal = 0;

    /**
     * Constructor for objects of class Table.
     * 
     */
    public Table()
    {    
        //Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        
        //Create the deck of cards to draw from
        mainDeck = new Deck(Card.Colour.BLUE, 1, 1, 1, 1);
        addObject(mainDeck, 500, 500);
        
        //Add the play cards button which when pressed will check player cards against dealer cards
        playButton = new Button();
        playButton.setImage("images/playcard.png");
        addObject(playButton, 570, 150);
        
        //Draw the dealers hand of cards
        dealerHand();
        
        //Set up the spaces for the players hand of cards
        for (int row = 0; row < 5; row++){
            //card = new Card(Blank=true);
            cardGrid[row] = new Card(true);
            addObject(cardGrid[row], 100 + 90*row+1, 150);
        }
        
        //Start the scenario and await player responses
        Greenfoot.start();
    }
    
    public void dealerHand()
    {
        //Draw first dealer card
        dealerCard[0] = mainDeck.drawCard();
        
        //Draw second dealer card
        dealerCard[1] = mainDeck.drawCard();
        
        //Calculate initial dealer total to decide whether to take more cards
        dealerTotal = dealerCard[0].getNumValue() + dealerCard[1].getNumValue();
        
        //Play dealer hand to draw more cards if required
        //- threshold is set at 15 but this could be lowered or raised
        if (dealerTotal!=21)
        {
            while((dealerTotal < 15) && (dealerCount < 5)){
                dealerCard[dealerCount] = mainDeck.drawCard();
                dealerCount++;
                //Start at row 2 since first two cards are already totaled.
                for (int row = 2; row <dealerCount; row++){
                    dealerTotal = dealerTotal + dealerCard[row].getNumValue();
                }
            }
        }
        
        //Set blank cards for remaining dealer cards
        for (int row = dealerCount; row < 5; row++){
            dealerCard[row] = new Card(true);
        }
        
        //Set and place turned cards for number of dealer cards
        for (int row = 0; row < dealerCount; row++){
            //card = new Card(Blank=true);
            blankGrid[row] = new Card(false);
            addObject(blankGrid[row], 100 + 90*row+1, 350);
        }
        
        //Set and place blank cards for number of dealer cards
        for (int row = dealerCount; row < 5; row++){
            //card = new Card(Blank=true);
            blankGrid[row] = new Card(true);
            addObject(blankGrid[row], 100 + 90*row+1, 350);
        }
    }
    
    public void endGame()
    {
        //Calculate the playerTotal
        for (int row = 0; row <playerCount; row++){
            playerTotal = playerTotal + cardGrid[row].getNumValue();
        }
        
        //Show dealer cards
        for (int row = 0; row < 5; row++){
            addObject(dealerCard[row], 100 + 90*row+1, 350);
        }
        
        //Check who wins
        if (dealerTotal >= playerTotal && dealerTotal <=21){
            //Dealer wins
            playButton.setImage("images/dealerwins.png");
        }
        else {
        if (playerTotal > dealerTotal && playerTotal <=21){
            //Player wins
            playButton.setImage("images/playerwins.png");
        }
        else{
        if (playerTotal <=21 && dealerTotal > 21){
            //Player wins
            playButton.setImage("images/playerwins.png");
        }
        else {
            //Dealer wins
            playButton.setImage("images/dealerwins.png");
        }
        }
        } 
        Greenfoot.stop();
    }
    
    /**
     * If the deck is clicked on, then draw a card from it (unless it's empty.)
     */
    public void act()
    {
        //React to play button being clicked.  No action will
        //take place until the player has placed two cards
        if(Greenfoot.mouseClicked(playButton) && playerCount >=2) {
            endGame();
        }
        
        //Reac to mouse click on any of players cards
        //Next available place will be filled
        if(Greenfoot.mouseClicked(cardGrid[0]) && mainDeck.getSize()>0 && playerCount <5){
            cardGrid[playerCount] = mainDeck.drawCard();
            playerCount++;
        }
        
        if(Greenfoot.mouseClicked(cardGrid[1]) && mainDeck.getSize()>0 && playerCount <5){
            cardGrid[playerCount] = mainDeck.drawCard();
            playerCount++;
        }
        
        if(Greenfoot.mouseClicked(cardGrid[2]) && mainDeck.getSize()>0 && playerCount <5){
            cardGrid[playerCount] = mainDeck.drawCard();
            playerCount++;
        }
        
        if(Greenfoot.mouseClicked(cardGrid[3]) && mainDeck.getSize()>0 && playerCount <5){
            cardGrid[playerCount] = mainDeck.drawCard();
            playerCount++;
        }
        
        if(Greenfoot.mouseClicked(cardGrid[4]) && mainDeck.getSize()>0 && playerCount <5){
            cardGrid[playerCount] = mainDeck.drawCard();
            playerCount++;
        }
        
        //Update cards drawn and placed by player
        for (int row = 0; row < playerCount; row++){
            addObject(cardGrid[row], 100 + 90*row+1, 150);
        }
    }
}





