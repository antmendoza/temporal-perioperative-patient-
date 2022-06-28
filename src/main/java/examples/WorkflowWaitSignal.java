package examples;

import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface WorkflowWaitSignal {

    @WorkflowMethod
    void start();

    @SignalMethod
    void notify(final NotifyWfRequest request);


    @QueryMethod
    NotifyWfRequest getData();


}
