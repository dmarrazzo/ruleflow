package com.myspace.ruleflow;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.command.CommandFactory;

/**
* Test
*/
public class Test {
    
    public static void main(String[] args) {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        
        KieSession kSession = kContainer.newKieSession();
        
        List<Command<?>> commands = new ArrayList<>();
        commands.add(CommandFactory.newInsert(new String("ok")));
        commands.add(CommandFactory.newInsert(new String("continue")));
        commands.add(CommandFactory.newStartProcess("ruleflow.RuleFlow"));
        
        kSession.execute(CommandFactory.newBatchExecution(commands));
        
        kSession.getFactHandles().forEach(f -> System.out.println(f));
        kSession.dispose();
    }
}