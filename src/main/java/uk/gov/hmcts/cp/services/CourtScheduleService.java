package uk.gov.hmcts.cp.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uk.gov.hmcts.cp.openapi.model.CourtScheduleResponse;
import uk.gov.hmcts.cp.repositories.CourtScheduleClient;

@Service
@RequiredArgsConstructor
public class CourtScheduleService {

    private final CourtScheduleClient courtScheduleClient;

    public CourtScheduleResponse getCourtScheduleByCaseId(final String caseId) throws ResponseStatusException {
        if (StringUtils.isEmpty(caseId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "caseId is required");
        }
        return courtScheduleClient.getCourtScheduleByCaseId(caseId);
    }

}
