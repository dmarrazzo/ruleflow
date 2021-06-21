Rule Flow usage example
=======================

This sample project shows the how to use the rule-flow to orchestrate DRL rules.

Rule flow are BPMN model that contains rule tasks.

![flow](src/main/resources/com/myspace/ruleflow/ruleflow.RuleFlow-svg.svg)

In `src/test/java/com/myspace/ruleflow/Test.java` the test code to start a rule flow and get back the results.

Trigger the rule flow
------------------------

### Embedded API

[src/test/java/com/myspace/ruleflow/Test.java]()

### Kieserver API client

[src/test/java/com/myspace/ruleflow/KieClient.java]()