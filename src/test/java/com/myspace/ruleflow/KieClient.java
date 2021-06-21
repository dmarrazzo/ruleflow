package com.myspace.ruleflow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;

public class KieClient {

    private static final String URL = "http://localhost:8080/kie-server/services/rest/server";
    private static final String user = System.getProperty("username", "donato");
    private static final String password = System.getProperty("password", "donato");
    private static final String CONTAINER = "ruleflow_1.0.0-SNAPSHOT";
  
    public static void main(String[] args) {
        KieClient clientApp = new KieClient();

        long start = System.currentTimeMillis();

        clientApp.ruleflow("ruleflow.RuleFlow");

        long end = System.currentTimeMillis();
        System.out.println("elapsed time: " + (end - start));
    }

    private void ruleflow(String processId) {
        try {
            KieServicesClient client = getClient();

            var commands = new ArrayList<Command<?>>();
            KieCommands commandsFactory = KieServices.Factory.get().getCommands();
            commands.add(commandsFactory.newInsert(new String("ok")));
            commands.add(commandsFactory.newStartProcess(processId));
            commands.add(commandsFactory.newGetObjects());
            BatchExecutionCommand batchExecution = commandsFactory.newBatchExecution(commands, "ksession");

            RuleServicesClient ruleClient = client.getServicesClient(RuleServicesClient.class);
            ServiceResponse<ExecutionResults> results = ruleClient.executeCommandsWithResults(CONTAINER, batchExecution);

            System.out.println(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private KieServicesClient getClient() {
        KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(URL, user, password);

        // Configuration for JMS
        // KieServicesConfiguration config =
        // KieServicesFactory.newJMSConfiguration(connectionFactory, requestQueue,
        // responseQueue, username, password)

        // Marshalling
        config.setMarshallingFormat(MarshallingFormat.JSON);
        Set<Class<?>> extraClasses = new HashSet<Class<?>>();
        config.addExtraClasses(extraClasses);
        Map<String, String> headers = null;
        config.setHeaders(headers);
        KieServicesClient client = KieServicesFactory.newKieServicesClient(config);

        return client;
    }

}
