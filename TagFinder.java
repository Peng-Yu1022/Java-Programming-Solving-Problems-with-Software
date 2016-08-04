/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA triples)
 * that is a multiple of 3 letters long.
 *
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class TagFinder {
    
    private static String gene;
    private static String ending; 
    
	public String findProtein(String dna) {
		
	    int start = dna.toUpperCase().indexOf("ATG");
		int stopTAG = dna.toUpperCase().indexOf("TAG", start+3);
		int stopTGA = dna.toUpperCase().indexOf("TGA", start+3);
		int stopTAA = dna.toUpperCase().indexOf("TAA", start+3);
		if (start == -1) {
			return "";
		}
		
		if ((stopTAG - start) % 3 == 0) {
		    gene = dna.substring(start, stopTAG+3);
		    //ending = stopCodon(gene);
		    return gene;
		} 
		else if ((stopTGA - start) % 3 ==0 ){
			gene = dna.substring(start, stopTGA + 3);
			//ending = stopCodon(gene);
			return gene;
			 }
		else if ((stopTAA-start) % 3 == 0){
		    gene = dna.substring(start, stopTAA + 3);
		    //ending = stopCodon(gene);
		    return gene;
			 }
		else{	 
		    gene = "";
		    //ending = stopCodon(gene);
		    return gene;
		}
	}
	
	public String stopCodon(String genes){
	    if (genes == ""){
	        return "";
	       }
	    else{
	        System.out.println("ending with: " + genes.substring(genes.length()-3));
	        return genes.substring(genes.length()-3);
	       }   
	   }
	
	public void testing() {
		String a = "AATGCTAGTTTAAATCTGA";
		String ap = "ATGCTAGTTTAAATCTGA";
		String result = findProtein(a);
		String ending = stopCodon(result);
		if (ap.equals(result)) {
			//System.out.println(result + ", ending with: " + result.substring(result.length()-3));
			System.out.println(result);
			System.out.println("Succeed!");
			}
		else {
			System.out.println("mistake for input: " + a);
			System.out.println("got: " + result);
			System.out.println("not: " + ap);
		}
	}
}
