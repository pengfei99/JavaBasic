package org.pengfei.projects.hypertheasu;

import java.net.MalformedURLException;

public class main {

    public static void main(String[] args){
        String url="https://thesaurus.mom.fr/opentheso/api/76609/crtrMxHMHz9TJ.json";
        String path="/tmp";
        String fileName="simpleTermOfMom.json";

        try {
            ThesaurusSource ts=new ThesaurusSource(url,path,fileName);
            ts.getContent();
        } catch (MalformedURLException e) {
            System.out.println("Url is not valid"+e.toString());
        }


    }
}
