package org.pengfei.projects.hypertheasu;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public class main {

    public static void main(String[] args){
        /** Step1: Get information of terms*/

        String url="https://thesaurus.mom.fr/opentheso/api/76609/crtrMxHMHz9TJ.json";
        //String url="https://thesaurus.mom.fr/opentheso/api/76609/crtrFvPi3Si8L.json";
       // String url="https://thesaurus.mom.fr/opentheso/api/76609/crtQ5dZHOBTSV.json";
        String path="/tmp";
        String fileName="simpleTermOfMom.json";

        try {
            ThesaurusSource ts=new ThesaurusSource(url,path,fileName);
            ts.getContent();
        } catch (MalformedURLException e) {
            System.out.println("Url is not valid"+e.toString());
        }


        /** Step2: Parser json file into java object*/
        try {
            ThesaurusParser parser=new ThesaurusParser("/tmp/simpleTermOfMom.json");

            parser.showTermDetail();
            parser.convertFileToJsonNode();

            // get term name
            Map<String, String> allTermNames = parser.getTermName();
            System.out.println("All names: "+allTermNames.toString());

            // get description
            String description=parser.getDescription();
            System.out.println("Description: "+description);

            // get synonyms
            List<String> synonyms = parser.getSynonym();
            System.out.println("Synonym: "+synonyms.toString());

            // todo add related term

            // get exactMatch
            Map<String, String> exactMatch = parser.getExactMatch();
            System.out.println("ExactMatch: "+exactMatch.toString());

            // get closeMatch
            Map<String, String> closeMatch = parser.getCloseMatch();
            System.out.println("CloseMatch: "+closeMatch.toString());

            // get narrowMatch
            Map<String, String> narrowMatch = parser.getNarrowMatch();
            System.out.println("NarrowMatch: "+narrowMatch.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
