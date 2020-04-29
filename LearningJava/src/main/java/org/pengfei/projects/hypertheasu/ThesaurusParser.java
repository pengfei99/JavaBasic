package org.pengfei.projects.hypertheasu;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.shiro.crypto.hash.Hash;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ThesaurusParser {


    private ObjectMapper mapper;
    private JsonNode term;


    // important field of a term for MOM thesaurus
    private JsonNode preLabel = null;
    private JsonNode altLabel = null;
    private JsonNode description = null;
    private JsonNode exactMatch = null;
    private JsonNode closeMatch = null;
    private JsonNode narrowMatch = null;
    private JsonNode relatedTerm=null;

    public ThesaurusParser(String filePath) throws IOException {
        mapper = new ObjectMapper();
        term = mapper.readTree(new File(filePath));
    }

    /*Term names, not nullable*/
    public Map<String, String> getTermName() {
        String prefLabelRoot = "term";
        String prefLabelValueFieldName = prefLabelRoot + "/value";
        String prefLabelLangFieldName = prefLabelRoot + "/lang";
        Map<String, String> termNameResults = new HashMap<>();
        if (preLabel != null) {
            // preLabel contains a list of prefLabel objects which have three fields(value,type,lang)
            if (preLabel.isArray()) {
                ArrayNode arrayNode = (ArrayNode) preLabel;
                // we loop over the list to exam each term name in all language
                for (int i = 0; i < arrayNode.size(); i++) {
                    JsonNode arrayElement = arrayNode.get(i);

                    // Get all name fields of a term name
                    Map<String, List<String>> preLabelFields = new HashMap<>();
                    parse(prefLabelRoot, arrayElement, preLabelFields);
                    // System.out.println(preLabelFields.toString());
                    // for now, we only save the name and language, we use language as the key
                    termNameResults.putIfAbsent(preLabelFields.get(prefLabelLangFieldName).get(0), preLabelFields.get(prefLabelValueFieldName).get(0));
                }
            } else throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");
        }
        return termNameResults;
    }

    /*Description, not nullable*/
    public String getDescription() {
        String descriptionRoot = "description";
        String descriptionValueFieldName = descriptionRoot + "/value";
        String resultDescription = "No description";
        if (description != null) {
            // description contains a list of definition objects which have three fields(value,type,lang)
            if (description.isArray()) {
                ArrayNode arrayNode = (ArrayNode) description;

                // we check if there are only one description or not
                if (arrayNode.size() == 1) {
                    JsonNode arrayElement = arrayNode.get(0);

                    // Get all fields of a synonym
                    Map<String, List<String>> descriptionFields = new HashMap<>();
                    parse(descriptionRoot, arrayElement, descriptionFields);
                    //System.out.println(descriptionFields.toString());
                    // for now, we only use the first value of the description
                    resultDescription = descriptionFields.get(descriptionValueFieldName).get(0);
                } else
                    throw new IllegalArgumentException("Json file has multiple description. Need to determine which one to use");
            } else throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");
        }
        return resultDescription;
    }

    /*Synonym, nullable*/
    public List<String> getSynonym() {
        String synonymRoot = "synonym";
        String synonymValueFieldName = synonymRoot + "/value";
        List<String> synonymResultList = new LinkedList<>();
        if (altLabel != null) {
            // altLabel contains a list of synonym objects which have three fields(value,type,lang)
            if (altLabel.isArray()) {
                //System.out.println("In synonym");
                ArrayNode arrayNode = (ArrayNode) altLabel;
                // we loop over the list to exam each synonym
                for (int i = 0; i < arrayNode.size(); i++) {
                    JsonNode arrayElement = arrayNode.get(i);

                    // Get all synonym fields of a synonym
                    Map<String, List<String>> synonymFields = new HashMap<>();
                    parse(synonymRoot, arrayElement, synonymFields);
                    //System.out.println(synonymFields.toString());
                    // for now, we only save the name
                    synonymResultList.add(synonymFields.get(synonymValueFieldName).get(0));

                }
            } else throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");
        }
        return synonymResultList;
    }

    /*RelatedTerm, nullable*/
    public List<String> getRelatedTerm() {
        String relatedTermRoot = "related";
        String relatedValueFieldName = relatedTermRoot + "/value";
        List<String> rTermResultList = new LinkedList<>();
        if (relatedTerm != null) {
            // altLabel contains a list of synonym objects which have three fields(value,type,lang)
            if (relatedTerm.isArray()) {
                //System.out.println("In synonym");
                ArrayNode arrayNode = (ArrayNode) relatedTerm;
                // we loop over the list to exam each synonym
                for (int i = 0; i < arrayNode.size(); i++) {
                    JsonNode arrayElement = arrayNode.get(i);

                    // Get all synonym fields of a synonym
                    Map<String, List<String>> relatedTermFields = new HashMap<>();
                    parse(relatedTermRoot, arrayElement, relatedTermFields);
                    // System.out.println(relatedTermFields.toString());
                    // for now, we only save the name
                    rTermResultList.add(relatedTermFields.get(relatedValueFieldName).get(0));

                }
            } else throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");
        }
        return rTermResultList;
    }

    /*ExactMatch nullable*/
    public Map<String, String> getExactMatch() {
        String exactMatchRoot = "eMatch";
        String eMatchValueFieldName = exactMatchRoot + "/value";
        Map<String, String> eMatchResults = new HashMap<>();
        if (exactMatch != null) {
            // exactMatch contains a list of exactMatch objects which have two fields(value, type)
            if (exactMatch.isArray()) {
                ArrayNode arrayNode = (ArrayNode) exactMatch;
                // we loop over the list to exam each exactMatch object
                for (int i = 0; i < arrayNode.size(); i++) {
                    JsonNode arrayElement = arrayNode.get(i);

                    // Get all exactMatch fields of an exactMatch object
                    Map<String, List<String>> eMatchFields = new HashMap<>();
                    parse(exactMatchRoot, arrayElement, eMatchFields);
                    // System.out.println(eMatchFields.toString());

                    // for now, we only have three types of url sources
                    String url = eMatchFields.get(eMatchValueFieldName).get(0);
                    if (url.startsWith("http://vocab.getty.edu")) {
                        eMatchResults.put("Getty_AAT", url);
                    } else if (url.startsWith("http://www.wikidata.org")) {
                        eMatchResults.put("Wikidata", url);
                    } else if (url.startsWith("https://ark.frantiq.fr")) {
                        eMatchResults.put("Pactols", url);
                    } else throw new IllegalArgumentException("Unknown exact match url sources.");


                }
            } else throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");
        }
        return eMatchResults;
    }

    /*CloseMatch nullable*/
    public Map<String, String> getCloseMatch() {
        String closeMatchRoot = "cMatch";
        String cMatchValueFieldName = closeMatchRoot + "/value";
        Map<String, String> cMatchResults = new HashMap<>();
        if (closeMatch != null) {
            // closeMatch contains a list of closeMatch objects which have two fields(value, type)
            if (closeMatch.isArray()) {
                ArrayNode arrayNode = (ArrayNode) closeMatch;
                // we loop over the list to exam each closeMatch object
                for (int i = 0; i < arrayNode.size(); i++) {
                    JsonNode arrayElement = arrayNode.get(i);

                    // Get all closeMatch fields of an closeMatch object
                    Map<String, List<String>> cMatchFields = new HashMap<>();
                    parse(closeMatchRoot, arrayElement, cMatchFields);
                    //System.out.println(cMatchFields.toString());

                    // for now, we only have three types of url sources
                    String url = cMatchFields.get(cMatchValueFieldName).get(0);
                    if (url.startsWith("http://vocab.getty.edu")) {
                        cMatchResults.put("Getty_AAT", url);
                    } else if (url.startsWith("http://www.wikidata.org")) {
                        cMatchResults.put("Wikidata", url);
                    } else if (url.startsWith("https://ark.frantiq.fr")) {
                        cMatchResults.put("Pactols", url);
                    } else throw new IllegalArgumentException("Unknown exact match url sources.");
                }
            } else throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");
        }
        return cMatchResults;
    }

    /*NarrowMatch nullable*/
    public Map<String, String> getNarrowMatch() {
        String narrowMatchRoot = "nMatch";
        String nMatchValueFieldName = narrowMatchRoot + "/value";
        Map<String, String> nMatchResults = new HashMap<>();
        if (narrowMatch != null) {
            // narrowMatch contains a list of narrowMatch objects which have two fields(value, type)
            if (narrowMatch.isArray()) {
                ArrayNode arrayNode = (ArrayNode) narrowMatch;
                // we loop over the list to exam each narrowMatch object
                for (int i = 0; i < arrayNode.size(); i++) {
                    JsonNode arrayElement = arrayNode.get(i);

                    // Get all closeMatch fields of an closeMatch object
                    Map<String, List<String>> nMatchFields = new HashMap<>();
                    parse(narrowMatchRoot, arrayElement, nMatchFields);
                    // System.out.println(nMatchFields.toString());

                    // for now, we only have three types of url sources
                    String url = nMatchFields.get(nMatchValueFieldName).get(0);
                    if (url.startsWith("http://vocab.getty.edu")) {
                        nMatchResults.put("Getty_AAT", url);
                    } else if (url.startsWith("http://www.wikidata.org")) {
                        nMatchResults.put("Wikidata", url);
                    } else if (url.startsWith("https://ark.frantiq.fr")) {
                        nMatchResults.put("Pactols", url);
                    } else throw new IllegalArgumentException("Unknown exact match url sources.");
                }
            } else throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");
        }
        return nMatchResults;
    }


    public void showTermDetail() {
        ThesaurusParser.traverse("Term", term);
    }


    public Map<String, JsonNode> convertFileToJsonNode() {
        Map<String, JsonNode> fields = new HashMap<>();
        if (term.isNull()) throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");
        else if (term.isArray()) throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");
            //term is an object
        else if (term.isObject()) {
            System.out.println("File parsed to object");
            // get root object of thesaurus(e.g.https://ark.mom.fr/ark:/76609/crtrMxHMHz9TJ)
            // Can't use json root field name, because each term has different json field name.
            // String rootPath="https://ark.mom.fr/ark:/76609/crtrMxHMHz9TJ";
            JsonNode rootObj = ThesaurusParser.getObjectOnlyFieldValue(term);
            // get fields of first level field
            List<String> rootObjFieldList = ThesaurusParser.getObjectFieldList(rootObj);

            System.out.println("Child fields list: ");
            rootObjFieldList.forEach(a -> System.out.println(a));

            //get pref Label json node
            if (rootObjFieldList.contains(MomThesaurusFieldConfiguration.PREF_LABEL)) {
                preLabel = rootObj.get(MomThesaurusFieldConfiguration.PREF_LABEL);
                fields.put("preLabel", preLabel);
            } else throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");

            //get description json node
            if (rootObjFieldList.contains(MomThesaurusFieldConfiguration.DESCRIPTION)) {
                description = rootObj.get(MomThesaurusFieldConfiguration.DESCRIPTION);
                fields.put("description", description);
            } else throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");

            //get alt Label json node, alt label is nullable, so no need to throw exception
            if (rootObjFieldList.contains(MomThesaurusFieldConfiguration.ALT_LABEL)) {
                altLabel = rootObj.get(MomThesaurusFieldConfiguration.ALT_LABEL);
                fields.put("altLabel", altLabel);
            }
            //get related term json node, which is nullable, so no need to throw exception
            if (rootObjFieldList.contains(MomThesaurusFieldConfiguration.RELATED_TERM)) {
                relatedTerm = rootObj.get(MomThesaurusFieldConfiguration.RELATED_TERM);
                fields.put("relatedTerm", relatedTerm);
            }
            //get exact match json node, which is nullable, so no need to throw exception
            if (rootObjFieldList.contains(MomThesaurusFieldConfiguration.EXACT_MATCH)) {
                exactMatch = rootObj.get(MomThesaurusFieldConfiguration.EXACT_MATCH);
                fields.put("exactMatch", exactMatch);
            }


            //get close match json node, which is nullable, so no need to throw exception
            if (rootObjFieldList.contains(MomThesaurusFieldConfiguration.CLOSE_MATCH)) {
                closeMatch = rootObj.get(MomThesaurusFieldConfiguration.CLOSE_MATCH);
                fields.put("closeMatch", closeMatch);
            }

            //get narrow match json node, which is nullable, so no need to throw exception
            if (rootObjFieldList.contains(MomThesaurusFieldConfiguration.NARROW_MATCH)) {
                narrowMatch = rootObj.get(MomThesaurusFieldConfiguration.NARROW_MATCH);
                fields.put("narrowMatch", narrowMatch);
            }

            // if json file root field is not an object, then not a valid json file
        } else throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");

        return fields;
    }


    public static JsonNode getObjectOnlyFieldValue(JsonNode source) {
        int count = 0;
        Iterator<String> fieldNames = source.fieldNames();
        JsonNode fieldValue = null;
        while (fieldNames.hasNext()) {
            count++;
            // if count>1, it means object has more than one field, which means not a valid mom json file.
            if (count > 1) {
                throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");
            }
            String fieldName = fieldNames.next();
            System.out.println("Root fieldName:" + fieldName);
            fieldValue = source.get(fieldName);

        }
        return fieldValue;
    }

    public static List<String> getObjectFieldList(JsonNode source) {
        Iterator<String> fieldNames = source.fieldNames();
        List<String> result = new LinkedList<>();

        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            result.add(fieldName);
        }
        return result;
    }

    /**
     * This method traverse all fields inside a JsonNode. It stores all leaf field(not object and array) name value pair
     * in a map
     *
     * @param root               is the root of json node which you want to traverse
     * @param rootName           is the root name of the json node
     * @param fieldNameValuePair is used to save the result field name value pair
     * @return void
     * @author Pengfei liu
     * @version 1.0
     * @since 2020-04-19
     */
    public void parse(String rootName, JsonNode root, Map<String, List<String>> fieldNameValuePair) {
        //note we can't create a new object and store information in a recursive method, because each new call will
        // reset the store object and remove all information previous saved.
        // if the field is a sub json object, for each field of the object call traverse()
        if (root.isObject()) {
            Iterator<String> fieldNames = root.fieldNames();

            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode fieldValue = root.get(fieldName);
                parse(rootName + "/" + fieldName, fieldValue, fieldNameValuePair);
            }
            // if the field is an array traverse each element with the name of root
        } else if (root.isArray()) {
            ArrayNode arrayNode = (ArrayNode) root;
            for (int i = 0; i < arrayNode.size(); i++) {
                JsonNode arrayElement = arrayNode.get(i);
                parse(rootName, arrayElement, fieldNameValuePair);
            }
            // if the field is a simple primitive type, It means we reached a leaf. so we can store it in the map
        } else {
            fieldNameValuePair.computeIfAbsent(rootName, k -> new ArrayList<>()).add(root.asText());
        }

    }

    /**
     * This method traverse all fields inside a JsonNode. If the root is the root of a json object. It can traverse
     * the whole json object and print all fields and their values.
     *
     * @param root is the root of json node which you want to traverse
     * @return void
     * @author Pengfei liu
     * @version 1.0
     * @since 2020-04-19
     */
    public static void traverse(String rootName, JsonNode root) {

        // if the field is a sub json object, for each field of the object call traverse()
        if (root.isObject()) {
            Iterator<String> fieldNames = root.fieldNames();

            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode fieldValue = root.get(fieldName);
                traverse(rootName + "/" + fieldName, fieldValue);
            }
            // if the field is an array traverse each element with the name of root
        } else if (root.isArray()) {
            ArrayNode arrayNode = (ArrayNode) root;
            for (int i = 0; i < arrayNode.size(); i++) {
                JsonNode arrayElement = arrayNode.get(i);
                traverse(rootName, arrayElement);
            }
            // if the field is a simple primitive type, print it as string.
        } else {

            System.out.println("field_name: " + rootName + ", field_value: " + root.asText());

        }
    }
}
