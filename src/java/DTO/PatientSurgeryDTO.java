package DTO;

/**
 *
 * @author ldtvu
 */
public class PatientSurgeryDTO {
    private String patientId, patientName, dob, gender, surgeryName, opDoctor,
            anes, timeStart, timeEnd, processSurgery;

    public PatientSurgeryDTO(String patientId, String patientName, String dob, String gender, String surgeryName, String opDoctor, String anes, String timeStart, String timeEnd, String processSurgery) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.dob = dob;
        this.gender = gender;
        this.surgeryName = surgeryName;
        this.opDoctor = opDoctor;
        this.anes = anes;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.processSurgery = processSurgery;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSurgeryName() {
        return surgeryName;
    }

    public void setSurgeryName(String surgeryName) {
        this.surgeryName = surgeryName;
    }

    public String getOpDoctor() {
        return opDoctor;
    }

    public void setOpDoctor(String opDoctor) {
        this.opDoctor = opDoctor;
    }

    public String getAnes() {
        return anes;
    }

    public void setAnes(String anes) {
        this.anes = anes;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getProcessSurgery() {
        return processSurgery;
    }

    public void setProcessSurgery(String processSurgery) {
        this.processSurgery = processSurgery;
    }
    
    
}
