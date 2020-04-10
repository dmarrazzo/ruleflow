package com.myspace.ruleflow;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
* Test
*/
public class Test {
    
    public static void main(String[] args) {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        
        KieSession kSession = kContainer.newKieSession();

        kSession.insert(new String("ok"));
        // comment next line to test the gateway logic branch
        kSession.insert(new String("continue"));
        kSession.startProcess("ruleflow.RuleFlow");
        
        kSession.getFactHandles().forEach(f -> System.out.println(f));
        kSession.dispose();
    }
}