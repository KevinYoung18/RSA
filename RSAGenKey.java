import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSAGenKey 
{
	private BigInteger primeP;
	private BigInteger primeQ;
	private BigInteger publicExponent = new BigInteger("65537");
	private BigInteger secretExponent;
	private BigInteger modulus;
	
	//main
	public static void main(String[] args)
	{
		Scanner kb = new Scanner(System.in);
		
		try 
		{
			RSAGenKey keygen = new RSAGenKey();
			System.out.println("Enter path to save keys: ");
			String pathName = kb.nextLine();
			keygen.writeToFile(pathName);
			System.out.println("Keys saved!");
		} 
		catch (IOException e) 
		{
			System.out.println("Unable to write to file. Terminating program");
		}
		
		kb.close();
		
	}
	
	
	RSAGenKey()
	{
		generatePrimes();
	}
	
	
	
	//Writes the modulus, publicExponent, and secretExponent, respectively to the file specified. 
	//		Each variable is written as a string and separated by a newline
	public void writeToFile(String pathName) throws IOException 
	{
		EncryptionIO.writeKey(pathName, modulus, publicExponent, secretExponent);
	}
	
	//generate variables primeP and primeQ as random 512 bit prime numbers
	//		generate the rest of the variables based on those numbers
	public void generatePrimes()
	{
		SecureRandom rand = new SecureRandom();
		primeP = new BigInteger(512, 100, rand);
		primeQ = new BigInteger(512, 100, rand);
		
		while(primeP.mod(publicExponent).equals(BigInteger.ONE))
		{
			primeP = new BigInteger(512, 100, rand);
		}
		while(primeQ.mod(publicExponent).equals(BigInteger.ONE))
		{
			primeQ = new BigInteger(512, 100, rand);
		}
		if(primeP.equals(primeQ))
		{
			generatePrimes();
		}
		
		modulus = primeP.multiply(primeQ);
		
		
		BigInteger eulersTotient = (primeP.subtract(BigInteger.ONE))
				.multiply(primeQ.subtract(BigInteger.ONE));
		
		secretExponent = publicExponent.modInverse(eulersTotient);
	}
	

	
	//getters
	public BigInteger getPrimeP() {
		return primeP;
	}
	public BigInteger getPrimeQ() {
		return primeQ;
	}
	public BigInteger getPublicExponent() {
		return publicExponent;
	}
	public BigInteger getSecretExponent() {
		return secretExponent;
	}
	public BigInteger getModulus() {
		return modulus;
	}
	
}
