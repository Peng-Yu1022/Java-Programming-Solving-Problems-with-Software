/**
 * for the assignment
 * 
 * @ Peng Yu 
 * @ August 5
 */
import edu.duke.*;
import java.io.*;

public class StoreAllGene
{
    private static String gene;
   
    public StorageResource storeAllGene(String dna){
    int start  = 0;
    StorageResource sr = new StorageResource();
    while (true) {
        int startloc = dna.toLowerCase().indexOf("atg", start);
        if (startloc == -1){
            break;
        }
        else{
            //System.out.println("start at " + startloc);
            int stoploc = findStopIndex(dna, startloc+3);
            if (stoploc != dna.length()){
                gene = dna.substring(startloc,stoploc+3);
                //System.out.println(gene);
                sr.add(gene);
                start = stoploc;
            } 
            else{start = startloc + 3;}
            
        }
   }
    return sr;
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

   
    public double cgRatio(String dna){
        int count = 0;
        int start = 0;
        while (true) {
            int startlocc = dna.toLowerCase().indexOf("c", start);
            if (startlocc == -1){
                break;
            }
            else{
                
                count += 1;
                start = startlocc+1;
       
            }
        }
        start = 0;
        while (true) {
            int startlocc = dna.toLowerCase().indexOf("g", start);
            if (startlocc == -1){
                break;
            }
            else{
                
                count += 1;
                start = startlocc+1;
       
            }
        }
        double ratio = (double)count/dna.length();
        return ratio;
    }
    public void printGene(StorageResource sr){
        int countSatisfiedGene = 0;
        int countSatisfiedRatio = 0;
        for(String eachGene:sr.data()){
            if(eachGene.length() > 60){
                countSatisfiedGene = countSatisfiedGene +1;
                System.out.println(eachGene);
            }
        }
        System.out.println(countSatisfiedGene);
        
        for(String eachGene:sr.data()){
            if(cgRatio(eachGene) > 0.35){
                countSatisfiedRatio =countSatisfiedRatio + 1;
                System.out.println(eachGene);
            }
        }
        System.out.println(countSatisfiedRatio);
    }
    
    public int countCTG(String dna){
        int countCTG = 0;
        int startCTG = 0;
        while (true) {
        int startloc = dna.toLowerCase().indexOf("ctg", startCTG);
        if (startloc == -1){
            break;
        }
        else{
            countCTG = countCTG + 1;
            startCTG = startCTG + 3;
        }
        //System.out.println(Math.min(stop1, Math.min(stop2, stop3)));
        //return Math.min(stop1, Math.min(stop2, stop3));
        }
        return countCTG;
    }
    
    public void testFinder(){
        FileResource fr = new FileResource("brca1line.fa");
        String strand = fr.asString();
        //String test = "ATGCCATAG";
        //storeAllGene(strand);
        //System.out.println(storeAllGene(strand).size());
        //cgRatio(strand);
        //System.out.println(cgRatio(strand));
        //printGene(storeAllGene(strand));
        System.out.println(countCTG(strand));
        //if (expected.equals(findAllGene(test))){
        //    System.out.println("succeed!");
        //}
        //else{
        //    System.out.println("Failed");
        //}

    }
}
