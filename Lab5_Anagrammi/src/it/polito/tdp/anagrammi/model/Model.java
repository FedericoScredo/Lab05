package it.polito.tdp.anagrammi.model;

import java.util.HashSet;
import java.util.Set;

import it.polito.tdp.anagrammi.DAO.DizionarioDAO;

public class Model {
	
	private int lettere;
	private int iterazioni;
	private String parola;
	private long numanagrammi;
	private Set<String>soluzione;
	private DizionarioDAO d=new DizionarioDAO();
	
	public Set<String> risolvi(String parola){
		
		this.parola=parola;
		lettere=parola.length();
		numanagrammi=fattoriale(lettere);

		soluzione=new HashSet<String>();
		soluzione.add(parola);
		
		recursive(soluzione,1);
		
//		if (soluzione.size()==numanagrammi){
//			System.out.println("giusto");
//		}
//		else
//			System.out.println("sbagliato: aspettati "+numanagrammi+" invece "+soluzione.size());
		
		return soluzione;
		
	}
	
	public void recursive(Set<String>parziale,int level){
		Set<String> temp=new HashSet<String>();
		temp.addAll(parziale);
		if (level>=lettere){
			soluzione.addAll(temp);
		}
		else{
//			System.out.println("livello"+level);
			for (String s:parziale){
//				System.out.println("   "+s);
				Set<String> temp1=generaAnagrammi(s);
				for(String a:temp1){
					if (!temp.contains(a)){
						temp.add(a);
//						System.out.println("      "+a);
						recursive(temp,level+1);
					}
				}
			}
		}
	}
	
	private Set<String> generaAnagrammi(String s){
		Set<String> temp=new HashSet<String>();
		for (int x=0;x<lettere;x++){
			for (int y=0;y<lettere;y++){
				char[] s1=s.toCharArray();
				s1[x]=s.charAt(y);
				s1[y]=s.charAt(x);
				String a="";
				for(char c:s1){
					a=a+""+c;
				}
//				System.out.println("         "+a);
				temp.add(a);
			}
		}
		return temp;
	}
	
	private long fattoriale(int lettere){
		long result=1;
		if (lettere==0){
			return 1;
		}
		else{
			result=fattoriale(lettere-1);
			result=lettere*result;
			return result;
		}
	}
	
	public boolean controlla(String s){
		return d.cercaParola(s);
	}

}
