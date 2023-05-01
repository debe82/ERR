package se.salt.rri.models;

import java.util.List;

public record UpdRatDto(
        String name,
        String breed,
        int age,
        String sex,
        boolean spayed,
        String cityName,

        String countryName,
        List<RatStatusDto> statuses
) {
}
