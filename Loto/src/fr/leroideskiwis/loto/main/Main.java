package fr.leroideskiwis.loto.main;

import java.util.Random;
import java.util.Scanner;

import fr.leroideskiwis.loto.Objects.Player;

public class Main {

    public static void main(String[] args) {
	
	Scanner scan = new Scanner(System.in);
	
	
	
	
	System.out.println("niveau de difficulté ?");
	
	String diffinit = scan.nextLine();

	
	
	
	Player p = new Player(diffinit);
	
	Random random = new Random();
	
	boolean Win1 = random.nextBoolean();
	boolean Win2 = random.nextBoolean();
	boolean Win3 = random.nextBoolean();
	boolean Win4 = random.nextBoolean();
	boolean Win5 = random.nextBoolean();
	boolean Win6 = random.nextBoolean();
	boolean Win7 = random.nextBoolean();
	boolean Win8 = random.nextBoolean();
	boolean Win9 = random.nextBoolean();
	boolean Win10 = random.nextBoolean();
	
	String Diff = p.getDifficulty();
	
	if(p.getDifficulty().equalsIgnoreCase("hard")){
	if(Win1 && Win2 && Win3 && Win4 && Win5 && Win6 && Win7 && Win8 && Win9 && Win10){
	    console("bravo ! tu as gagné !");
	    scan.close();
	     
	 } else {
	     
	     console("non désolé tu as perdu");
	     scan.close();
	     
	 }
	
	} else if(Diff.equalsIgnoreCase("easy")) {
	    if(Win1 && Win2 && Win3){
		console("bravo ! tu as gagné !");
		scan.close();
		     
		 } else {
		     
		     console("non désolé tu as perdu");
		     scan.close();
		     
		 }
	} else if(Diff.equalsIgnoreCase("moyen")){
	 if(Win1 && Win2 && Win3 && Win4 && Win5){
		    
	     console("bravo ! tu as gagné !");
	     scan.close();
	 } else {
	     
	     console("non désolé tu as perdu");
	     scan.close();
	     
	 }
	}
	

    }
    
    public static boolean InisialiseBoolean(Random random){
	
	boolean Win = random.nextBoolean();
	
	if(Win){
	    
	    return true;
	    
	} else {
	    return false;
	}
	
    }
    
    public static void console(String message){
	System.out.println(message);
    }

}
