package org.pengfei.projects.hypertheasu;

import java.io.IOException;
import java.net.MalformedURLException;

public class main {

    public static void main(String[] args){
        /** Step1: Get information of terms*/

       /* String url="https://thesaurus.mom.fr/opentheso/api/76609/crtrMxHMHz9TJ.json";
        String path="/tmp";
        String fileName="simpleTermOfMom.json";

        try {
            ThesaurusSource ts=new ThesaurusSource(url,path,fileName);
            ts.getContent();
        } catch (MalformedURLException e) {
            System.out.println("Url is not valid"+e.toString());
        }*/


        /** Step2: Parser json file into java object*/
        try {
            ThesaurusParser parser=new ThesaurusParser("/tmp/simpleTermOfMom.json");
            //parser.getSynonym();
           // parser.showTermDetail();
            parser.convertFileToJsonNode();
            parser.getSynonym();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
