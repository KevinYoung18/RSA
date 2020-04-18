import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) throws IOException
	{
		Scanner kb = new Scanner(System.in);
		
		try 
		{
			//key generation
			System.out.println("KEY GENERATION");
			System.out.println();
			RSAGenKey keygen = new RSAGenKey();
			System.out.println("Enter path to save keys: ");
			String pathName = kb.nextLine();
			keygen.writeToFile(pathName);
			System.out.println("Keys saved!");
			
			//encryption
			System.out.println();
			System.out.println("ENCRYPTION");
			System.out.println();
			System.out.println("Enter key file path: ");
			RSAEncrypt encryptor = new RSAEncrypt(kb.nextLine());
			
			System.out.println("Enter message file path: ");
			String sourcePath = kb.nextLine();
			
			System.out.println("Enter ciphertext destination file path: ");
			String destinationPath = kb.nextLine();
			
			encryptor.encryptFromFile(sourcePath, destinationPath);
			System.out.println("File encrypted!");
			
			//decryption
			System.out.println();
			System.out.println("DECRYPTION");
			System.out.println();
			System.out.println("Enter key file path: ");
			RSADecrypt decryptor = new RSADecrypt(kb.nextLine());
			
			System.out.println("Enter ciphertext file path: ");
			sourcePath = kb.nextLine();
			
			System.out.println("Enter message destination path: ");
			destinationPath = kb.nextLine();
			
			decryptor.decryptFromFile(sourcePath, destinationPath);
			System.out.println("File decrypted!");
			
			
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found. Terminating Program");
		} 
		
		catch (IOException e) 
		{
			System.out.println("Unable to write to file. Terminating program");
		}
		
		kb.close();
	}
}
