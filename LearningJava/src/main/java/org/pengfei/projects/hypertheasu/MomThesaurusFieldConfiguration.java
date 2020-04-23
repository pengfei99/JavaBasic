package org.pengfei.projects.hypertheasu;

public class MomThesaurusFieldConfiguration {
    // It contains the name of term in various languages, we use the french version as the term main name
    public static final String PREF_LABEL="http://www.w3.org/2004/02/skos/core#prefLabel";
    // It contains the name of synonym terms
    public static final String ALT_LABEL="http://www.w3.org/2004/02/skos/core#altLabel";
    public static final String RELATED_TERM="http://www.w3.org/2004/02/skos/core#related";
    // It contains the description of the term
    public static final String DESCRIPTION="http://www.w3.org/2004/02/skos/core#definition";

    public static final String EXACT_MATCH ="http://www.w3.org/2004/02/skos/core#exactMatch";
    public static final String NARROW_MATCH="http://www.w3.org/2004/02/skos/core#narrowMatch";
    public static final String CLOSE_MATCH="http://www.w3.org/2004/02/skos/core#closeMatch";
}
