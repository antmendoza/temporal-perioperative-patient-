package perioperative.phases;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;

@WorkflowInterface
public interface CompleteSurgeonConsultationTask {

    @SignalMethod
    void completeSurgeonConsultation(CompleteSurgeonConsultationRequest surgeonConsultation);

}
