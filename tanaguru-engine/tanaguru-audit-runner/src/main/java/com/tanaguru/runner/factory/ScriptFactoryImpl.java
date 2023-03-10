package com.tanaguru.runner.factory;

import com.google.gson.Gson;
import com.tanaguru.domain.entity.audit.TanaguruTest;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;

@Component
public class ScriptFactoryImpl implements ScriptFactory {
    /**
     * Create an executable script.
     *
     * @param coreScript       the content script
     * @param tanaguruTestList tanaguru tests
     * @return the full script
     */
    public String create(String coreScript, Collection<TanaguruTest> tanaguruTestList) {
        StringBuilder strb = new StringBuilder();
        Gson gson = new Gson();
        strb.append(coreScript);
        strb.append("\n");
        for (TanaguruTest tanaguruTest : tanaguruTestList) {
            
            strb.append("\ncreateTanaguruTest({id:").append(tanaguruTest.getId());
            strb.append(",\nlang:`").append(tanaguruTest.getLang()).append("`");
            strb.append(",\nname:`").append(tanaguruTest.getName()).append("`");
            if(tanaguruTest.getQuery() != null && tanaguruTest.getQuery().startsWith("HTML")) {
                strb.append(",\nquery:").append(tanaguruTest.getQuery());
            }else if(tanaguruTest.getQuery() != null){
                strb.append(",\nquery:`").append(tanaguruTest.getQuery()).append("`");
            }
            strb.append(",\ntags:").append(gson.toJson(tanaguruTest.getTags()));


            if (tanaguruTest.getExpectedNbElements() != null) {
                strb.append(",\nexpectedNbElements:");
                try {
                    strb.append(
                            Integer.parseInt(tanaguruTest.getExpectedNbElements()));
                } catch (NumberFormatException nfe) {
                    strb.append(gson.toJson(tanaguruTest.getExpectedNbElements()));
                }
            }

            if (tanaguruTest.getDescription() != null) {
                strb.append(",\ndescription:`").append(tanaguruTest.getDescription()).append("`");
            }
            
            if (tanaguruTest.getCode() != null) {
                strb.append(",\ncode:`").append(tanaguruTest.getCode()).append("`");
            }
            
            if (tanaguruTest.getStatus() != null) {
                strb.append(",\nstatus:`").append(tanaguruTest.getStatus()).append("`");
            }
            
            if (tanaguruTest.getTestStatus() != null) {
                strb.append(",\ntestStatus:\"").append(tanaguruTest.getTestStatus()).append("\"");
            }
            
            if(tanaguruTest.getContrast() != null){
                strb.append(",\ncontrast:\"").append(tanaguruTest.getContrast()).append("\"");
            }

            if (tanaguruTest.getFilter() != null) {
                strb.append(",\nfilter:").append(tanaguruTest.getFilter());
            }
            
            if (tanaguruTest.getMark() != null) {
                strb.append(",\nmark:").append(gson.toJson(tanaguruTest.getMark()));
            }
            
            if (tanaguruTest.getNode() != null) {
                strb.append(",\node:").append(tanaguruTest.getNode());
            }

            if (tanaguruTest.getAnalyzeElements() != null) {
                strb.append(",\nanalyzeElements:").append(tanaguruTest.getAnalyzeElements());
            }
            strb.append("});");
        }
        strb.append("\nremoveAllDataTNG();");
        strb.append("\nreturn JSON.stringify(loadTanaguruTests());");
        return strb.toString();
    }
}
