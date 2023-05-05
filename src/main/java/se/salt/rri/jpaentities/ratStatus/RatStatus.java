package se.salt.rri.jpaentities.ratStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import se.salt.rri.jpaentities.clinicalStatus.ClinicalStatus;
import se.salt.rri.jpaentities.rat.RescuedRat;

import java.util.Date;

@Entity
@Table(name = "ratstatuses")
public class RatStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ratStatusId;
    private Date startDate;
//    private String additionalCure;
    private Date endDate;
    private boolean isCured;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "rescuedRatId")
    private RescuedRat rat;

    @ManyToOne
    @JoinColumn(name = "clinicalStatusId")
    private ClinicalStatus clinicalStatus;

    public RatStatus() {}

    public RatStatus(long ratStatusId, Date startDate, Date endDate, boolean isCured) {
        this.ratStatusId = ratStatusId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCured = isCured;
    }

    public long getRatStatusId() {
        return ratStatusId;
    }

    public void setRatStatusId(long ratStatusId) {
        this.ratStatusId = ratStatusId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isCured() {
        return isCured;
    }

    public void setCured(boolean cured) {
        isCured = cured;
    }

    public RescuedRat getRat() {
        return rat;
    }

    public void setRat(RescuedRat rat) {
        this.rat = rat;
    }

    public ClinicalStatus getClinicalStatus() {
        return clinicalStatus;
    }

    public void setClinicalStatus(ClinicalStatus clinicalStatus) {
        this.clinicalStatus = clinicalStatus;
    }

    @Override
    public String toString() {
        return "RatStatus{" +
                "ratStatusId=" + ratStatusId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isCured=" + isCured +
                ", clinicalStatus=" + clinicalStatus +
                '}';
    }
}
