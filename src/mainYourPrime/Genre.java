package mainYourPrime;

public abstract class Genre {
	// the superclass for our NotSpotify project
	// variable(s):
	//  price (double)
	// 
	// method(s):
	//  setPrice(int rating) - abstract method for genre sub-types
	public double price = 0.0;
	abstract double setPrice(int rating);
}