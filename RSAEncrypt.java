import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class RSAEncrypt 
{
	private BigInteger publicExponent = new BigInteger("65537");
	private BigInteger modulus;
	
	
	//main
	public static void main(String[] args) 
	{
		Scanner kb = new Scanner(System.in);
		
		
		try 
		{
			System.out.println("Enter key file path: ");
			RSAEncrypt encryptor = new RSAEncrypt(kb.nextLine());
			
			System.out.println("Enter message file path: ");
			String sourcePath = kb.nextLine();
			
			System.out.println("Enter ciphertext destination file path: ");
			String destinationPath = kb.nextLine();
			
			encryptor.encryptFromFile(sourcePath, destinationPath);
			System.out.println("File encrypted!");
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found. Terminating Program");
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			System.out.println("IO error. Terminating Program");
			e.printStackTrace();
		}
		kb.close();
	}
	
	//Constructors
	public RSAEncrypt(BigInteger modulus) 
	{
		this.modulus = modulus;
	}
	public RSAEncrypt(String keyFilePath) throws FileNotFoundException 
	{
		BigInteger[] keys = EncryptionIO.readKey(keyFilePath); 
		publicExponent = keys[1];
		modulus = keys[0];
	}
	
	// encrypts the message contained by the file at sourcePath and returns a String of the encrypted message.
	//		if the parameter destinationPath is specified it writes the ciphertext to the file at that path
	//		if no path exists, the files and directories required are created
	public String encryptFromFile(String sourcePath, String destinationPath) throws IOException
	{
		String cipherText = encryptFromFile(sourcePath);
		EncryptionIO.writeMessage(destinationPath, cipherText);
		return cipherText;
		
	}
	public String encryptFromFile(String sourceFile) throws IOException
	{
		String message = EncryptionIO.readMesage(sourceFile);
		return encrypt(message).toString();
	}
	
	
	//Receives the message and returns the ciphertext
	public BigInteger encrypt(String message)
	{
		BigInteger messageValue = MessageConverter.stringToBigInteger(message); 
		return messageValue.modPow(publicExponent, modulus);
	}
}
