import java.math.BigInteger;

public class MessageConverter 
{
	
	final static int STRING_LENGTH = 3;
	
	//converts a string into a series of unsigned ints representing the ascii values of each char
	//		which are then concatenated to form a single BigInteger
	//		e.g. stringToBigInteger("abc") returns 97098099
	public static BigInteger stringToBigInteger(String string)
	{
		byte[] byteArray = string.getBytes();
		String intRepresentation = "";
		for(byte b : byteArray)
		{
			intRepresentation += byteToString(b);
		}
		return new BigInteger(intRepresentation);
	}
	
	// converts a byte to a string representation of its unsigned integer value, 
	//		if the string is less than  3 chars long it is padded with '0's
	//		e.g. byteToString(96) = "096"
	private static String byteToString(byte input)
	{
		Integer asInt = Byte.toUnsignedInt(input);
		String output = asInt.toString();
		while(output.length() < STRING_LENGTH)
		{
			output = "0" + output;
		}
		
		return output;
	}
	
	// parses a BigInteger to convert it to a string. this is the inverse of stringToBigInteger()
	public static String bigIntegerToString(BigInteger bigInt)
	{
		String asString = bigInt.toString();
		
		//add leading zeros
		while((asString.length() % STRING_LENGTH) != 0 )
		{
			asString = "0" + asString;
		}
		
		String result =  "";
		
		//separate string into strings of length 3
		for(int i = 0; i < asString.length(); i+=STRING_LENGTH)
		{
			String tempString = "";
			
			for(int j = 0; j < STRING_LENGTH; j++ )
			{
				tempString += asString.charAt(i+j);
			}
			//convert string of numbers into corresponding ascii char
			result += (char)Byte.parseByte(tempString);
		}
		
		return result;
	}
	
	
}
