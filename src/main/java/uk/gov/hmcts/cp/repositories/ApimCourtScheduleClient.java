package uk.gov.hmcts.cp.repositories;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import uk.gov.hmcts.cp.openapi.model.CourtScheduleResponse;

import java.net.URI;

import static uk.gov.hmcts.cp.utils.Utils.ignoreCertificates;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApimCourtScheduleClient {

    private final RestTemplate restTemplate;

    @Getter
    @Value("${service.apim-court-schedule-client.url}")
    private String apimCourtScheduleClientUrl;

    @Getter
    @Value("${service.apim-court-schedule-client.path}")
    private String apimCourtScheduleClientPath;

    @Getter
    @Value("${service.apim-court-schedule-client.apim-subscription-key}")
    private String apimCourtScheduleClientApimSubscriptionKey;

    public CourtScheduleResponse getCourtScheduleByCaseId(final String caseUrn) {
        try {
            final String apimCourtScheduleUrl = buildUrl(caseUrn);
            ignoreCertificates();
            final ResponseEntity<CourtScheduleResponse> responseEntity = restTemplate.exchange(
                apimCourtScheduleUrl,
                HttpMethod.GET,
                getRequestEntity(),
                CourtScheduleResponse.class
            );

            if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
                return responseEntity.getBody();
            }
        } catch (Exception e) {
            log.error("Error while getting court schedule response by case urn: {}", caseUrn, e);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Court schedule not found by urn: " + caseUrn);
    }

    private String buildUrl(final String caseUrn) {
        return UriComponentsBuilder
            .fromUriString(getApimCourtScheduleClientUrl())
            .path(getApimCourtScheduleClientPath())
            .buildAndExpand(caseUrn)
            .toUriString();
    }

    private HttpEntity<String> getRequestEntity() {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Ocp-Apim-Subscription-Key", getApimCourtScheduleClientApimSubscriptionKey());
        return new HttpEntity<>(headers);
    }
}
