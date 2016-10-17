import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Scanner;

import javax.crypto.Cipher;

public class ServerMain {
	public static void main(String[] args) throws Exception{		
		Scanner scan = new Scanner(System.in);
		int port=3003;
		
		String server_addr;
		int server_port;
		String client_addr;
		int client_port;
		String gateway_addr;
		int gatewayInterface = 3000;

		client_addr = "192.168.0.6";	//server의 ip주소
		client_port = 3003;
		gateway_addr = "192.168.0.6";	//gateway의 ip주소
		
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        
        server_addr = serverSocket.getLocalSocketAddress().toString();
        server_port = serverSocket.getLocalPort();
        
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		
		Key[] rsaKey = generateRSAKey();	
		Key server_publicKey = rsaKey[0];	//server의 publicKey 생성
		Key server_privateKey = rsaKey[1];	//server의 privateKey 생성
		Key client_publicKey = null;
		
		Message send_message2 = new Message(client_addr, client_port, server_addr, server_port, "data", null, server_publicKey);
        
    	oos.reset();
    	oos.writeObject(send_message2);	//server의 publicKey를 client에게 전달
    	
    	boolean start = true;
    	
        while(true){
        	
        	Message recv_message = (Message)ois.readObject();	//client로부터 받은 메세지
        	client_publicKey = recv_message.publicKey;
        	
        	if(start){		//채팅 시작
        		System.out.println("start");
        		start = false;
        	}
        	else{
        		System.out.println("client>"+new String(testRSA_decrypt(server_privateKey, recv_message.msg)));
    			//client가 보낸 메세지를 privateKey로 복호화해서 출력
            	
            	System.out.print(">");
    			String str = scan.nextLine();
    			//server가 채팅창에 메시지 입력
    			
    			if(str.equals("exit")) break;
    			//server가 exit을 보내면 disconnect
    			
    			byte[] cipherText2 = testRSA_encrypt(client_publicKey,str);
    			//server가 보낼 메세지를 client의 publicKey로 암호화
    			
            	Message send_message = new Message(recv_message.src_addr, recv_message.src_port, socket.getLocalAddress().toString(),port,"data",cipherText2, server_publicKey);
                
            	oos.reset();
            	oos.writeObject(send_message);	//암호문을 client에게 전송
        	}
        	
        }
        
        /*disconnect*/
        Message send_message = new Message(server_addr,server_port,client_addr, client_port,"disconnect",null, null);
		oos.reset();
		oos.writeObject(send_message);
		
		for(int i=0;i<5;i++){
			System.out.println("wait "+(5-i));
			Thread.sleep(1000);
		}
		ois.close();
		serverSocket.close();
        System.out.println("disconnect");
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
