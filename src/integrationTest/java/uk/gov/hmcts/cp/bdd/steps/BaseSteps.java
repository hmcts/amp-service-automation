package uk.gov.hmcts.cp.bdd.steps;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class BaseSteps {

    private static final String COURTHOUSES_BASE_API_VERSION = "0.0.2";
    private static final String COURTJUDGES_BASE_API_VERSION = "0.2.0";
    private static final String COURTSCHEDULING_BASE_API_VERSION = "0.1.0";
    public static final String COURTHOUSES_BASE_API_URL = "https://virtserver.swaggerhub.com/HMCTS-DTS/api-cp-refdata-courthearing-courthouses/" + COURTHOUSES_BASE_API_VERSION;
    public static final String COURTJUDGES_BASE_API_URL = "https://virtserver.swaggerhub.com/HMCTS-DTS/api-cp-refdata-courthearing-judges/" + COURTJUDGES_BASE_API_VERSION;
    public static final String COURTSCHEDULING_BASE_API_URL = "https://virtserver.swaggerhub.com/HMCTS-DTS/api-cp-crime-schedulingandlisting-courtschedule/" + COURTSCHEDULING_BASE_API_VERSION;
    public static final String COURTHOUSES_API = "/courthouses";
    public static final String COURTJUDGES_API = "/judges";
    public static final String COURTSCHEDULING_API = "/case";
    public static final String COURTSCHEDULING = "/courtschedule";

    public static final String TRACK_MY_CASE_HEALTH = "/health";

    private static final String BODY = "body";

    private final HashMap<String, Object> localData = new HashMap<>();
    protected final ObjectMapper objectMapper = new ObjectMapper();

    public void cleatLocalData() {
        this.localData.clear();
    }

    public <T> void setBody(T body) {
        this.localData.put(BODY, body);
    }

    @SuppressWarnings("unchecked")
    public <T> T getBody() {
        return (T) this.localData.get(BODY);
    }
}
