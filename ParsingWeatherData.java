
/**
 * Write a description of class ParsingWeatherData here.
 * 
 * @ Peng Yu
 * @ assignment2 of week3
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ParsingWeatherData{
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        
        CSVRecord coldestSoFar = null;

        for(CSVRecord currentRow : parser){
            
            coldestSoFar = getColdestOfTwoTemp(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }
    
    public CSVRecord getColdestOfTwoTemp (CSVRecord currentRow, CSVRecord coldestSoFar){
        
        if (coldestSoFar == null){
            coldestSoFar = currentRow;
            }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            if (currentTemp < coldestTemp){
                coldestSoFar = currentRow;
            }
        }
        return coldestSoFar;            
    }
    
    String coldestTemperatureFileName;
    String currentFileName;
    
    public String fileWithColdestTemperature(){
		
		CSVRecord coldestSoFar = null;
		coldestTemperatureFileName = null;
		DirectoryResource dr = new DirectoryResource();
		
		for(File f : dr.selectedFiles()){
		    FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser();
		    CSVRecord currentRow  = coldestHourInFile(parser);
			currentFileName = f.getName(); 
			coldestSoFar = getColdestOfTwoTempFile(currentRow, coldestSoFar);
			
		}
		return coldestTemperatureFileName;
	}
	
	public CSVRecord getColdestOfTwoTempFile (CSVRecord currentRow, CSVRecord coldestSoFar){
		
		
		
		if (coldestSoFar == null){
				coldestSoFar = currentRow;
				coldestTemperatureFileName = currentFileName;
			}
		else {
		    double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
		    double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
			if (currentTemp < coldestTemp && currentTemp != -9999){
				coldestSoFar = currentRow;
				coldestTemperatureFileName = currentFileName;
				
			}
		}
		return coldestSoFar;
    }
    
    public CSVRecord coldestHumidityInFile(CSVParser parser){
		
		CSVRecord coldestSoFar = null;

		for(CSVRecord currentRow : parser){
			
			coldestSoFar = getColdestOfTwoHumidity(currentRow, coldestSoFar);
		}
		return coldestSoFar;
	}
	
	public CSVRecord lowestHumidityInManyFiles(){
		
		CSVRecord coldestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		
		for(File f : dr.selectedFiles()){
		    FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser();
		    CSVRecord currentRow  = coldestHumidityInFile(parser);
			coldestSoFar = getColdestOfTwoHumidity(currentRow, coldestSoFar);
		}
		return coldestSoFar;
	}
	
	public CSVRecord getColdestOfTwoHumidity (CSVRecord currentRow, CSVRecord coldestSoFar){
		
		
		
		if (coldestSoFar == null){
				coldestSoFar = currentRow;
			}
		else {
		    if (!currentRow.get("Humidity").equals("N/A")){
			double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
			double coldestHumidity = Double.parseDouble(coldestSoFar.get("Humidity"));
			if (currentHumidity < coldestHumidity){
				coldestSoFar = currentRow;
			}
            }
			
		}
		return coldestSoFar;
			
	}
    
	public double averageTemperatureInFile(CSVParser parser){
		
		double currentTemp;
		double averageTemp;
		int numberOfTemp;
		double totalTemp;
		totalTemp = 0;
		numberOfTemp = 0;
		
		for(CSVRecord currentRow:parser){
			currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			if (currentTemp != -9999){
				numberOfTemp = numberOfTemp + 1;
				totalTemp = totalTemp + currentTemp;
			}
		}
		averageTemp = totalTemp/numberOfTemp;
		return averageTemp;
	}
	
	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
		
		double currentTemperature;
		double currentHumidity;
		double averageTemperature;
		int numberOfTemperature;
		double totalTemperature;
		totalTemperature = 0;
		numberOfTemperature = 0;
		
		for(CSVRecord currentRow : parser){
			currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
			currentTemperature = Double.parseDouble(currentRow.get("TemperatureF"));
			//if (currentTemperature != -9999){
			    
			    if (currentHumidity >= value){
			        
			        numberOfTemperature +=1;
			        totalTemperature = totalTemperature + currentTemperature;
			     }
            //}
		}
		
		if(numberOfTemperature != 0){
		    averageTemperature = totalTemperature/numberOfTemperature;
		    return averageTemperature;
		  }
		else{return 0;}  
		
	}
	
    //String coldestTemperatureFileName = null;
    //String currentFileName = null;
    
    
    public void testColdestHourInFile(){
        
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord coldestHourInfo = coldestHourInFile(parser);
            System.out.println(coldestHourInfo.get("TemperatureF") + " " + coldestHourInfo.get("DateUTC"));
        }
    }
    
    public void  testFileWithColdestTemperature(){
		String coldestTempFileName = fileWithColdestTemperature();
        System.out.println(coldestTempFileName);
	}
	
	public void testColdestHumidityInFile(){
		
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord coldestHumidityInfo = coldestHumidityInFile(parser);
		System.out.println(coldestHumidityInfo.get("Humidity") + " " + coldestHumidityInfo.get("DateUTC"));
	}
	
	public void  testLowestHumidityInManyFiles(){
		CSVRecord coldestHumidityInfo = lowestHumidityInManyFiles();
		System.out.println(coldestHumidityInfo.get("Humidity") + " " + coldestHumidityInfo.get("DateUTC"));
	//	lowestHumidityInManyFiles();
	}
	
	public void testAverageTemperatureInFile(){
		
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		System.out.println(averageTemperatureInFile(parser)); 
		
	}
	public void testAverageTemperatureWithHighHumidityInFile(){
		
		double averageTemp;
		
	    FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		averageTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
		if(averageTemp != 0){
		System.out.println("The Temperature is " + averageTemp);
        }
        else{System.out.println("No temperature with that humidity");}
	}
}