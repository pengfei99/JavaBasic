package org.pengfei.projects.hypertheasu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

public class AtlasJsonGenerator {
    private ObjectMapper objectMapper;
    private ObjectNode termNode;

    private Map<String, String> termNames;
    private String description;
    private List<String> synonyms = null;
    private List<String> relatedTerms = null;
    private Map<String, String> exactMatch = null;
    private Map<String, String> closeMatch = null;
    private Map<String, String> narrowMatch = null;


    public AtlasJsonGenerator(Map<String, String> termNames, String description) {
        objectMapper = new ObjectMapper();
        termNode = objectMapper.createObjectNode();
        this.termNames = termNames;
        this.description = description;
    }

    public AtlasJsonGenerator(Map<String, String> termNames, String description, List<String> synonyms) {
        this(termNames, description);
        this.synonyms = synonyms;
    }

    public AtlasJsonGenerator(Map<String, String> termNames, String description, List<String> synonyms, List<String> relatedTerms) {

        this(termNames, description, synonyms);
        this.relatedTerms = relatedTerms;

    }

    public AtlasJsonGenerator(Map<String, String> termNames, String description, List<String> synonyms, List<String> relatedTerms,
                              Map<String, String> exactMatch) {
        this(termNames, description, synonyms, relatedTerms);
        this.exactMatch = exactMatch;

    }

    public AtlasJsonGenerator(Map<String, String> termNames, String description, List<String> synonyms, List<String> relatedTerms,
                              Map<String, String> exactMatch, Map<String, String> closeMatch) {
        this(termNames, description, synonyms, relatedTerms, exactMatch);
        this.closeMatch = closeMatch;

    }

    public AtlasJsonGenerator(Map<String, String> termNames, String description, List<String> synonyms, List<String> relatedTerms,
                              Map<String, String> exactMatch, Map<String, String> closeMatch, Map<String, String> narrowMatch) {
        this(termNames, description, synonyms, relatedTerms, exactMatch, closeMatch);
        this.narrowMatch = narrowMatch;

    }

/*


"qualifiedName":"applique@Artefacts",

*/


    public void generateTerm(String glossaryName, String glossaryGuid) {


        /** Step1 : generate anchor object, not nullable
         * anchor has two not nullable fields displayText, glossaryGuid,
         * and one nullable fields "relationGuid"
         * */
        ObjectNode anchor = objectMapper.createObjectNode();
        anchor.put("displayText", glossaryName);
        anchor.put("glossaryGuid", glossaryGuid);

        //add anchor to global term object
        termNode.putObject("anchor");
        termNode.set("anchor", anchor);

        /** Step2: Add term name*/
        String termName = getName("fr");
        termNode.put("name", termName);

        /** Step3: Add long description and short description*/
        termNode.put("longDescription", description);
        termNode.put("shortDescription", termName);

        /** Step4: Add qualifiedName*/
        termNode.put("qualifiedName", termName + "@" + glossaryName);

       /* //create two users object
        ObjectNode user1 = objectMapper.createObjectNode();
        user1.put("name", "pliu");
        user1.put("age", 18);

        ObjectNode user2 = objectMapper.createObjectNode();
        user2.put("name", "haha");
        user2.put("age", 38);

        //create an array object for users
        ArrayNode users = objectMapper.createArrayNode();
        users.add(user1);
        users.add(user2);*/


    }

    public String getName(String lang) {
        return termNames.get(lang);
    }

    public void showGeneratedJsonFile() {
        try {
            System.out.println(objectMapper.writeValueAsString(termNode));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
