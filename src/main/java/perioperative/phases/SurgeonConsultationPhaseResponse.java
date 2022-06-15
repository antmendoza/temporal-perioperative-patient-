package perioperative.phases;

import java.util.Objects;

public class SurgeonConsultationPhaseResponse {

    private String result;

    public SurgeonConsultationPhaseResponse() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(final String result) {
        this.result = result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SurgeonConsultationPhaseResponse that = (SurgeonConsultationPhaseResponse) o;
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }

}
