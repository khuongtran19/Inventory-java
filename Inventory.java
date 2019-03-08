package Project3; 
import java.io.*;
import java.util.*;  

public class Inventory
{
   private int startinventory;
   private int productid;
    
   public Inventory(int productid, int startinventory) 
   {
      this.productid = productid; 
      this.startinventory = startinventory;
   }
   public int getProductid() 
   {
      return productid; 
   } 
   public void setProductid(int productid) 
   {
      this.productid = productid; 
   }
   public int getStartinventory() 
   {
      return startinventory; 
   }
   public void setStartinventory(int startinventory) 
   {
      this.startinventory = startinventory; 
   } 
   @Override 
   public String toString() 
   {
      return + productid + "  " + startinventory; 
   } 
}

