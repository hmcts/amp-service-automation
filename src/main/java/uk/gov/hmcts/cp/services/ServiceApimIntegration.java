package uk.gov.hmcts.cp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uk.gov.hmcts.cp.openapi.model.CourtScheduleResponse;
import uk.gov.hmcts.cp.utils.Utils;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ServiceApimIntegration {

    private final ApimCourtScheduleService apimCourtScheduleService;
    private final CaseUrnMapperService caseUrnMapperService;

    public ResponseEntity<?> getCourtScheduleByCaseUrn(final String caseUrn) {
        try {
            CourtScheduleResponse courtScheduleResponse = apimCourtScheduleService.getCourtScheduleByCaseUrn(caseUrn);
            return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(courtScheduleResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

}
