package se.salt.rri.jpaentities.clinicalStatus;

import java.util.List;

public interface IClinicalStatusRepository {
    ClinicalStatus getStatus(Long id);

    ClinicalStatus addNewStatus(ClinicalStatus status);

    List<ClinicalStatus> getAllStatus();

    ClinicalStatus getStatusByTitle(String title);
}
