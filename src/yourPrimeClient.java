import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import mainYourPrime.MyMedia;
import mainYourPrime.Subscriber;

public class yourPrimeClient {
	public static void main(String[] args) {
		Subscriber testUser = new Subscriber("TestUser", "test", "password", new MyMedia(null, null, null));
		yourPrimeClient primeClient = new yourPrimeClient();

		try {
			primeClient.login(testUser);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void go() throws UnknownHostException, IOException {
		Socket sk = new Socket("127.0.0.1", 4444);

		PrintWriter writer = new PrintWriter(sk.getOutputStream());
		writer.println("GET-B");
		writer.flush();

		InputStreamReader streamReader = new InputStreamReader(sk.getInputStream());
		BufferedReader reader = new BufferedReader(streamReader);

		// close stream and socket
		reader.close();
		sk.close();
	}
	
	// TODO complete to login function - user will contact server add request access to myMedia library
	// The server will authenticate user's login information, verify, and perform additional check on outstanding fees
	//
	public void login(Subscriber user) throws UnknownHostException, IOException {
		Socket s = new Socket("127.0.0.1", 4242);

		s.close();
	}
}
