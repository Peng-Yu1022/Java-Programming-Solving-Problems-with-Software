
/**
 * for the assignment
 * 
 * @ Peng Yu 
 * @ August 5
 */
import edu.duke.*;
import java.io.*;

public class FindAllGene
{
    private static String allGene;
   
    public String findAllGene(String dna){
    
    int start  = 0;
    while (true) {
        int startloc = dna.toLowerCase().indexOf("atg", start);
        if (startloc == -1){
            break;
        }
        else{
            System.out.println("start at " + startloc);
            int stoploc = findStopIndex(dna, startloc+3);
            if (stoploc != dna.length()){
                allGene = dna.substring(startloc,stoploc+3);
                System.out.println(allGene);
                start = stoploc;
            } 
            else{start = startloc + 3;}
            
        }
   }
    return allGene;
}

    public int findStopIndex(String dna, int index){
        int stop1 = dna.toLowerCase().indexOf("tag", index);
        if (stop1 == -1 || (stop1 - index) % 3 != 0){
            stop1 = dna.length();
        }
        int stop2 = dna.toLowerCase().indexOf("tga", index);
        if (stop2 == -1 || (stop2 - index) % 3 != 0){
            stop2 = dna.length();
        }
        int stop3 = dna.toLowerCase().indexOf("taa", index);
        if (stop3 == -1 || (stop3 - index) % 3 != 0){
            stop3 = dna.length();
        }
        //System.out.println(Math.min(stop1, Math.min(stop2, stop3)));
        return Math.min(stop1, Math.min(stop2, stop3));
    
}

    public void testFinder(){
        String test = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        findAllGene(test);
        //if (expected.equals(findAllGene(test))){
        //    System.out.println("succeed!");
        //}
        //else{
        //    System.out.println("Failed");
        //}
}
}
