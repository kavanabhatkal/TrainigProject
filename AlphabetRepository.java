package com.pelatro.training.project;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class AlphabetRepository extends Thread{
	//server code
	ConcurrentHashMap<Character,Integer> hm;  
	//static Vector<ClientHandler>  ar= new Vector<>(); 
	//static int i = 0; 

	ServerSocket ss;
	Socket s;
	
	public AlphabetRepository(ConcurrentHashMap hm) {
		// TODO Auto-generated constructor stub
		this.hm=hm;
	}
	
	
	
	//database change synchronized
	
	@Override
	public void run() {
			
		
		
		
		
			try {
	
			ss = new ServerSocket(4444);
			//Socket s; 
			int i = 0; 
			//s = ss.accept();
			
			
			while (true) 
			{ 
				// Accept the incoming request 
				s = ss.accept(); 

				//System.out.println("New client request received : " + s); 
				
				// obtain input and output streams 
				DataInputStream dis = new DataInputStream(s.getInputStream()); 
				//DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
				String received = dis.readLine();
				s.close();
				
				
				
				//System.out.println("Creating a new handler for this client...")

				
				Thread t = new ClientHandler(received,hm); 
				  
                // Invoking the start() method 
                t.start(); 
				

			} 
		
		
		
			}catch(Exception e) {
				
			}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			//passHashMap();		
	
			/*for(;;) {
				storeInRepository();
				passHashMap();				
			}*/
		
			
		
		
		//alphaFactoryAdd();
		//store into variables
		//}
		
	}
	/*
	void storeInRepository() {
		ServerSocket ss;
		Socket chatSoc;
		
		
		try {
			ss = new ServerSocket(4444);
			chatSoc = ss.accept();
			BufferedReader br=new BufferedReader(new InputStreamReader(chatSoc.getInputStream()));
			char alphaFromFactory=br.readLine().charAt(0);
			int previousCount;
			previousCount=hm.get(alphaFromFactory);
			System.out.println(alphaFromFactory);
			hm.replace(alphaFromFactory,previousCount , (previousCount+1));
			System.out.println(previousCount);
			//getvalue of that character
			ss.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
//*/
//	void alphaFactoryAdd() {
//		//session.update()
//	}
//	public static void main(String args[]) {
//		ConcurrentHashMap<Character,Integer> hm=new ConcurrentHashMap<Character, Integer>(); 
//		for(char ch='a';ch<='z';ch++) {
//			hm.put(ch, 0);
//		}
//		AlphabetRepository a=new AlphabetRepository(hm);
//		//a.start();
//		//a.initTable();
//		a.run();
//	}
//	
//	
//	
//	
//	
//	
//	void passHashMap() {
//		ServerSocket serverSocket;
//		Socket socket;
//		
//		
//		
//		try {
//			serverSocket = new ServerSocket(4444);
//			socket = serverSocket.accept();
//			
//			PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
//			//System.out.println();
//			pw.println(hm);
//
//			socket.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//		
//		
//	}
//	void wordBuilderDelete() {}
	
}
/*
class Repository{
	ConcurrentHashMap<Character,Integer> hm;  
	
	public Repository(ConcurrentHashMap hm) {
		// TODO Auto-generated constructor stub
		this.hm=hm;
	}
}
*/