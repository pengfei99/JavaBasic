package org.pengfei.projects.hypertheasu;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ThesaurusParser {


    private ObjectMapper mapper;
    private JsonNode term;
    private List<String> synonym = new LinkedList<>();

    // important field of a term for MOM thesaurus
    private JsonNode preLabel = null;
    private JsonNode altLabel = null;
    private JsonNode description = null;
    private JsonNode exactMatch = null;
    private JsonNode closeMatch = null;
    private JsonNode narrowMatch = null;

    public ThesaurusParser(String filePath) throws IOException {
        mapper = new ObjectMapper();
        term = mapper.readTree(new File(filePath));
    }

    /*Synonym*/
    public String getSynonym() {
        if(altLabel!=null){
            if(altLabel.isArray()) {
                System.out.println("In synonym");
                ArrayNode arrayNode = (ArrayNode) altLabel;
                for (int i = 0; i < arrayNode.size(); i++) {
                    JsonNode arrayElement = arrayNode.get(i);
                    traverse("Synonym", arrayElement);
                }
            }
            else throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");
        }
        return null;
    }


    public void showTermDetail() {
        ThesaurusParser.traverse(".", term);
    }


    public Map<String, JsonNode> convertFileToJsonNode() {
        Map<String, JsonNode> fields = new HashMap<>();
        if (term.isNull()) throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");
        else if (term.isArray()) throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");
            //term is an object
        else if (term.isObject()) {
            System.out.println("Object");
            // get root object of thesaurus(e.g.https://ark.mom.fr/ark:/76609/crtrMxHMHz9TJ)
            // Can't use json root field name, because each term has different json field name.
            // String rootPath="https://ark.mom.fr/ark:/76609/crtrMxHMHz9TJ";
            JsonNode rootObj = ThesaurusParser.getObjectOnlyFieldValue(term);
            // get fields of first level field
            List<String> rootObjFieldList = ThesaurusParser.getObjectFieldList(rootObj);
            rootObjFieldList.forEach(a -> System.out.println(a));

            //get pref Label json node
            if (rootObjFieldList.contains(MomThesaurusFieldConfiguration.PREF_LABEL)) {
                preLabel = rootObj.get(MomThesaurusFieldConfiguration.PREF_LABEL);
                fields.put("preLabel", preLabel);
            } else throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");

            //get description json node
            if (rootObjFieldList.contains(MomThesaurusFieldConfiguration.DESCRIPTION)) {
                if (rootObjFieldList.contains(MomThesaurusFieldConfiguration.DESCRIPTION)) ;
                fields.put("description", description);
            } else throw new IllegalArgumentException("Not a valid json file for MOM thesaurus.");

            //get alt Label json node, alt label is nullable, so no need to throw exception
            if (rootObjFieldList.contains(MomThesaurusFieldConfiguration.ALT_LABEL)) {
                altLabel = rootObj.get(MomThesaurusFieldConfiguration.ALT_LABEL);
                fields.put("altLabel", altLabel);
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
            if (rootObjFieldList.contains(MomThesaurusFieldConfiguration.NARROW_MATCH)){
                narrowMatch = rootObj.get(MomThesaurusFieldConfiguration.NARROW_MATCH);
                fields.put("narrowMatch",narrowMatch);
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
            System.out.println("fieldName:" + fieldName);
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

    public static void parse(String rootName, JsonNode root) {

        // if the field is a sub json object, for each field of the object call traverse()
        if (root.isObject()) {
            Iterator<String> fieldNames = root.fieldNames();

            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode fieldValue = root.get(fieldName);
                parse(rootName + "/" + fieldName, fieldValue);
            }
            // if the field is an array traverse each element with the name of root
        } else if (root.isArray()) {
            ArrayNode arrayNode = (ArrayNode) root;
            for (int i = 0; i < arrayNode.size(); i++) {
                JsonNode arrayElement = arrayNode.get(i);
                parse(rootName, arrayElement);
            }
            // if the field is a simple primitive type, print it as string.
        } else {

            //parse and save all synonym
            if (rootName.endsWith("core#altLabel/value")) {

            }


        }
    }

    /**
     * This method and traverse all fields inside a JsonNode. If the root is the root of a json object. It can traverse
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
