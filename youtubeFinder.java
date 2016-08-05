import edu.duke.*;
import java.io.*;
/**
 * Find each URL linked to youtube.com.
 * 
 * @author Peng Yu
 * @version 4-August
 */
public class youtubeFinder
{
    public void findLink()
    {
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        //String links = ur.asString;
        for (String line : ur.lines()){
            if (line.toLowerCase().indexOf("youtube") != -1){
                int center = line.toLowerCase().indexOf("youtube");
                int beginning = line.lastIndexOf("\"",center);
                int end = line.indexOf("\"", center);
                String link = line.substring(beginning , end+1);
                System.out.println(link);
            }
            //else{
            //    System.out.println("");
            //}
        
            //System.out.println(line);  
        }
    }
}    
