/*
package secureYourPrime;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import mainYourPrime.MyMedia;
import mainYourPrime.Subscriber;

public class SecureClient {
	private SecretKey secretKey;
	private Cipher cipher;

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException,
		NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, 
		InvalidKeySpecException {

		Subscriber testUser = new Subscriber("TestUser", "test", "password", new MyMedia(null, null, null));
		SecureClient primeClient = new SecureClient();

		try {
			primeClient.login(testUser);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void login(Subscriber user) throws UnknownHostException, IOException, NoSuchAlgorithmException,
		NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, 
		InvalidAlgorithmParameterException {

		// Set up connection to the server
		Socket socket = new Socket("127.0.0.1", 4242);
		PrintWriter writer = new PrintWriter(socket.getOutputStream());
		
		// write message to send to server socket
		System.out.println("Requesting access to the server\n");
		writer.println(getMessage(user.getUserID(), user.getPassword()));
		writer.flush();

		// get message from the server to confirm connection
		InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
		BufferedReader reader = new BufferedReader(streamReader);
		String message = reader.readLine();
		System.out.println(message + "\n");

		if (message.equals("Access granted")) {
			String path = "/Users/eir/eclipse-workspace/YourPrime/src/secretFile.txt";
			System.out.println("Creating secret file, and transmitting the file over socket\n");
			writeFile(path);
			transmitFile(socket, path);
			System.out.println("Transmission completed\n");
		}
        
		// close stream and socket
		reader.close();
		socket.close();
	}

	public void setCipher(String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, 
		InvalidKeyException {
		this.secretKey = KeyGenerator.getKeyFromPassword();
		this.cipher = Cipher.getInstance(algorithm);
        this.cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	}

	// TODO complete the getMessage() method to return the encrypted String comprises of the
	// message to the server, username and password - use the encryption protocol available in the class
	//
	public String getMessage(String user, String password) throws InvalidKeyException, NoSuchAlgorithmException, 
		InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, 
		UnsupportedEncodingException{

	}

	// TODO complete the method to write an encrypted file using the cipherOutputStream
	//
	public void writeFile(String path) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, 
		NoSuchPaddingException {

	}
	
	// TODO transfer the file in bytes to preserve the encoding of the encrypted file
	// 
	public void transmitFile(Socket socket, String path) throws IOException {
	
	}
}
*/
