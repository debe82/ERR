package se.salt.rri.jpaentities.clinicalStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClinicalStatusRepository implements IClinicalStatusRepository{

    @Autowired
    IJpaClinicalStatusRepository jpaRepo;

    @Override
    public ClinicalStatus getStatus(Long id) {
        return jpaRepo.findById(id).orElse(null);
    }

    @Override
    public ClinicalStatus addNewStatus(ClinicalStatus status) {
        return jpaRepo.save(status);
    }

    @Override
    public List<ClinicalStatus> getAllStatus() {
        Iterable<ClinicalStatus> listOfStatus = jpaRepo.findAll();
        return Streamable.of(listOfStatus).toList();
    }

    @Override
    public ClinicalStatus getStatusByTitle(String title) {
        return jpaRepo.findByTitle(title);
    }

    public void deleteStatus(Long id) {
        jpaRepo.deleteById(id);
    }
}
