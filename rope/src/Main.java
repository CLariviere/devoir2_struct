public class Main {

	public static void main(String[] args) {
		batchDeTests(); //Mettre en commentaire si vous ne voulez pas faire ces tests
	}

	public static void batchDeTests(){
		//Idéalement, ces tests ne devraient afficher que des true, et jamais de false ou
		//de message d'erreur!
		//Ces tests supposent que les méthodes toString et length ont bien été implémentées
		//Ces tests ne couvrent pas tous les cas de figure possibles. Il pourrait être
		//judicieux d'en faire plus!

		// try{
		// 	System.out.println("Test du constructeur vide");
		// 	Rope corde = new Rope();
		// 	testEqual(corde, "");
		// }catch (Exception e){
		// 	System.out.println(e.getMessage());
		// }

		// try{
		// 	System.out.println("\nTest de insert (1/5)");
		// 	Rope corde = new Rope();
		// 	corde.insert(0,"Bonjour");
		// 	testEqual(corde, "Bonjour");
		// }catch (Exception e){
		// 	System.out.println(e.getMessage());
		// }

		// try{
		// 	System.out.println("\nTest de insert (2/5)");
		// 	Rope corde = new Rope();
		// 	String s = "Bonjour";
		// 	for(int i = 0; i < s.length(); ++i){
		// 		corde.insert(i, s.charAt(i)+"");
		// 	}
		// 	testEqual(corde, s);
		// }catch (Exception e){
		// 	System.out.println(e.getMessage());
		// }

		// try{
		// 	System.out.println("\nTest de insert (3/5)");
		// 	Rope corde = new Rope();
		// 	String s = "Bonjour";
		// 	for(int i = s.length() - 1; i >= 0; --i){
		// 		corde.insert(0, s.charAt(i)+"");
		// 	}
		// 	testEqual(corde, s);
		// }catch (Exception e){
		// 	System.out.println(e.getMessage());
		// }

		// try{
		// 	System.out.println("\nTest de insert (4/5)");
		// 	Rope corde = new Rope();
		// 	corde.insert(0, "Dragga"); //Dragga
		// 	corde.insert(6, "lama"); //Draggalama
		// 	corde.insert(10, "ha"); //Draggalamaha
		// 	corde.insert(6, "mma"); //Draggammalamaha
		// 	corde.insert(13, "lat");//Draggammalamalatha
		// 	testEqual(corde, "Draggammalamalatha");
		// } catch (Exception e){
		// 	System.out.println(e.getMessage());
		// }

		// try{
		// 	System.out.println("\nTest de insert (5/5)");
		// 	Rope corde = new Rope();
		// 	corde.insert(0, "Bonjour");
		// 	corde.insert(100, "Bonjour");
		// 	System.out.println("false");
		// }catch (IndexOutOfBoundsException e){
		// 	System.out.println("true");
		// }catch (Exception e){
		// 	System.out.println("false");
		// }

		// try{
		// 	System.out.println("\nTest de charAt (1/2)");
		// 	Rope corde = new Rope();
		// 	corde.insert(0, "Bonjour");
		// 	testEqualCharAt(corde, "Bonjour");
		// }catch (Exception e){
		// 	System.out.println(e.getMessage());
		// }

		// try{
		// 	System.out.println("\nTest de charAt (2/2)");
		// 	Rope corde = new Rope();
		// 	corde.insert(0, "Bonjour");
		// 	corde.insert(0, "Salut");
		// 	corde.insert(0, "asfas");
		// 	corde.insert(0, "vfVFt");
		// 	corde.insert(0, "EFVW");
		// 	corde.insert(0, "vfbERe");
		// 	corde.insert(0, "bEFBGRTHS");
		// 	corde.charAt(9);
		// 	System.out.println("false");
		// }catch (IndexOutOfBoundsException e){
		// 	System.out.println("true");
		// }catch (Exception e){
		// 	System.out.println("false");
		// }

		try{
			System.out.println("\nTest de split (1/5)");
			Rope corde1 = new Rope();
			corde1.insert(0, "Salut");
			corde1.insert(5, "Bonjour");
			Rope corde2 = corde1.split(5);
			testEqual(corde1, "Salut");
			testEqual(corde2, "Bonjour");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("\nTest de split (2/5)");
			Rope corde1 = new Rope();
			corde1.insert(0, "Dragga"); //Dragga
			corde1.insert(6, "lama"); //Draggalama
			corde1.insert(10, "ha"); //Draggalamaha
			corde1.insert(6, "mma"); //Draggammalamaha
			corde1.insert(13, "lat");//Draggammalamalatha
			Rope corde2 = corde1.split(8);
			testEqual(corde1, "Draggamm");
			testEqual(corde2, "alamalatha");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("\nTest de split (3/5)");
			Rope corde1 = new Rope();
			corde1.insert(0, "Dragga"); //Dragga
			corde1.insert(6, "lama"); //Draggalama
			corde1.insert(10, "ha"); //Draggalamaha
			corde1.insert(6, "mma"); //Draggammalamaha
			corde1.insert(13, "lat");//Draggammalamalatha
			Rope corde2 = corde1.split(0);
			testEqual(corde1, "");
			testEqual(corde2, "Draggammalamalatha");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("\nTest de split (4/5)");
			Rope corde1 = new Rope();
			corde1.insert(0, "Dragga"); //Dragga
			corde1.insert(6, "lama"); //Draggalama
			corde1.insert(10, "ha"); //Draggalamaha
			corde1.insert(6, "mma"); //Draggammalamaha
			corde1.insert(13, "lat");//Draggammalamalatha
			Rope corde2 = corde1.split(18);
			testEqual(corde1, "Draggammalamalatha");
			testEqual(corde2, "");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("\nTest de split (5/5)");
			Rope corde1 = new Rope();
			corde1.insert(0, "Dragga"); //Dragga
			corde1.insert(6, "lama"); //Draggalama
			corde1.insert(10, "ha"); //Draggalamaha
			corde1.insert(6, "mma"); //Draggammalamaha
			corde1.insert(13, "lat");//Draggammalamalatha
			Rope corde2 = corde1.split(500);
			System.out.println("false");
		}catch (IndexOutOfBoundsException e){
			System.out.println("true");
		}catch (Exception e){
			System.out.println("false");
		}

		try{
			System.out.println("\nTest de concat (1/3)");
			Rope corde1 = new Rope();
			corde1.insert(0,"Salut");
			Rope corde2 = new Rope();
			corde2.insert(0,"Bonjour");
			corde1.concat(corde2);
			testEqual(corde1, "SalutBonjour");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("\nTest de concat (2/3)");
			Rope corde1 = new Rope();
			String s = "";
			for(int i = 0; i < 10; i++){
				Rope corde2 = new Rope();
				corde2.insert(0, "Hello" + i);
				s += "Hello" + i;
				corde1.concat(corde2);
			}
			testEqual(corde1, s);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("\nTest de concat (3/3)");
			Rope corde1 = new Rope();
			for(int i = 0; i < 10; i++){
				Rope corde2 = new Rope();
				corde1.concat(corde2);
			}
			testEqual(corde1, "");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("\nTest de delete (1/6)");
			Rope corde = new Rope();
			corde.insert(0,"Bonjour");
			corde.delete(0, 7);
			testEqual(corde, "");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("\nTest de delete (2/6)");
			Rope corde = new Rope();
			corde.insert(0,"Bonjour");
			corde.delete(3, 3);
			testEqual(corde, "Bonjour");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("\nTest de delete (3/6)");
			Rope corde = new Rope();
			String s = "Bonjour";
			for(int i = 0; i < s.length(); ++i){
				corde.insert(i, s.charAt(i)+"");
			}
			corde.delete(1, 6);
			testEqual(corde, "Br");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("\nTest de delete (4/6)");
			Rope corde = new Rope();
			corde.insert(0, "Dragga"); //Dragga
			corde.insert(6, "lama"); //Draggalama
			corde.insert(10, "ha"); //Draggalamaha
			corde.insert(6, "mma"); //Draggammalamaha
			corde.insert(13, "lat");//Draggammalamalatha
			corde.delete(0,1);//raggammalamalatha
			corde.delete(2,4);//raammalamalatha
			corde.delete(3,8);//raamalatha
			corde.delete(9,10);//raamalath
			corde.delete(3,6);//raaath
			testEqual(corde, "raaath");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("\nTest de delete (5/6)");
			Rope corde = new Rope();
			corde.insert(0, "Bonjour");
			corde.delete(5, 3);
			System.out.println("false");
		}catch (IndexOutOfBoundsException e){
			System.out.println("true");
		}catch (Exception e){
			System.out.println("false");
		}

		try{
			System.out.println("\nTest de delete (6/6)");
			Rope corde = new Rope();
			corde.insert(0, "Bonjour");
			corde.delete(5, 100);
			System.out.println("false");
		}catch (IndexOutOfBoundsException e){
			System.out.println("true");
		}catch (Exception e){
			System.out.println("false");
		}

		try{
			System.out.println("\nTest de rebalance");
			Rope corde = new Rope();
			String s = "Draggammalamalatha birbouchel Ostrumaplivi tigaudô umô transi Li";
			for(int i = 0; i < s.length(); ++i){
				corde.insert(i, s.charAt(i)+"");
			}
			corde.rebalance();
			testEqual(corde, s);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("\nTest de clear");
			Rope corde = new Rope();
			corde.insert(0, "Dragga"); //Dragga
			corde.insert(6, "lama"); //Draggalama
			corde.insert(10, "ha"); //Draggalamaha
			corde.insert(6, "mma"); //Draggammalamaha
			corde.insert(13, "lat");//Draggammalamalatha
			corde.clear();
			testEqual(corde, "");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("\nTest de substring (1/6)");
			Rope corde = new Rope();
			corde.insert(0,"Bonjour");
			System.out.println(corde.substring(0, 7).equals("Bonjour"));
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("\nTest de substring (2/6)");
			Rope corde = new Rope();
			corde.insert(0,"Bonjour");
			System.out.println(corde.substring(3, 3).equals(""));
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("\nTest de substring (3/6)");
			Rope corde = new Rope();
			String s = "Bonjour";
			for(int i = 0; i < s.length(); ++i){
				corde.insert(i, s.charAt(i)+"");
			}
			System.out.println(corde.substring(1, 6).equals("onjou"));
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("\nTest de substring (4/6)");
			Rope corde = new Rope();
			corde.insert(0, "Dragga"); //Dragga
			corde.insert(6, "lama"); //Draggalama
			corde.insert(10, "ha"); //Draggalamaha
			corde.insert(6, "mma"); //Draggammalamaha
			corde.insert(13, "lat");//Draggammalamalatha
			System.out.println(corde.substring(7, 13).equals("malama"));
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		try{
			System.out.println("\nTest de substring (5/6)");
			Rope corde = new Rope();
			corde.insert(0, "Bonjour");
			corde.substring(5, 3);
			System.out.println("false");
		}catch (IndexOutOfBoundsException e){
			System.out.println("true");
		}catch (Exception e){
			System.out.println("false");
		}

		try{
			System.out.println("\nTest de substring (6/6)");
			Rope corde = new Rope();
			corde.insert(0, "Bonjour");
			corde.substring(5, 100);
			System.out.println("false");
		}catch (IndexOutOfBoundsException e){
			System.out.println("true");
		}catch (Exception e){
			System.out.println("false");
		}

	}

	public static void testEqual(Rope corde, String s){
		System.out.println(corde.length() == s.length());
		System.out.println(corde.toString().equals(s));
	}

	public static void testEqualCharAt(Rope corde, String s){
		boolean correct = corde.length() == s.length();
		System.out.println(correct);
		if(correct){
			for(int i = 0; i < s.length(); ++i){
				if(corde.charAt(i) != s.charAt(i)) {
					correct = false;
					break;
				}
			}
		}
		System.out.println(correct);

	}


}
