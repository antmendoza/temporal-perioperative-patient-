package perioperative;

import io.temporal.workflow.Workflow;
import perioperative.phases.SurgeonConsultationPhase;
import perioperative.phases.CompleteSurgeonConsultationRequest;
import perioperative.phases.SurgeonConsultationPhaseResponse;

public class PerioperativePatientImpl implements PerioperativePatient {

    private Patient patient;

    private SurgeonConsultationPhase surgeonConsultationPhase;

    @Override
    public PerioperativeResponse start(final Patient patient) {

        this.patient = patient;

        surgeonConsultationPhase =
                Workflow.newChildWorkflowStub(SurgeonConsultationPhase.class);

        final PerioperativeResponse perioperativeResponse = new PerioperativeResponse();

        // This is a blocking call that returns only after child has completed.
        final SurgeonConsultationPhaseResponse surgeonConsultationPhaseResponse =
                surgeonConsultationPhase.start(patient);
        perioperativeResponse.setSurgeonConsultationPhaseResponse(surgeonConsultationPhaseResponse);


        return perioperativeResponse;
    }

    @Override
    public Patient queryPatient() {
        return this.patient;
    }


    @Override
    public void updatePatient(Patient.PatientDetails patientDetails) {
        this.patient = this.patient.withPatientDetails(patientDetails);
    }

    @Override
    public void completeSurgeonConsultation(CompleteSurgeonConsultationRequest surgeonConsultation) {
        this.surgeonConsultationPhase.completeSurgeonConsultation(surgeonConsultation);
    }

}
