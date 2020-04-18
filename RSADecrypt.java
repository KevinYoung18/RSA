import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class RSADecrypt 
{
	private BigInteger secretExponent;
	private BigInteger modulus;
	
	//main
	public static void main(String[] args) 
	{
		Scanner kb = new Scanner(System.in);
		
		try 
		{
			System.out.println("Enter key file path: ");
			RSADecrypt decryptor = new RSADecrypt(kb.nextLine());
			
			System.out.println("Enter ciphertext file path: ");
			String sourcePath = kb.nextLine();
			
			System.out.println("Enter message destination path: ");
			String destinationPath = kb.nextLine();
			
			decryptor.decryptFromFile(sourcePath, destinationPath);
			System.out.println("File decrypted!");
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found. Terminating Program");
		} 
		catch (IOException e) 
		{
			System.out.println("IO error. Terminating Program");
			e.printStackTrace();
		}
		kb.close();
	}
	
	//Constructors
	public RSADecrypt(BigInteger secretExponent,BigInteger modulus)
	{
		this.secretExponent = secretExponent;
		this.modulus = modulus;
	}
	public RSADecrypt(String keyFilePath) throws FileNotFoundException
	{
		BigInteger[] keys = EncryptionIO.readKey(keyFilePath); 
		secretExponent = keys[2];
		modulus = keys[0];
	}
	
	// decrypts the message contained by the file at sourcePath and returns a String of the decrypted message.
	//		if the parameter destinationPath is specified it writes the message to the file at that path
	//		if no path exists, the files and directories required are created
	public String decryptFromFile(String sourcePath, String destinationPath) throws IOException
	{
		String message = decryptFromFile(sourcePath);
		EncryptionIO.writeMessage(destinationPath, message);
		return message;
	}
	public String decryptFromFile(String sourcePath) throws IOException
	{
		BigInteger cipherText  = new BigInteger(EncryptionIO.readMesage(sourcePath));
		return decrypt(cipherText);
	}
	
	//decrypts cipherText and returns it as a string
	public String decrypt(BigInteger cipherText)
	{
		cipherText = cipherText.modPow(secretExponent, modulus);
		return MessageConverter.bigIntegerToString(cipherText);
	}
}
