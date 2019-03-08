package Project3; 
import java.io.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
public class inventoryfile 
{
   static LinkedList<Product> products;
   static LinkedList<Sales> sales;
   static LinkedList<Inventory> inventory;
   public static void main(String[]args) 
   {
      products = readProductData("Project3/Product Data.csv");
      sales = readSalesData("Project3/Sales Data.csv");
      inventory = readInventoryData("Project3/Inventory Data.csv");
      System.out.printf("%-11s %-20s %-10s %-10s \n","Product ID "," Starting Inventory "," Sold "," Final Units ");
 
      Collections.sort(sales);
      combine();
      printInventory();
   }
   
   public static void combine()
   {
      Sales s1, s2;
      int index = 0;
      int size = sales.size();
      
      for(int i = 0; i < size; i++){
         if(i == 0)
            index++;
         else{
            s1 = sales.get(index - 1);
            s2 = sales.get(index);
            
            if(s1.getProductid() == s2.getProductid()){
               s1.setUnits(s1.getUnits() + s2.getUnits());
               sales.remove(index);
            }
            else{
               index++;
            }
         }
      }
   }   
   private static void printInventory(){
      for(Inventory i: inventory){
         for(Sales s: sales){
            int actsold=0;
            int remains=i.getStartinventory()-s.getUnits();
            if(i.getProductid()==s.getProductid()){
               if(i.getStartinventory()>s.getUnits()){
                  actsold=s.getUnits();      
                  System.out.printf("%-11s %-20s %-10s %-10s \n",i.getProductid(),i.getStartinventory(), actsold,remains);
               }
               else if(s.getUnits()>i.getStartinventory())
               {
                  actsold=i.getStartinventory();
                  int remain1=0;
                  System.out.printf("%-11s %-20s %-10s %-10s \n",i.getProductid(),i.getStartinventory(), actsold,remain1);
               }
            }
         }
      } 
         
   }
   private static LinkedList<Product> readProductData(String fileName) 
   {
      LinkedList<Product> products = new LinkedList<>();
      Path pathToFile = Paths.get(fileName);
      try (BufferedReader br = Files.newBufferedReader(pathToFile,StandardCharsets.US_ASCII)) 
      {
         String line = br.readLine();
         while (line != null) 
         {
            line = br.readLine();
            String[] data = null;
            if(line != null){
               data = line.split(",");
            }
            if(data != null && data.length > 0){
               Product product = createProduct(data);
               products.add(product);
            }
         }
      }
      catch (IOException ioe) 
      {
         ioe.printStackTrace(); 
      }
      return products; 
   }
   private static Product createProduct(String[] data1) 
   {
      int productid = Integer.parseInt(data1[0]);    
      double unitprice = Double.parseDouble(data1[2].substring(1, data1[2].length()));
      String description = data1[1];
      return new Product(productid, unitprice, description);
   }
   private static LinkedList<Sales> readSalesData(String fileName) 
   {
      LinkedList<Sales> sales = new LinkedList<>();
      Path pathToFile = Paths.get(fileName);
      try (BufferedReader br = Files.newBufferedReader(pathToFile,StandardCharsets.US_ASCII)) 
      {
         String line = br.readLine();
         while (line != null) 
         {
            line = br.readLine();
            String[] data = null;
            if(line != null)
            {
               data = line.split(",");
            }
            if(data != null && data.length > 0)
            {
               Sales product = createSales(data);
               sales.add(product);
            }
         }
      }
      catch (IOException ioe) 
      {
         ioe.printStackTrace(); 
      }
      return sales; 
   }
   private static Sales createSales(String[] data2) 
   {
      double customerid= Integer.parseInt(data2[0]);     
      int productid= Integer.parseInt(data2[1]);
      int units= Integer.parseInt(data2[2]);
      return new Sales(productid, customerid, units);
   }
   private static LinkedList<Inventory> readInventoryData(String fileName) 
   {
      LinkedList<Inventory> inventory = new LinkedList<>();
      Path pathToFile = Paths.get(fileName);
      try (BufferedReader br = Files.newBufferedReader(pathToFile,StandardCharsets.US_ASCII)) 
      {
         String line = br.readLine();
         while (line != null) 
         {
            line = br.readLine();
            String[] data = null;
            if(line != null)
            {
               data = line.split(",");
            }
            if(data != null && data.length > 0)
            {
               Inventory product = createInventory(data);
               inventory.add(product);
            }
         }
      }
      catch (IOException ioe) 
      {
         ioe.printStackTrace(); 
      }
      return inventory; 
   }
   private static Inventory createInventory(String[] data3) 
   {     
      int productid= Integer.parseInt(data3[0]);
      int startinventory= Integer.parseInt(data3[1]);
      return new Inventory(productid, startinventory);
   }
}