package se.salt.rri.models;

import se.salt.rri.jpaentities.clinicalStatus.ClinicalStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record RatDto(
  Long id,
  String name,
  int age,
  String sex,
  String breed,
  boolean spayed,

  List<RatStatusDto> clinicalHistory,

  String cityName,

  String countryName,

  UUID medicalNumber,

  Date rescue

) {
}
