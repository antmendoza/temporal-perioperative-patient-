package examples;

import io.temporal.workflow.Workflow;

public class WorkflowSendSignalImpl implements WorkflowSendSignal {

    @Override
    public void start() {

        WorkflowWaitSignal waitSignal = Workflow.newExternalWorkflowStub(WorkflowWaitSignal.class, "patientId");
        final NotifyWfRequest data = new NotifyWfRequest("data");
        waitSignal.notify(data);

    }


}
