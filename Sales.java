package Project3; 
import java.io.*;
import java.util.*;  

public class Sales implements Comparable<Sales>
{
   private int units;
   private double unitprice;
   private int productid;
    
   public Sales(int productid, double unitprice, int units) 
   {
      this.productid = productid;
      this.unitprice = unitprice; 
      this.units = units; 
   }
   public int getProductid() 
   {
      return productid; 
   } 
   public void setProductid(int productid) 
   {
      this.productid = productid; 
   }
   public double getUnitprice() 
   {
      return unitprice; 
   } 
   public void setUnitprice(double unitprice) 
   {
   this.unitprice = unitprice; 
   }
   public int getUnits() 
   {
   return units; 
   }
   public void setUnits(int units) 
   {
   this.units = units; 
   }
   public int compareTo(Sales s){
      if(this.productid > s.getProductid())
         return 1;
      else if(this.productid < s.getProductid())
         return -1;
      else
         return 0;
   }
   public double total(){
      return units * unitprice;
   }
   @Override 
   public String toString() 
   {
   return + productid + "  " + units + "  " + unitprice; 
   } 
}

