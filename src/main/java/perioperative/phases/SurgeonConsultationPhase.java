package perioperative.phases;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import perioperative.Patient;

@WorkflowInterface
public interface SurgeonConsultationPhase extends CompleteSurgeonConsultationTask {

    @WorkflowMethod
    SurgeonConsultationPhaseResponse start(Patient patient);


}
