package perioperative;

import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import perioperative.phases.CompleteSurgeonConsultationTask;

@WorkflowInterface
public interface PerioperativePatient extends CompleteSurgeonConsultationTask {

    @WorkflowMethod
    PerioperativeResponse start(Patient patient);

    @QueryMethod
    Patient queryPatient();


    @SignalMethod
    void updatePatient(Patient.PatientDetails patientDetails);

}
