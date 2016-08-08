
/**
 * Assignment codes of week3-1
 * 
 * @ Peng Yu 
 * @ August 7th
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData
{
    public String countryInfo(CSVParser parser, String country){
        for (CSVRecord record: parser){
            String countryname = record.get("Country");
            if (countryname.contains(country)){
                String info =  country + " : " + record.get("Exports") + " : " +record.get("Value (dollars)");
                return info;
            }
            
        }
        return "NOT FOUND";
    }
    
    public void listExportersOneProducts(CSVParser parser, String exportitem1){
        for (CSVRecord record: parser){
            String items = record.get("Exports");
            if(items.contains(exportitem1)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportitem1, String exportitem2){
        for (CSVRecord record: parser){
            String items = record.get("Exports");
            if(items.contains(exportitem1) && items.contains(exportitem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record:parser){
            String values  = record.get("Value (dollars)");
            if (values.length() > amount.length()){
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));;
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource("exportdata.csv");
        CSVParser parser  = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Nauru"));
        //listExportersTwoProducts(parser,"fish","nuts");
        listExportersOneProducts(parser,"sugar");
        //bigExporters(parser, "$999,999,999,999");
    }
}   

