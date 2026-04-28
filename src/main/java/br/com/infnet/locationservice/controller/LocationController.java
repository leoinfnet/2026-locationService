package br.com.infnet.locationservice.controller;

import br.com.infnet.locationservice.dto.LocationContextResponse;
import br.com.infnet.locationservice.dto.ValidateLocationRequest;
import br.com.infnet.locationservice.service.LocationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/context")
    public LocationContextResponse getLocationContext(
            @RequestParam Double latitude,
            @RequestParam Double longitude
    ) {
        return locationService.getLocationContext(latitude, longitude);
    }

    @PostMapping("/validate")
    public String validateLocation(@RequestBody ValidateLocationRequest request) {
        locationService.validateCoordinates(request.latitude(), request.longitude());
        return "Coordinates are valid.";
    }
}