package br.com.infnet.locationservice.dto;

public record ValidateLocationRequest(
        Double latitude,
        Double longitude
) {
}