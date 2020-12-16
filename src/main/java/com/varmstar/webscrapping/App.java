/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.varmstar.webscrapping;

/**
 *
 * @author jithu
 */
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {
    public static void main(String[] args) {
        
        try {
            // Here we create a document object and use JSoup to fetch the website
            Document doc = Jsoup.connect("https://prefeitura.pbh.gov.br/saude/licitacao/pregao-eletronico-151-2020").get();

          
            
            // Get the publications date and bidding date div          
            Elements tenderData = doc.getElementsByClass("lbl-licitacao");
            // Get the object data
            Elements tenderDataObj = doc.getElementsByClass("views-field").select("p");
            // Get the links
            Elements tenderLinks = doc.getElementsByClass("views-field-field-historico-da-licitacao").select("a[href]"); 
            
            
            //loop the tenderData element to get publications date and bidding date
            for (Element data : tenderData) {
              String publicationDate = data.text();
              String[] requiredData = {"DATA DA PUBLICAÇÃO:","DATA DA LICITAÇÃO:"};
              
               for(int i=0;i<requiredData.length;i++){
                   
                   if(publicationDate.startsWith(requiredData[i])){
                       // Format and print the information to the console             
                        System.out.println( publicationDate);
                        System.out.println("\n");
                   };
               } 
            }
            
            //printing object data
            String publicationObject = tenderDataObj.text();
            System.out.println( "Object :" + publicationObject);
            System.out.println("\n");
            
            //looping link element to get all the links
            for (Element dataLink : tenderLinks) {
                System.out.println("reacheddddd");
                String publicationLink = dataLink.text();
                System.out.println("\nlink: " + dataLink.attr("href"));
                System.out.println("Links :" + publicationLink);
            }
  
          // In case of any IO errors, we want the messages written to the console
          } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
