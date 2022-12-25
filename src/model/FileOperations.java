/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahegazy
 */
public class FileOperations {

    public  List<InvoiceHeader> readFile(File f) {
        if(f.exists()){
            String[] data  ;
            List<InvoiceHeader> list=null;
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(f));
            String row = null;
            while ((row = csvReader.readLine()) != null) {
                data= row.split(",");
             System.out.println(data[0]);
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    return  list;
       
        }else{
            System.out.println("File Not found");
        }
        return  null;
    }

    public void writeFile(ArrayList<InvoiceHeader> invoiceList,File f) {
        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter(f);
            for (InvoiceHeader rowData : invoiceList) {
                csvWriter.write(rowData.getInvoiceDate().toString());
                csvWriter.write(",");
                 csvWriter.write(rowData.getCustomerName());
                csvWriter.write("\n");
                for(InvoiceLine inLine : rowData.getInvoiceLines()){
                    csvWriter.write(inLine.getItemName());
                    csvWriter.write(",");
                    csvWriter.write(String.valueOf(inLine.getItemPrice()));
                    csvWriter.write(",");
                    csvWriter.write(String.valueOf(inLine.getCount()));
                    csvWriter.write("\n");
                }
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                csvWriter.close();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
        }

    }

    public Date getCurrentDate() {
         Date date,date1=null;
        try {
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
             date = new Date();
             date1=new SimpleDateFormat("dd/MM/yyyy").parse(f.format(date));
         
        } catch (ParseException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
           return (date1);
     
    }
}
