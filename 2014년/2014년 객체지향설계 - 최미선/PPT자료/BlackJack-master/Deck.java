import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Collections;

/**
 * Write a description of class Deck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Deck extends Actor
{
    private ArrayList<Card> cards;
    /** The number of sets of each of the suits. */
    private int spades, clubs, hearts, diamonds;
    private Card.Colour colour;
    /** The location of the picture of an empty deck (outline of a deck.) */
    private static final String EMPTY_DECK = "empty.png";
    private int showNum;
    
    /**
     * Create a customised deck of a certain colour
     * @param colour the colour of the deck and the suits required
     */
    public Deck(Card.Colour colour, int spaces, int clubs, int hearts, int diamonds)
    {
        this.colour = colour;
        this.diamonds = diamonds;
        this.clubs = clubs;
        this.spades = spades;
        this.hearts = hearts;
        setColour();
        fill();
        shuffle();
    }
    
    /**
     * Fill the deck with a complete set of cards. Get rid of any cards
     * still in the deck.
     */
    
    public void fill()
    {
        cards = new ArrayList<Card>();
        for(Card.Suit suit : Card.Suit.values()) {
            for(Card.Value value : Card.Value.values()){
                cards.add(new Card(colour, value, suit, false));
            }
        }
        setColour();
    }
    
    /**
     * Shuffle the deck
     */
    public void shuffle()
    {
        Collections.shuffle(cards);
    }
    
    /**
     * Set the deck to a certain colour
     */
    private void setColour()
    {
        if(colour==Card.Colour.BLUE) {
            setImage(new GreenfootImage("images/cards/blueflip.png"));
        }
        else {
            setImage(new GreenfootImage("images/cards/redflip.png"));
        }
    }
    
    /**
     * Draw a card from the deck.
     * @return the card that's been drawn, or null if no cards are left.
     */
    public Card drawCard()
    {
        if(getSize()==0) return null;
        Card card = cards.get(0);
        cards.remove(card);
        if(getSize()==0) {
            setImage(new GreenfootImage(Card.CARD_IMAGE_LOCATION+EMPTY_DECK));
        }
        return card;
    }
    
    /**
     * Get the size of the deck
     * @return the number of cards left in the deck
     */
    public int getSize()
    {
        return cards.size();
    }
    
    /**
     * Draw a card from the deck but flip it
     * @return the card that's been drawn, or null if no cards are left.
     */
    public Card drawFlippedCard()
    {
        if(getSize()==0) return null;
        Card card = cards.get(0);
        cards.remove(card);
        if(getSize()==0) {
            setImage(new GreenfootImage(Card.CARD_IMAGE_LOCATION+EMPTY_DECK));
        }
        return card;
    }

    /**
     * Act - do whatever the Deck wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
