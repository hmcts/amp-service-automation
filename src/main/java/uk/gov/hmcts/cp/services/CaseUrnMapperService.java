package uk.gov.hmcts.cp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import uk.gov.hmcts.cp.domain.CaseMapperResponse;

import static uk.gov.hmcts.cp.utils.Utils.ignoreCertificates;

@Service
@RequiredArgsConstructor
@Slf4j
public class CaseUrnMapperService {
    private static final Logger LOG = LoggerFactory.getLogger(CaseUrnMapperService.class);
    private final RestTemplate restTemplate;

    @Getter
    @Value("${service.case-mapper-service.url}")
    private String caseMapperServiceUrl;

    @Getter
    @Value("${service.case-mapper-service.path}")
    private String caseMapperServicePath;

    public String getCaseId(final String caseUrn) {
        try {
            ignoreCertificates();
            final ResponseEntity<CaseMapperResponse> responseEntity = restTemplate.exchange(
                    getCaseIdUrl(caseUrn),
                    HttpMethod.GET,
                    getRequestEntity(),
                    CaseMapperResponse.class
            );
            LOG.atInfo().log(" CaseMapperResponse is : {} and body : {} caseurn : {} ", responseEntity.getStatusCode(), responseEntity.getBody(), caseUrn);

            if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
                final CaseMapperResponse body = responseEntity.getBody();
                return body.getCaseId();
            }
        } catch (Exception e) {
            LOG.atError().log("Error while getting case id from case urn: {}", caseUrn, e);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Case not found by urn: " + caseUrn);
    }

    private String getCaseIdUrl(final String caseUrn) {
        return UriComponentsBuilder
                .fromUriString(getCaseMapperServiceUrl())
                .pathSegment(getCaseMapperServicePath())
                .pathSegment(caseUrn)
                .buildAndExpand(caseUrn)
                .toUriString();
    }

    private HttpEntity<String> getRequestEntity() {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }

}
