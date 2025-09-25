package uk.gov.hmcts.cp.controllers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.cp.utils.TimeBasedOneTimePasswordUtil;

import java.security.GeneralSecurityException;

@Component
public class TrackMyCaseClient {

    @Getter
    @Value("${track-my-case.url}")
    private String trackMyCaseUrl;

    @Getter
    @Value("${one-login.email}")
    private String oneLoginEmail;

    @Getter
    @Value("${one-login.password}")
    private String oneLoginPassword;

    @Value("${one-login.2fa}")
    private String oneLogin2fa;

    public String getNext2FA() {
        try {
            return TimeBasedOneTimePasswordUtil.generateCurrentNumberString(this.oneLogin2fa);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }
}
