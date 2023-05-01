package se.salt.rri.jpaentities.clinicalStatus;

import jakarta.persistence.*;
import se.salt.rri.jpaentities.rat.RescuedRat;
import se.salt.rri.jpaentities.ratStatus.RatStatus;

import java.util.List;

@Entity
public class ClinicalStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clinicalStatusId;

    private String title;

    private  String description;
    private String medInstructions;

    @OneToMany(mappedBy="clinicalStatus")
    private List<RatStatus> ratstatuses;

    public ClinicalStatus(String title, String description, String medInstructions) {
        this.title = title;
        this.description = description;
        this.medInstructions = medInstructions;
    }

    public ClinicalStatus() {
    }

    public Long getClinicalStatusId() {
        return clinicalStatusId;
    }

    public void setClinicalStatusId(Long clinicalStatusId) {
        this.clinicalStatusId = clinicalStatusId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedInstructions() {
        return medInstructions;
    }

    public void setMedInstructions(String medInstructions) {
        this.medInstructions = medInstructions;
    }

    @Override
    public String toString() {
        return "ClinicalStatus{" +
                "clinicalStatusId=" + clinicalStatusId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", medInstructions='" + medInstructions + '\'' +
                '}';
    }
}
