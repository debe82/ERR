package se.salt.rri.models;

import java.util.Date;

public record RatStatusDto(
        long id,
        String title,
        String description,

        String medInstructions,
        Date start,
        Date stop,
        boolean cured

) {
}
