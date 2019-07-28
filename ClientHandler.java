package com.pelatro.training.project;

import java.io.BufferedReader;

import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;
import com.pelatro.training.project.dto.Log;

public class ClientHandler extends Thread
{
	
	private String received;
	
	private ConcurrentHashMap<Character,Integer> hm;
	public String getReceived() {
		return received;
	}

	public void setReceived(String received) {
		this.received = received;
	}

	public ConcurrentHashMap<Character, Integer> getHm() {
		return hm;
	}

	public void setHm(ConcurrentHashMap<Character, Integer> hm) {
		this.hm = hm;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	private Log log=new Log();

	
	SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
	
	public ClientHandler(String received, 
			ConcurrentHashMap<Character, Integer> hm) { 
		this.hm = hm; 
		this.received = received; 
		//this.s = s; 
		//this.isloggedin=true; 
	} 

	@Override
	public void run() {
		// TODO Auto-generated method stub

		
		
		while (true) 
		{ 
			try
			{ 
				
			
				// break the string into message and recipient part 
				StringTokenizer st = new StringTokenizer(received, "#"); 
				String Msg = st.nextToken(); 
				String recipient = st.nextToken(); 

				
				
				if(recipient.equals("wordBuilder")) {
					
					buildWord(Msg);
					
				}
				else
				{
					
					storeInRepository(Msg);
					
				}
				
			} catch (Exception e) { 
				
				e.printStackTrace(); 
			} 
			
		} 
			
	}
	
	
	
	
	public void storeInRepository(String msg) {
		
		
		
		try {
			
			//System.out.println("inside store"+msg);
			char alphaFromFactory=msg.charAt(0);
			
			int previousCount;
			
			previousCount=hm.get(alphaFromFactory);
			//System.out.println(alphaFromFactory);
			//Thread.sleep(7000);
			hm.replace(alphaFromFactory,previousCount , (previousCount+1));
			//System.out.println(previousCount);
			//getvalue of that character
			//ss.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	synchronized public void buildWord(String msg) {
		
		int count;
		
		int i;
		
		for(i=0;i<msg.length();i++) {
			
			count=hm.get(msg.charAt(i));
			if(count>0) {
				hm.replace(msg.charAt(i), count, count-1);
			}
			else {
				
				try {
					notifyAll();
					
					wait(5000);
					
					count=hm.get(msg.charAt(i));
					if(count>0)
						hm.replace(msg.charAt(i), count, count-1);
					else {
						for(int j=0;j<i;j++)
						{
							count=hm.get(msg.charAt(j));
							hm.replace(msg.charAt(j), count, count+1);
						}
						
						log.setWord(msg);
						Date date= new Date();
						
						long time = date.getTime();
						
						Timestamp ts = new Timestamp(time);
						
						log.setTime(ts);
						
						log.setStatus("failure");
						System.out.println("---word "+msg+" cannot be built----");
						
						// SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
						Session session=sessionFactory.openSession();
						session.beginTransaction();
						session.save(log);
						
						session.getTransaction().commit();
						session.close();
						
						
						//session.save(e1);
						
						
						
						
						//return false;
						//return;
						//System.exit(0);
						currentThread().stop();
					}
						
					
					
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
					
			}	
		}
		
		if(i==msg.length()) {
			
			log.setWord(msg);
			Date date= new Date();
			
			long time = date.getTime();
			
			Timestamp ts = new Timestamp(time);
			
			log.setTime(ts);
			
			log.setStatus("success");
			
			
		
			System.out.println("word "+msg+" built");
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(log);
			
			session.getTransaction().commit();
			session.close();
			//return true;
			//return;
			currentThread().stop();
			//System.exit(0);
		}
		
		
		
		
		
		
	}
	

}







