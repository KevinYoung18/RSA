import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class EncryptionIO 
{
	
		//Writes the variables modulus, publicExponent and secretExponent, respectively to the file specified by the pathName. Each variable is written as a string and separated by a newline
		//	if the file or directory specified by the path name does not exist, one is created.
		public static void writeKey(String pathName, BigInteger modulus, BigInteger publicExponent, BigInteger secretExponent ) throws IOException
		{
			Path path = Paths.get(pathName);
			String data = (modulus.toString() + System.lineSeparator() + publicExponent.toString() + System.lineSeparator() + secretExponent.toString());
			if(path.getParent() != null)
				Files.createDirectories(path.getParent());
			Files.write(path, data.getBytes());
		}
		
		//reads the key file at path and returns an array of BigIntegers representing the modulus, publicExponent and secretExponent respectively
		public static BigInteger[] readKey(String pathName) throws FileNotFoundException
		{
			BigInteger[] keys = new BigInteger[3];
			File file= new File(pathName);
			Scanner fileReader  = new Scanner(file);
		
			for(int i = 0; i < 3; i++)
			{
				String s = fileReader.nextLine();
				keys[i] = new BigInteger(s);
			}
			
			fileReader.close();
			
			return keys;
		}
		
		//reads the message from the file at the specified path
		// 		returns string of the message
		public static String readMesage(String pathName) throws IOException
		{
			String message = new String(Files.readAllBytes(Paths.get(pathName))); 
			return message;
		}
		
		//reads the to the file at the specified path. if the path does not exist,
		//		 the files and directories required are created
		public static void writeMessage(String pathName, String message) throws IOException
		{
			Path path = Paths.get(pathName);
			if(path.getParent() != null)
				Files.createDirectories(path.getParent());
			Files.write(path, message.getBytes());
		}
		
}
