import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("initialisation");
		Thread.sleep(750);
		
		String rootid = "root";
		String rootmdp = "antony2003kiwi";
		Thread.sleep(750);
		
		String kiwiid = "LeRoiDesKiwis";
		String kiwimdp = "kiwi123456";
		Thread.sleep(750);
		
		Scanner scan = new Scanner(System.in);
		Thread.sleep(750);
		Random random = new Random();
		System.out.println("initialisation terminée !");
		Thread.sleep(500);
		System.out.println("identifiant :");
		String idconfirm = scan.nextLine();
		
		if(idconfirm.equals(rootid)){
			System.out.println("mot de passe :");
			String mdpconfirmroot = scan.nextLine();
			if(mdpconfirmroot.equals(rootmdp)){
				System.out.println("vous êtes maintenant connecté !");
				Thread.sleep(750);
				while(true){
					System.out.println("commande :");
					String commande = scan.nextLine();
					
					if(commande.equals("deconnect")){
						System.out.println("commande en developpement");
					} else {
						System.out.println("commande inconnue");
					}
					
				}
				
				
			} else {
				System.out.println("mot de passe incorrect !");
				System.exit(0);
			}
			
		} else if(idconfirm.equalsIgnoreCase(kiwiid)) {
			System.out.println("mot de passe :");
			String mdpconfirmkiwi = scan.nextLine();
			
			if(mdpconfirmkiwi.equals(kiwimdp)){
				System.out.println("vous êtes maintenant connecté");
				while(true){
					System.out.println("commande :");
					String commande = scan.nextLine();
					
					int ne;
					if(commande.equals("help")){
						System.out.println("liste des commandes :");
						System.out.println("help : pour afficher ce menu");
						System.out.println("devine : petit jeux sympa ;)");
						System.out.println("exit : pour quitter");
						
					} else if (commande.equals("exit")){
						System.out.println("voulez vous vraiment quitter ?");
						String confirmation = scan.nextLine();
						if(confirmation.equals("oui")){
							System.out.println("arrêt...");
							Thread.sleep(750);
							System.exit(0);
						}
					} else if (commande.equals("devine")){
						System.out.println("veuillez entrer un nombre entre 1 et 100");
						 ne = scan.nextInt();
						 int nd = random.nextInt(100) + 1;
						 
						 
						 while(nd != ne){
							 if(nd > ne) {
								 System.out.println("le nombre est trop petit\n");
							 } else if (nd < ne){
								 System.out.println(""
								 		+ "le nombre est trop grand");
							 }
							 System.out.println("veuillez entrer un nombre");
							 ne = scan.nextInt();
							 
						 }
						 System.out.println("Bravo ! tu as gagné !");
						 Thread.sleep(1000);
						 
					} else if (commande.startsWith("say")){
						//String say = scan.nextLine();
						System.out.println(commande.substring(4));
					} else {
						System.out.println("commande inconnue");
					}
			}
			
			}
		
			

		} else {
			System.out.println("identifiant inconnue !");
			System.exit(0);
		}
	}
}

			
			
		//} else {
			//System.out.println("mot de passe incorrect");
			//System.out.println("session terminée");
			//System.exit(0);
		//}
		

	

