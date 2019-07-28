package com.pelatro.training.project;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class AlphabetFactory implements Runnable {
	//List<Character> l=new ArrayList<Character>();
	private List<Character> l;
	
	private int rate;
	
	public Boolean flag=false;
	public List<Character> getL() {
		return l;
	}
	public void setL(List<Character> l) {
		this.l = l;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}


	
	public AlphabetFactory(List<Character> l,int rate){
		this.l=l;
		this.rate=rate;
		
	}
	AlphabetFactory() {
	
	}

	@Override
	public void run() {
	
		pickAlphabet();
	}
	
	
	public void pickAlphabet() {
		
		int i;
		char ch;
		flag=false;
		if(l==null)
		{
			System.out.println("List is empty");
			return;
		}
		flag=true;
		for(i=0;;i++) {
			//System.out.println(i%l.size());
			
			ch=l.get((i%l.size()));
			
			
			try {
				//sleep(1);
				Socket soc;
				
				soc = new Socket("LOCALHOST",4444);
				
				PrintWriter pw=new PrintWriter(new OutputStreamWriter(soc.getOutputStream()),true);
				Thread.sleep(rate);
				pw.println(ch+"#AlphaFactory");
				soc.close();
			} catch (Exception e) {
				System.out.println("Socket connection not established properly");
			} 
		}
	}
	
}

