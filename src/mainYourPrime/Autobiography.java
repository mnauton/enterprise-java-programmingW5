package mainYourPrime;

public class Autobiography extends Genre {

	@Override
	double setPrice(int rating) {
		// TODO complete the method using the fees in the document
		if (rating > 3) {
			return 2.99;
		} else if (rating == 3) {
			return 1.99;
		} 
		return 0.99;
	}
}
