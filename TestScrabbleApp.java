package test.com.pelatro.training.project;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNoException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import com.pelatro.training.project.AlphabetFactory;
import com.pelatro.training.project.AlphabetRepository;
import com.pelatro.training.project.ClientHandler;
import com.pelatro.training.project.ScrabbleApp;
//import 
public class TestScrabbleApp {
	ScrabbleApp s;
	
	@Before
	public void setUp() {
		s=new ScrabbleApp();
	
	}
	
	
	
//	@Test
//	public void nullConfigurationFile() {
//	
//		
//	
//			
//			s.initAlphabetFactory(null);
//			Assert.assertNull(s.getaFactory());
//	
//
//	}
	
	
	
	
	
	
	@Test
	public void checkSimpleUserInput() {
		//File file = new File("/home/kavanabhatkal/eclipse-workspace/ScrabbleProject/src/com/pelatro/training/project/ScrabbleConfig.txt");
		String word;
		Boolean flag=false;
		word=s.getInputFromUser();
		//System.out.println(word.matches("^[a-zA-Z]*$"));
		flag=word.matches("^[a-zA-Z]*$");
		Assert.assertEquals(flag,true);
	}
	
	@Test
	public void checkUserInputContainCharacterOtherThanAlphabet() {
		//File file = new File("/home/kavanabhatkal/eclipse-workspace/ScrabbleProject/src/com/pelatro/training/project/ScrabbleConfig.txt");
		String word;
		Boolean flag=false;
		word=s.getInputFromUser();
		//System.out.println(word.matches("^[a-zA-Z]*$"));
		flag=word.matches("^[a-zA-Z]*$");
		Assert.assertEquals(flag,false);
	}
	
//	
//	@Test
//	public void checkUserInputNull() {
//		//File file = new File("/home/kavanabhatkal/eclipse-workspace/ScrabbleProject/src/com/pelatro/training/project/ScrabbleConfig.txt");
//		String word;
//		word=s.getInputFromUser();
//		
//		Assert.assertEquals(word,null);
//	}


	
	@Test
	public void checkAlphabetRepository() {
		String fileName="/home/kavanabhatkal/eclipse-workspace/ScrabbleProject/src/com/pelatro/training/project/ScrabbleConfig.txt";
		
		s.initAlphabetRepository();
		
		Assert.assertNotEquals(s.getAlphabetRepository(), null);
	}
	
	@Test
	public void validConfigurationFile() {
		
		String fileName="/home/kavanabhatkal/eclipse-workspace/ScrabbleProject/src/com/pelatro/training/project/ScrabbleConfig.txt";
		
		s.initAlphabetFactory(fileName);
		
		Assert.assertNotEquals(s.getaFactory(),null);
	}

	
	@Test
	public void checkPickAlphabetMethod() {
		AlphabetFactory alphabetFactory=new AlphabetFactory(null,1);
		alphabetFactory.pickAlphabet();
		Assert.assertFalse(alphabetFactory.flag);
	}
	
	@Test
	public void checkToStoreInRepositoryMethod() {
		ConcurrentHashMap<Character, Integer> hm=new ConcurrentHashMap<Character, Integer>();
		String msg="h";
		for(char ch='a';ch<='z';ch++) {
			hm.put(ch, 0);
		}
		ClientHandler ch=new ClientHandler(msg, hm);
		ch.storeInRepository(msg);
		assertEquals(hm.get('h'), 1);
	
	}
	@Test
	public void checkBuildWordMethod() {
		ConcurrentHashMap<Character, Integer> hm=new ConcurrentHashMap<Character, Integer>();
		String msg="hello";
		for(char ch='a';ch<='z';ch++) {
			hm.put(ch, 0);
		}
		ClientHandler ch=new ClientHandler(msg, hm);
		ch.buildWord(msg);
		System.out.println("kkk");
		assertEquals(ch.getLog().getStatus(), "failure");
	
	}
//	@Test
//	public void checkCorrectPickAlphabetMethod() {
//		List<Character> l= new ArrayList<Character>();
//		l.add('c');
//		AlphabetFactory alphabetFactory=new AlphabetFactory(l,1);
//		alphabetFactory.pickAlphabet();
//		Assert.assertTrue(alphabetFactory.flag);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/*
	@Test
	public void notFoundConfigurationFile() {
		
		String fileName="/hom/kavanabhatkal/a.txt";
		Boolean flag=false;
		try {
		
		s.initAlphabetFactory(fileName);
	
		}catch(Exception e) {
			flag=true;
		}
		System.out.println("kkk");
		//File file = new File("/home/kavanabhatkal/a.txt");
		//assertTrue(file.exists());
		Assert.assertEquals(flag,true);
	}
	
	
*/
	
	
	




}
