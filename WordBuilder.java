package com.pelatro.training.project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class WordBuilder extends Thread{
	//socket
	String word;
	
	
	
	WordBuilder(String word){
		this.word=word;
	}
	
	WordBuilder(){
		
	}
	@Override
	public void run() {
		//System.out.println("inside run of wordbuli");
		//String word=takeInput();
		buildWord();
		
		
	}
	
	
	
	
	
	String takeInput(){
		System.out.println("enter word");
		Scanner sc=new Scanner(System.in);
		String word;
		word=sc.next();
		return word;
	}
	
	void buildWord() {
		//retrieve field
		//String word="kavana";
		
		try {
		//for(;;) {	
		Socket soc;
		soc = new Socket("LOCALHOST",4444);
		//BufferedReader br=new BufferedReader(new InputStreamReader(soc.getInputStream()));
		PrintWriter pw=new PrintWriter(new OutputStreamWriter(soc.getOutputStream()),true);
		//ConcurrentHashMap<Character, Integer> ch;
		//System.out.println(word);
		pw.println(word+"#wordBuilder");
		//System.out.println(br.readLine());
		//```````````````````````````````````````````````
		//we have got hash map in string
		//check each alpha of word present
		//if not wait
		//again fetch repository
		//check if its present
		//if yes then decrease the count of letter by 1
		//`````````````````````````````````````````````````````````````
		//Thread.sleep(5);
		//pw.println(ch);
		soc.close();
		//}
		
		}catch(Exception e) {
		
		}//check
		}
		//if not found sleep then again retrieve
	/*	
	public static void main(String[] args) {
		WordBuilder wb=new WordBuilder();
		wb.buildWord();
	}*/
	
}
