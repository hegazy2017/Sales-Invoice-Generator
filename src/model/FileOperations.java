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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahegazy
 */
public class FileOperations {

    
    public  ArrayList<InvoiceHeader> readInvoiceHeaderFromCSV(String fileName,String file2) {
        ArrayList<InvoiceHeader> invoiceHeader = new ArrayList<InvoiceHeader>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                InvoiceHeader invoiceheader = createinvoiceHeader(attributes,file2);
                invoiceHeader.add(invoiceheader);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return invoiceHeader;
    }
    
    private  InvoiceHeader createinvoiceHeader(String[] metadata,String fileName) {
        int invoiceNum = Integer.parseInt(metadata[0]);
        String invoiceDate = metadata[1];
        String customerName = metadata[2];
        return new InvoiceHeader(invoiceNum, invoiceDate,customerName,readInvoiceLineFromCSV(fileName));
    }
    
  public  ArrayList<InvoiceLine> readInvoiceLineFromCSV(String fileName) {
        ArrayList<InvoiceLine> invoiceLine = new ArrayList<InvoiceLine>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                InvoiceLine invoice = createinvoiceLine(attributes);
                invoiceLine.add(invoice);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return invoiceLine;
    }
 private static InvoiceLine createinvoiceLine(String[] metadata) {
        String itemName = metadata[0];
        int itemPrice = Integer.parseInt(metadata[1]);
        int count = Integer.parseInt(metadata[2]);
        return new InvoiceLine(itemName, itemPrice, count);
    }



    
 
 public void writeFile(ArrayList<InvoiceHeader> invoiceList,File invoiceHeaderFile, File invoiceLineFile) {
        FileWriter invoiceHeaderWriter = null;
        FileWriter invoiceLineWriter = null;
        try {
            invoiceHeaderWriter = new FileWriter(invoiceHeaderFile);
            invoiceLineWriter   = new FileWriter(invoiceLineFile);
            for (InvoiceHeader rowData : invoiceList) {
                invoiceHeaderWriter.write(String.valueOf(rowData.getInvoiceNum()));
                invoiceHeaderWriter.write(",");
                invoiceHeaderWriter.write(rowData.getInvoiceDate().toString());
                invoiceHeaderWriter.write(",");
                 invoiceHeaderWriter.write(rowData.getCustomerName());
                invoiceHeaderWriter.write("\n");
                for(InvoiceLine inLine : rowData.getInvoiceLines()){
                    invoiceLineWriter.write(inLine.getItemName());
                    invoiceLineWriter.write(",");
                    invoiceLineWriter.write(String.valueOf(inLine.getItemPrice()));
                    invoiceLineWriter.write(",");
                    invoiceLineWriter.write(String.valueOf(inLine.getCount()));
                    invoiceLineWriter.write("\n");
                }
            }
            invoiceHeaderWriter.flush();
            invoiceLineWriter.flush();
            invoiceHeaderWriter.close();
            invoiceLineWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                invoiceHeaderWriter.close();
            invoiceLineWriter.close();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
        }

    }

}
