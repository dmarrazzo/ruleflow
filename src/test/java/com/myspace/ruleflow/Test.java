package com.myspace.ruleflow;

import com.myspace.model.MyFact;

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
        
        KieSession ksession = kContainer.newKieSession();

        ruleflow(ksession);
        // salience(ksession);
        
        System.out.println("WM after execution:");
        ksession.getFactHandles().forEach(f -> System.out.println(ksession.getObject(f)));
        ksession.dispose();
    }

    private static void ruleflow(KieSession ksession) {
        ksession.insert(new String("ok"));
        // comment next line to test the gateway logic branch
        ksession.insert(new String("continue"));
        ksession.startProcess("ruleflow.RuleFlow");
    }

    private static void salience(KieSession ksession) {
        ksession.insert(new MyFact());
        ksession.fireAllRules();
    }
}