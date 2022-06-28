package examples.signal;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.testing.TestWorkflowRule;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.UUID;

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
    public void testSendSignal() {


        testWorkflowRule.getTestEnvironment()
                .start();


        final String clientId = UUID.randomUUID()
                .toString();


        final WorkflowWaitSignal workflowWaitSignal = testWorkflowRule.getWorkflowClient()
                .newWorkflowStub(WorkflowWaitSignal.class, WorkflowOptions.newBuilder()
                        .setWorkflowId(clientId)
                        .setTaskQueue(testWorkflowRule.getTaskQueue())
                        .build());

        WorkflowClient.start(workflowWaitSignal::start);
        Assert.assertNull(workflowWaitSignal.getData());


        final WorkflowSendSignal workflowSendSignal = testWorkflowRule.getWorkflowClient()
                .newWorkflowStub(WorkflowSendSignal.class, WorkflowOptions.newBuilder()
                        .setTaskQueue(testWorkflowRule.getTaskQueue())
                        .build());

        workflowSendSignal.start(new WorkflowSendSignalRequest(clientId));


        Assert.assertNotNull(workflowWaitSignal.getData());
        Assert.assertEquals(new NotifyWfRequest(clientId), workflowWaitSignal.getData());


    }


}
