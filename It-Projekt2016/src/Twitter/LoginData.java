package Twitter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;

public class LoginData implements Serializable {


	private static final long serialVersionUID = 1L;
	private String consumerKey;
	private String consumerSecret;
	private String accesToken;
	private String accesTokenSecret;
	private String name;
	
	public LoginData() {
		
		name = "test";
		consumerKey = null;
		consumerSecret = null;
		accesToken = null;
		accesTokenSecret = null;
		
	}
	
	@Override
	public String toString() {
		return "LoginData [consumerKey=" + consumerKey + ", consumerSecret="
				+ consumerSecret + ", accesToken=" + accesToken
				+ ", accesTokenSecret=" + accesTokenSecret + ", name=" + name
				+ "]";
	}

	public void saveLoginData() throws FileNotFoundException, IOException {
		
		String url = LoginData.class.getResource("").toString();
		url = url.substring(5, url.length());
		url = url.replace("Twitter/", "") + "LoginData/" + name +".bin";
		System.out.println(url);
		
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(url));
		objectOutputStream.writeObject(this);
		objectOutputStream.flush();
		objectOutputStream.close();
	}
	
	public LoginData loadLoginData(String url) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		ObjectInputStream objectInputStreamt = new ObjectInputStream(new FileInputStream(url));
		LoginData loginData = (LoginData) objectInputStreamt.readObject();
		objectInputStreamt.close();
		return loginData;
		
	}
	
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public void setAccesToken(String accesToken) {
		this.accesToken = accesToken;
	}

	public void setAccesTokenSecret(String accesTokenSecret) {
		this.accesTokenSecret = accesTokenSecret;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public String getAccesToken() {
		return accesToken;
	}

	public String getAccesTokenSecret() {
		return accesTokenSecret;
	}
	
	
}
