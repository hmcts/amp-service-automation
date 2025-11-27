package uk.gov.hmcts.cp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uk.gov.hmcts.cp.openapi.model.CourtScheduleResponse;
import uk.gov.hmcts.cp.utils.Utils;

@Service
@RequiredArgsConstructor
public class ServiceCpCrimeSchedulingandlistingCourtschedule {

    private final CourtScheduleService courtScheduleService;
    private final CaseUrnMapperService caseUrnMapperService;

    public ResponseEntity<CourtScheduleResponse> getCourtScheduleByCaseUrn(final String caseUrn) {
        final String sanitizedCaseUrn;
        final CourtScheduleResponse courtScheduleResponse;
        try {
            sanitizedCaseUrn = Utils.sanitizeString(caseUrn);
            final String caseId = caseUrnMapperService.getCaseId(sanitizedCaseUrn);
            courtScheduleResponse = courtScheduleService.getCourtScheduleByCaseId(caseId);
        } catch (ResponseStatusException e) {
            throw e;
        }
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(courtScheduleResponse);
    }

}
