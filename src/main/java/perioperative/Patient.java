package perioperative;

import java.util.Objects;

public class Patient {
    private String id;
    private PatientDetails patientDetails;

    public Patient() {
    }

    public Patient(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public PatientDetails getPatientDetails() {
        return patientDetails;
    }

    public void setPatientDetails(final PatientDetails patientDetails) {
        this.patientDetails = patientDetails;
    }

    public Patient withPatientDetails(final PatientDetails patientDetails) {
        final Patient patient = new Patient(this.id);
        patient.patientDetails = patientDetails;
        return patient;
    }


    @Override
    public String toString() {
        return "Patient{" + "id='" + id + '\'' + ", patientDetails=" + patientDetails + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(patientDetails, patient.patientDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientDetails);
    }

    public static class PatientDetails {

        private String name;
        private String surname;


        public PatientDetails() {}

        public PatientDetails(final String name, final String surname) {
            this.name = name;
            this.surname = surname;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(final String surname) {
            this.surname = surname;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final PatientDetails that = (PatientDetails) o;
            return Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, surname);
        }

    }

}
