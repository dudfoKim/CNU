import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Scanner;

import javax.crypto.Cipher;

public class ClientMain {
	public static void main(String[] args) throws Exception{
		Scanner scan = new Scanner(System.in);

		String server_addr;
		int server_port;
		String client_addr;
		int client_port;
		String gateway_addr;
		int gatewayInterface = 3000;

		server_addr = "192.168.0.6";	//server의 ip주소
		server_port = 3003;
		gateway_addr = "192.168.0.6";	//gateway의 ip주소

		/*connect*/		
		Socket socket = new Socket(gateway_addr,gatewayInterface);		
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());				
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

		client_addr = socket.getLocalAddress().toString();
		client_port = socket.getLocalPort();
		
		Key[] rsaKey = generateRSAKey();
		Key client_publicKey = rsaKey[0];	//client의 publicKey 생성
		Key client_privateKey = rsaKey[1];	//client의 privateKey 생성
		
		System.out.println("\nConnect message");
		Message send_message = new Message(server_addr,server_port, client_addr, client_port, "connect", null, client_publicKey);	
		oos.reset();
		oos.writeObject(send_message);

		System.out.print("Send Data (>\"exit\" => disconnect)");
		
		/*start*/
		System.out.print(">");
		String start_str = "start";
		
		send_message = new Message(server_addr,server_port,client_addr, client_port,"data",start_str.getBytes(),client_publicKey);
		oos.reset();
		oos.writeObject(send_message);	//start메세지 전송
		System.out.println("start");

		Key server_publlicKey = null;
		
		Message recv_message1 = (Message)ois.readObject();	//server로부터 받은 메세지
        	server_publlicKey = recv_message1.publicKey;

		/*send data*/
		while(true){
				
        	
			System.out.print(">");
			String str = scan.nextLine();	
			//client가 메세지 입력
			if(str.equals("exit")) break;
			//exit을 입력하면 disconnect
			
			byte[] cipherText2 = testRSA_encrypt(server_publlicKey,str);
			//client가 보낼 메세지를 server의 publicKey로 암호화
			
			send_message = new Message(server_addr,server_port,client_addr, client_port,"data",cipherText2,client_publicKey);
			oos.reset();
			oos.writeObject(send_message);	//암호문을 server에 전달

			Message recv_message = (Message)ois.readObject();

			if(recv_message.msg != null){
				System.out.println(">"+new String(testRSA_decrypt(client_privateKey, recv_message.msg)));
				//server로부터 받은 메세지를 privateKey로 복호화해서 출력
			}
			
		}

		/*disconnect*/
		send_message = new Message(server_addr,server_port,client_addr, client_port,"disconnect",null, null);
		oos.reset();
		oos.writeObject(send_message);

		for(int i=0;i<5;i++){
			System.out.println("wait "+(5-i));
			Thread.sleep(1000);
		}
		ois.close();
		socket.close();
		System.out.println("disconnect");
	}

	public static byte[] testRSA_encrypt(Key key, String text) throws Exception{
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cipherText = cipher.doFinal(text.getBytes()); // �븫�샇�솕�맂 �뜲�씠�꽣(byte 諛곗뿴)
		return cipherText;
	}

	public static byte[] testRSA_decrypt(Key key, byte[] cipherText) throws Exception{
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] plainText = cipher.doFinal(cipherText);
        return plainText;
	}
	
	public static Key[] generateRSAKey() throws Exception{
		Key[] key = new Key[2];
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);

		KeyPair keyPair = keyPairGenerator.genKeyPair();
		Key publicKey = keyPair.getPublic(); // 怨듦컻�궎
		Key privateKey = keyPair.getPrivate(); // 媛쒖씤�궎

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
		RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);

		key[0] = publicKey;
		key[1] = privateKey;

		return key;
	}

	// byte[] to hex
	public static String byteArrayToHex(byte[] ba) {
		if (ba == null || ba.length == 0) {
			return null;
		}

		StringBuffer sb = new StringBuffer(ba.length * 2);
		String hexNumber;
		for (int x = 0; x < ba.length; x++) {
			hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}
		return sb.toString();
	} 
}
