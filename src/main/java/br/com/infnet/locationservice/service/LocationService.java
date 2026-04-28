package br.com.infnet.locationservice.service;

import br.com.infnet.locationservice.dto.LocationContextResponse;
import br.com.infnet.locationservice.exception.InvalidCoordinatesException;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    public LocationContextResponse getLocationContext(Double latitude, Double longitude) {
        validateCoordinates(latitude, longitude);

        String regionName = determineRegionName(latitude, longitude);
        String zoneType = determineZoneType(latitude, longitude);
        boolean sensitiveArea = isSensitiveArea(latitude, longitude);
        String threatLevel = determineThreatLevel(sensitiveArea, zoneType);
        String summary = buildSummary(regionName, zoneType, sensitiveArea, threatLevel);

        return new LocationContextResponse(
                latitude,
                longitude,
                regionName,
                zoneType,
                sensitiveArea,
                threatLevel,
                summary
        );
    }

    public void validateCoordinates(Double latitude, Double longitude) {
        if (latitude == null || longitude == null) {
            throw new InvalidCoordinatesException("Latitude e longitude são obrigatórias.");
        }

        if (latitude < -90 || latitude > 90) {
            throw new InvalidCoordinatesException("Latitude deve estar entre -90 e 90.");
        }

        if (longitude < -180 || longitude > 180) {
            throw new InvalidCoordinatesException("Longitude deve estar entre -180 e 180.");
        }
    }

    private String determineRegionName(Double latitude, Double longitude) {
        if (latitude < -20 && longitude < -40) {
            return "South Atlantic Sector";
        }

        if (latitude > 40 && longitude < -70) {
            return "North Atlantic Monitoring Zone";
        }

        if (latitude > 0 && longitude > 100) {
            return "Eastern Pacific Observation Belt";
        }

        return "Unmapped Observation Area";
    }

    private String determineZoneType(Double latitude, Double longitude) {
        if (Math.abs(latitude) < 10) {
            return "Equatorial";
        }

        if (latitude > 45 || latitude < -45) {
            return "Polar Influence";
        }

        if (longitude > -20 && longitude < 20) {
            return "Continental Corridor";
        }

        return "Coastal Frontier";
    }

    private boolean isSensitiveArea(Double latitude, Double longitude) {
        return latitude > -25 && latitude < -20 && longitude > -45 && longitude < -40;
    }

    private String determineThreatLevel(boolean sensitiveArea, String zoneType) {
        if (sensitiveArea) {
            return "HIGH";
        }

        if ("Polar Influence".equals(zoneType)) {
            return "MEDIUM";
        }

        return "LOW";
    }

    private String buildSummary(String regionName,
                                String zoneType,
                                boolean sensitiveArea,
                                String threatLevel) {

        return "Region: " + regionName +
                ", zone type: " + zoneType +
                ", sensitive area: " + sensitiveArea +
                ", threat level: " + threatLevel + ".";
    }
}
