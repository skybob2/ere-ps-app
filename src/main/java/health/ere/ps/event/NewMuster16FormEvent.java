package health.ere.ps.event;

import health.ere.ps.model.muster16.Muster16PrescriptionForm;

public class NewMuster16FormEvent {
    private Muster16PrescriptionForm muster16PrescriptionForm;

    public NewMuster16FormEvent(Muster16PrescriptionForm muster16PrescriptionForm) {
        this.muster16PrescriptionForm = muster16PrescriptionForm;
    }

    public Muster16PrescriptionForm getMuster16PrescriptionForm() {
        return muster16PrescriptionForm;
    }
}