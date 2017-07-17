package Validate;

/**
 *
 * @author ldtvu
 */
public class PatientValidate {
    private String idError, nameError, dobError, 
            addError, emailError, phoneError, statusError, timeStart, timeEnd;

    
    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }


    public PatientValidate() {
    }

    public PatientValidate(String idError, String nameError, String dobError, String addError, String emailError, String phoneError, String statusError, String timeStart, String timeEnd) {
        this.idError = idError;
        this.nameError = nameError;
        this.dobError = dobError;
        this.addError = addError;
        this.emailError = emailError;
        this.phoneError = phoneError;
        this.statusError = statusError;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
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

    public String getIdError() {
        return idError;
    }

    public void setIdError(String idError) {
        this.idError = idError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getDobError() {
        return dobError;
    }

    public void setDobError(String dobError) {
        this.dobError = dobError;
    }

    public String getAddError() {
        return addError;
    }

    public void setAddError(String addError) {
        this.addError = addError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }
    
}
