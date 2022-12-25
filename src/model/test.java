/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ahegazy
 */
public class test {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter HOw Many Invoice do you want to fill");
        int numOfInvoices = in.nextInt();
        String itemName ;
        int itemPrice ;
        int itemcount ;
        int invoiceNum;
        String invoiceDate;
        String customerName;
        InvoiceLine il = null;
        InvoiceHeader ih  = null;
        ArrayList<InvoiceLine> ilist = new ArrayList<>();
        ArrayList<InvoiceLine> ilist2=null;
        ArrayList<InvoiceHeader> invoiceHeaderList = new ArrayList<>();
        FileOperations fo = new FileOperations();
    
        for (int i = 1; i <= numOfInvoices; i++) {
            invoiceNum = i;
             //----------------------- Fill Invoice Header----------------------
            System.out.println("Enter First Invoice Date");
            invoiceDate = in.next();
            System.out.println("Enter First Invoice Customer Name");
            customerName = in.next();
            //----------------------- Fill Invoice Line-------------------------
            System.out.println("Enter First Item");
            itemName = in.next();
            System.out.println("Enter First Item Price");
            itemPrice = in.nextInt();
            System.out.println("Enter First Item Count");
            itemcount = in.nextInt();
            il = new InvoiceLine(itemName, itemPrice, itemcount);
            ilist.add(il);
            ih  = new InvoiceHeader(invoiceNum, invoiceDate, customerName, ilist);
            invoiceHeaderList.add(ih);
        }
    
         String fileName = "InvoiceHeader.csv";
        if(!fileName.contains("csv")){
              System.out.println("Error Extension");
        }else{    
        File f = new File("InvoiceHeader.csv");   
        
         for (InvoiceHeader il2 : invoiceHeaderList) {
            System.out.println(il2.getInvoiceNum()+"\n" +"{ \n" + il2.getInvoiceDate() + "," + il2.getCustomerName()+"\n");
     
                 ilist2  = il2.getInvoiceLines();
                 for(InvoiceLine inl : ilist2){
                   System.out.println(inl.getItemName() + "," + inl.getItemPrice() + "," + inl.getCount()+"\n"+"}"); 
                 }
                      
        }
          fo.writeFile(invoiceHeaderList,f);
       System.out.println("-------------- Writing Done----------");
        }
        
   
     
           
        
       /*
       List<InvoiceHeader> listInvoicesList = fo.readFile(f);
       listInvoicesList.forEach((inread) -> {
           System.out.println("Read"+inread.getCustomerName());
           });
         */
    }
}
