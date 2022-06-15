package perioperative.phases;

import java.util.Objects;

public class CompleteSurgeonConsultationRequest {
    private boolean isPerioperative;

    public CompleteSurgeonConsultationRequest() {}

    public boolean isPerioperative() {
        return isPerioperative;
    }

    public void setPerioperative(final boolean perioperative) {
        isPerioperative = perioperative;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CompleteSurgeonConsultationRequest that = (CompleteSurgeonConsultationRequest) o;
        return isPerioperative == that.isPerioperative;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isPerioperative);
    }

}
