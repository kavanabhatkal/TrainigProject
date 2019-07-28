package com.pelatro.training.project;
//import
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class ScrabbleApp {
	private AlphabetFactory[] aFactory=null;
	private AlphabetRepository alphabetRepository;
	
	
	
	public static void main(String[] args) {
	
		
		Scanner sc=new Scanner(System.in);
		ScrabbleApp sa=new ScrabbleApp();
		final String CONFIGURATIONFILE= "/home/kavanabhatkal/eclipse-workspace/ScrabbleProject/src/com/pelatro/training/project/ScrabbleConfig.txt";
		
		String word;
		String choice;
		
		System.out.println("WELCOME TO SCRABBLE APP");
		
		System.out.println("-------------------------");
		
		
		
		
		
		
		sa.initAlphabetRepository();
		
		
		
		sa.alphabetRepository.start();
		
		
		sa.initAlphabetFactory(CONFIGURATIONFILE);
		
		
		
		for(int i=0;i<sa.aFactory.length;i++) {
			Thread t=new Thread(sa.aFactory[i],"af");
			t.setDaemon(true);
			t.start();
			
			
			
		}

		
		while(true) {
			
			
			word=sa.getInputFromUser();
			
			
			
			if(word.matches("^[a-zA-Z]*$")==false) {
				System.out.println("Enter only alphabets");
				continue;
			}
			
			
			
			
			WordBuilder wordBuilder1 =new WordBuilder(word);
			Thread t4=new Thread(wordBuilder1,"wb");
			
			
			wordBuilder1.start();
			
			System.out.println("Do you want to continue[y/n]");
			choice=sc.next();
			if(choice.equalsIgnoreCase("n")) {
				try {
					Thread.sleep(10000);
					System.out.println("----------------------");
					System.out.println("THANK YOU");
					System.exit(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//break;
		}
		
		
		}
	}
		
		
		
	
	
	
	public String getInputFromUser() {
		Scanner sc=new Scanner(System.in);
		String word;
		System.out.println("Enter word");
		word=sc.next();
	
			
		return word;
		
	}
	
	public void initAlphabetFactory(String file) {
		
		if(file==null) {
			
			System.out.println("file invalid");
			
			System.exit(0);
		}
		 
		//System.out.println("after");
		
		BufferedReader br=null;
		String firstLine=new String();
		String[] line;
		List<Character> l;
		String[] part=new String[2];
		int i,count=0,j=0;
		int noOfFactory,lengthOfCharacter,lengthOfString;
		String[] firstParameter=new String[2];
		String[] secondParameter=new String[2];
		String[] element;
		
		
		try {
			
			br=new BufferedReader(new FileReader(file));
			
			try {
				firstLine=br.readLine();
				
				
				if(firstLine==null) {
					System.out.println("File is empty");
					System.exit(0);
					
				}
				
						
						
				part=firstLine.split(":");
				
				noOfFactory=Integer.parseInt(part[1]);
				
				aFactory=new AlphabetFactory[noOfFactory];
				
				line=new String[(noOfFactory*2)+1];
				
				while((line[count]=br.readLine())!=null) {	
					
					count++;
				}
				
				br.close();	
				for(i=0;i<noOfFactory;i++) {
					
					firstParameter=line[j++].split(":");
					//System.out.println(firstParameter[1]);
					l=new ArrayList<Character>();
					//9-4
					//11-5
					lengthOfString=firstParameter[1].length();
					lengthOfCharacter=lengthOfString-(lengthOfString/2);
					
					element=new String[lengthOfCharacter];
					element=firstParameter[1].split(" ");
					
					
					for(int k=0;k<lengthOfCharacter;k++)
					 {
						l.add(element[k].charAt(0));
					 }
					
					
					secondParameter=line[j++].split(":");
					
					
					
					aFactory[i]=new AlphabetFactory(l ,Integer.parseInt(secondParameter[1]) );
					
					
					l=null;
					element=null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//this.flag=true;
				e.printStackTrace();
			}
		
			
			
			
		} catch (Exception e) {
			
			System.out.println("Configuration File not found");
			System.exit(0);
		}
		
		
	}
	
	

	public AlphabetFactory[] getaFactory() {
		
		return aFactory;
	}



	public void setaFactory(AlphabetFactory[] aFactory) {
		this.aFactory = aFactory;
	}



	public AlphabetRepository getAlphabetRepository() {
		return alphabetRepository;
	}



	public void setAlphabetRepository(AlphabetRepository alphabetRepository) {
		this.alphabetRepository = alphabetRepository;
	}



	public void initAlphabetRepository() {
		//System.out.println(aFactory[0].rate);
		ConcurrentHashMap<Character,Integer> hm=new ConcurrentHashMap<Character, Integer>();  
		for(char ch='a';ch<='z';ch++) {
			hm.put(ch, 0);
		}
		alphabetRepository=new AlphabetRepository(hm);
		
		
	}

}
