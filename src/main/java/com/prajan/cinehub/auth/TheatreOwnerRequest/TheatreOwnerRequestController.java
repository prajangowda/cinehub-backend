package com.prajan.cinehub.auth.TheatreOwnerRequest;

import com.prajan.cinehub.auth.model.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TheatreOwnerRequestController {

    private final TheatreOwnerRequestService service;

    @PostMapping("/owner/request")
    public TheatreOwnerResponseDto submit(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody TheatreOwnerRequest request
    ) {

        log.info("Submitting theatre owner request for user: {}", userDetails.getUsername());
        return service.submitRequest(userDetails.getId(), request);
    }

    @GetMapping("/admin/owner-requests")
    public List<TheatreOwnerResponseDto> pendingRequests() {
        log.info("Fetching pending theatre owner requests");
        return service.getPendingRequests();
    }

    @PutMapping("/admin/owner-requests/{id}/approve")
    public void approve(@PathVariable Long id) {
        log.info("Approving theatre owner request with ID: {}", id);
        service.approve(id);
    }

    @PutMapping("/admin/owner-requests/{id}/reject")
    public void reject(@PathVariable Long id) {
        log.info("Rejecting theatre owner request with ID: {}", id);
        service.reject(id);
    }
}