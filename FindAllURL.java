/**
 * for the assignment
 * 
 * @ Peng Yu 
 * @ August 6
 */
import edu.duke.*;
import java.io.*;

public class FindAllURL
{   
    public StorageResource storeAllURLs(String urls){
    int start  = 0;
    String http = "http";
    StorageResource urlsr = new StorageResource();
    while (true) {
        int startloc = urls.toLowerCase().indexOf("href=", start);
        
        if (startloc == -1){
            break;
        }
        //if( startloc == urls.toLowerCase().indexOf("http",startloc+6)){
        else{    
            int stoploc = urls.indexOf("\"",startloc+6);
            String url = urls.substring(startloc+6, stoploc);
            if(url.toLowerCase().indexOf("http") !=-1){
                urlsr.add(url);
            }
            start = stoploc;
        }
            
    }
    return urlsr;
    }
    
    public void printURLs(StorageResource urlsr){
        int countHttps = 0;
        int countComEnd = 0;
        int countComs = 0;
        for(String eachURL:urlsr.data()){
   
            System.out.println(eachURL);
            if (eachURL.startsWith("https")){
                countHttps = countHttps+1;
            }
            if (eachURL.toLowerCase().indexOf(".com")!= -1){
                countComs = countComs+1;
            }
            if (eachURL.endsWith(".com") || eachURL.endsWith(".com/")){
                countComEnd = countComEnd+1;
            }
        }
                System.out.println(urlsr.size());
                System.out.println(countHttps);
                System.out.println(countComs);
                System.out.println(countComEnd);
            
        }
    
    

    public void numberOfDots(StorageResource urlsr){
        int countDots = 0;
        for(String eachURL:urlsr.data()){
            
            int startloc = 0;
            while (true) {
            int dotloc = eachURL.toLowerCase().indexOf(".", startloc);
        
            if (startloc == -1){
                break;
            }
            //if( startloc == urls.toLowerCase().indexOf("http",startloc+6)){
            else{    
                countDots = countDots+1;
                }
            startloc = dotloc+1;    
       
            System.out.println(countDots);
            }
        }
    }
    
    public void testURLWithStorage(){
        URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
        String strand = url.asString();
        //String test = "ATGCCATAG";
        //storeAllGene(strand);
        //System.out.println(storeAllURLs(strand).size());
        //cgRatio(strand);
        //System.out.println(cgRatio(strand));
        printURLs(storeAllURLs(strand));
        //System.out.println(countCTG(strand));
        //if (expected.equals(findAllGene(test))){
        //    System.out.println("succeed!");
        //}
        //else{
        //    System.out.println("Failed");
        //}

    }
}
