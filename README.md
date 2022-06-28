# Workflow to manage a patient path

This project use Temporal to orchrestate a patient workflow. It has been build with the idea to explore and demostrate 
the use of the following temporal's capabilities:
- signalMethod
- queryMethod
- child workflows



## Communicate workflows executions through signals
- [SendSignalBetweenWorkflowsWorkflowTest](./src/test/java/examples/signal/SendSignalBetweenWorkflowsWorkflowTest.java)


## Dynamic workflow
- [DynamicWorkflowTest](./src/test/java/examples/dynamicworflow/DynamicWorkflowTest.java)

## Execute test
Either open the project in IntelliJ and run the tests from inside or in the project's root directory run:

```
mvn clean test
```
