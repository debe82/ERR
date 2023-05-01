package se.salt.rri.jpaentities.clinicalStatus;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import se.salt.rri.jpaentities.rat.RatRepository;
import se.salt.rri.jpaentities.rat.RescuedRat;
import se.salt.rri.jpaentities.ratStatus.RatStatus;
import se.salt.rri.models.AddRatDto;
import se.salt.rri.models.ClinicalStautsDto;
import se.salt.rri.models.RatDto;
import se.salt.rri.models.RatStatusDto;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicalStatusService {
    @Autowired
    ClinicalStatusRepository repo;

    public ClinicalStatus convertStatusFromDto(ClinicalStautsDto statusDto) {
        System.out.println("convert to to clinical");
        return new ClinicalStatus(statusDto.title(), statusDto.description(), statusDto.medInstructions());
    }

    public List<ClinicalStatus> convertStatusListFromDto(List<ClinicalStautsDto> dtoList){
        System.out.println("converting dtoList to clinical list");
//        return dtoList.stream().map(status -> convertStatusFromDto(status)).collect(Collectors.toList());
        return AddNewStatus(dtoList);
    }

    public RatStatusDto convertStatusToDto(RatStatus ratStatus) {
        return new RatStatusDto(
                ratStatus.getClinicalStatus().getTitle(),
                ratStatus.getClinicalStatus().getDescription(),
                ratStatus.getClinicalStatus().getMedInstructions(),
                ratStatus.getStartDate(),
                ratStatus.getEndDate(),
                ratStatus.isCured()
        );
    }

    public List<RatStatusDto> convertRatStatusListToDto(List<RatStatus> ratStatusList){
        return ratStatusList.stream().map(status -> convertStatusToDto(status)).collect(Collectors.toList());
    }

    public ClinicalStatus getClinicalStatus(Long id) {
        ClinicalStatus clinicalStatus = repo.getStatus(id);
        return clinicalStatus;
    }

    public ClinicalStatus getStatusByTitle(String title) {
        ClinicalStatus status = repo.getStatusByTitle(title);
        if (status == null) return null;
        return status;
    }


    public List<ClinicalStatus> getAllStatuses() {
        List<ClinicalStatus> statusList = repo.getAllStatus();
        return statusList;
    }

    public List<ClinicalStatus> AddNewStatus(List<ClinicalStautsDto> stautsDtoList) {
        List<ClinicalStatus> clinicalStatusList = new ArrayList<>();
        for(ClinicalStautsDto statusDto : stautsDtoList) {
            ClinicalStatus clinicalStatusToAdd = new ClinicalStatus();
            ClinicalStatus clinicalStatus = getStatusByTitle(statusDto.title());
            if (clinicalStatus == null) {
                clinicalStatusToAdd.setTitle(statusDto.title());
                clinicalStatusToAdd.setDescription(statusDto.description());
                clinicalStatusToAdd.setMedInstructions(statusDto.medInstructions());
                ClinicalStatus added = repo.addNewStatus(clinicalStatusToAdd);
            } else {
                clinicalStatusToAdd = clinicalStatus;
            }
            clinicalStatusList.add(clinicalStatusToAdd);
        }

        return clinicalStatusList;
    }

    public void deleteStatus(Long id) {
        repo.deleteStatus(id);
    }
}
