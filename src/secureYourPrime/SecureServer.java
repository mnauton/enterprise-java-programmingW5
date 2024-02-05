package secureYourPrime;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class SecureServer {
	private Map<String, String> userDb = new HashMap<>();
	private Map<String, Double> feesDb = new HashMap<>();
	private SecretKey secretKey;
	private Cipher cipher;
	
	public static void main(String[] args) throws NoSuchAlgorithmException, 
		InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException,
		BadPaddingException, InvalidKeySpecException {
		SecureServer server = new SecureServer();
		try {
			server.go();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void go() throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, 
		InvalidKeyException, IllegalBlockSizeException, BadPaddingException, 
		InvalidKeySpecException {
		try (ServerSocket serverSock = new ServerSocket(4242)) {
			while(true) {
				Socket s = serverSock.accept();
				BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
				PrintWriter writer = new PrintWriter(s.getOutputStream());

		        // TODO process authentication using encrypted message from the client side
				// you need to decode the String and pass it as argument to the authenticate message

				// TODO create a new thread to process encrypted file tranferred from client
				// considering that the server will be processing a number of request from client, then
				// this should be implemented as multithreaded operations
				//
				
			}
		}
	}

	private void setSecurity(String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException, 
		NoSuchPaddingException, InvalidKeyException {
		// TODO decode and then decipher the message
	    this.secretKey = KeyGenerator.getKeyFromPassword();
        this.cipher = Cipher.getInstance(algorithm);
        this.cipher.init(Cipher.DECRYPT_MODE, secretKey);
	}

	private String authenticate(String input) {
		String[] s = input.split("-");
		if (s[0].equals("LOGIN")) {
			if (checkUser(s[1], s[2]))
				if (!checkAccount(s[1]))
					return "Outstanding fees - you are barred";
			else {
				return "Access granted";
			}
		}
		return "Wrong credentials";
	}

	private boolean checkUser(String user, String password) {
		userDb.put("TestUser","password");
		if (userDb.containsKey(user) && password.equals(userDb.get(user)))
			return true;
		return false;
	}

	private boolean checkAccount(String user) {
		feesDb.put("TestUser",0d);
		if (feesDb.get(user) >= 0) {
			return true;
		}
		return false;
	}
}