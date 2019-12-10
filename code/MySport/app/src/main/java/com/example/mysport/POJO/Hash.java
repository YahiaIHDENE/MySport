package mySport;
import java.math.BigInteger;
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;

public class Hash {
	private String pwd; 
	
	public Hash(String pwd){
		this.pwd = pwd;}
	
	public String hashString () throws NoSuchAlgorithmException {
	MessageDigest digest = MessageDigest.getInstance("SHA-256");
	   byte[] byt = digest.digest(pwd.getBytes());
	   BigInteger intN = new BigInteger(1, byt);
	   String pwdhash = intN.toString(16);
	   while (pwdhash.length() < 32) {
		   pwdhash = "0" + pwdhash;
	   }

	   return pwdhash;
	}
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String a = "2";
		Hash hash= new Hash(a);
		String password_hash= "";
		try{
			password_hash = hash.hashString();
		}catch ( Exception e ) {};
		
		System.out.println( password_hash);
		//da3811154d59c4267077ddd8bb768fa9b06399c486e1fc00485116b57c9872f5
		//ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb

	}

	
	
}