package webservices;

import dto.FunnelDTO;
import io.restassured.http.Header;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import io.vavr.control.Try;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import java.util.UUID;

import static webservices.Parser.toJson;


public class RestClient {

    private static final Logger LOG = Logger.getLogger("console");
    private static final String TEST_FUNNEL_URL = "https://credapi.credify.tech/api/brfunnelorch/v2/resume/byLeadSecret";

    public static Response sendPostTestFunnelRequest(final FunnelDTO funnelDTO) {
        String body = toJson(funnelDTO);
        return Try.of(() -> given()
                .contentType("application/json")
                .body(body)
                .header(new Header("x-cf-source-id","coding-challenge"))
                .header(new Header("x-cf-corr-id", UUID.randomUUID().toString()))
                .when().log().all()
                .and().post(TEST_FUNNEL_URL)
                .then().log().all()
                .and().extract().response())
                .onFailure(RuntimeException.class, x -> LOG.error("Unable to execute the request"))
                .get();
    }

    public static void compareResponseCodes(final int actualCode, final int expectedCode) {
        Assertions.assertThat(actualCode)
                .as("The response code is not as expected. Received %d", actualCode)
                .isEqualTo(expectedCode);
    }
}
