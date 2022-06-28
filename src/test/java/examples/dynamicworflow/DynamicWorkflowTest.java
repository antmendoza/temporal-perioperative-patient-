package examples.dynamicworflow;

import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.testing.TestWorkflowRule;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class DynamicWorkflowTest {


    @Rule
    public TestWorkflowRule testWorkflowRule = TestWorkflowRule.newBuilder()
            .setWorkflowTypes(DynamicWorkflowImpl.class)
            .setDoNotStart(true)
            .build();

    @After
    public void cleanUp() {
        testWorkflowRule.getTestEnvironment()
                .shutdown();
    }

    @Test
    public void dynamicWf() {

        testWorkflowRule.getTestEnvironment()
                .start();

        final WorkflowStub dynamicWorkflow = testWorkflowRule.getWorkflowClient()
                .newUntypedWorkflowStub("AnyDynamicWorkflow", WorkflowOptions.newBuilder()
                        .setTaskQueue(testWorkflowRule.getTaskQueue())
                        .build());

        final String encodedValue = "encodedValue";
        dynamicWorkflow.start(encodedValue);
        Assert.assertEquals(new DynamicWorkflowResponse(encodedValue),
                dynamicWorkflow.getResult(DynamicWorkflowResponse.class));

    }

}
