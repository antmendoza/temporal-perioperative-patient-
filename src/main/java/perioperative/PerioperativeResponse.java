package perioperative;

import perioperative.phases.SurgeonConsultationPhaseResponse;

import java.util.Objects;

public class PerioperativeResponse {

    private SurgeonConsultationPhaseResponse surgeonConsultationPhaseResponse;

    public PerioperativeResponse() {
    }

    public void setSurgeonConsultationPhaseResponse(final SurgeonConsultationPhaseResponse surgeonConsultationPhaseResponse) {
        this.surgeonConsultationPhaseResponse = surgeonConsultationPhaseResponse;
    }

    public SurgeonConsultationPhaseResponse getSurgeonConsultationPhaseResponse() {
        return surgeonConsultationPhaseResponse;
    }



    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PerioperativeResponse that = (PerioperativeResponse) o;
        return Objects.equals(surgeonConsultationPhaseResponse, that.surgeonConsultationPhaseResponse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surgeonConsultationPhaseResponse);
    }

}
