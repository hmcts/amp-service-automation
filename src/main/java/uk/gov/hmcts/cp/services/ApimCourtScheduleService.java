package uk.gov.hmcts.cp.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uk.gov.hmcts.cp.openapi.model.CourtScheduleResponse;
import uk.gov.hmcts.cp.repositories.ApimCourtScheduleClient;

@Service
@RequiredArgsConstructor
public class ApimCourtScheduleService {

    private final ApimCourtScheduleClient apimCourtScheduleClient;

    public CourtScheduleResponse getCourtScheduleByCaseUrn(final String caseUrn) throws ResponseStatusException {
        if (StringUtils.isEmpty(caseUrn)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "caseUrn is required");
        }
        return apimCourtScheduleClient.getCourtScheduleByCaseId(caseUrn);
    }

}
