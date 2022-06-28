package examples;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.testing.TestWorkflowRule;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class SendSignalBetweenWorkflowsWorkflowTest {

    @Rule
    public TestWorkflowRule testWorkflowRule = TestWorkflowRule.newBuilder()
            .setWorkflowTypes(WorkflowWaitSignalImpl.class, WorkflowSendSignalImpl.class)
            .setDoNotStart(true)
            .build();

    @After
    public void cleanUp() {
        testWorkflowRule.getTestEnvironment()
                .shutdown();
    }

    @Test
    public void testSendSignal() throws InterruptedException {


        testWorkflowRule.getTestEnvironment()
                .start();


        final WorkflowWaitSignal workflowWaitSignal = testWorkflowRule.getWorkflowClient()
                .newWorkflowStub(WorkflowWaitSignal.class, WorkflowOptions.newBuilder()
                        .setWorkflowId("patientId")
                        .setTaskQueue(testWorkflowRule.getTaskQueue())
                        .build());

        WorkflowClient.start(workflowWaitSignal::start);
        Assert.assertNull(workflowWaitSignal.getData());


        final WorkflowSendSignal workflowSendSignal = testWorkflowRule.getWorkflowClient()
                .newWorkflowStub(WorkflowSendSignal.class, WorkflowOptions.newBuilder()
                        .setTaskQueue(testWorkflowRule.getTaskQueue())
                        .build());

        workflowSendSignal.start();


        Assert.assertNotNull(workflowWaitSignal.getData());


    }


}
