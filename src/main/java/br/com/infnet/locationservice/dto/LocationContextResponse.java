package br.com.infnet.locationservice.dto;

public record LocationContextResponse(
        Double latitude,
        Double longitude,
        String regionName,
        String zoneType,
        boolean sensitiveArea,
        String threatLevel,
        String summary
) {
}