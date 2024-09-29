import Handlers.ApiRequestManager;
import Handlers.ApiResponseManager;
import Models.RequestPayload;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

public class KariyerSearchTestCases {

    @BeforeMethod
    public void setUp() {
        // Used to initialize any required setup for the tests
        System.out.println("Test Started");
    }

    @AfterMethod
    public void tearDown() {
        // Used to clean up or log information after tests
        System.out.println("Test Finished");
    }

    @Test
    @Description("Scenario for sending valid parameters and checking the success response")
    @Severity(SeverityLevel.NORMAL)
    public void ValidParameters() {
        // Create a payload with valid parameters for the search request
        RequestPayload payload = new RequestPayload();
        payload.setMemberId(26189469);
        payload.setSize(50);
        payload.setCurrentPage(1);
        payload.setKeyword("Software Test Engineer");
        payload.setLanguage(Arrays.asList(1, 2));
        payload.setDontShowAppliedJobs(false);

        Response response = ApiRequestManager.sendPostRequest(payload);

        ApiResponseManager.validateSuccessResponse(response);
    }

    @Test
    @Description("Scenario for sending an invalid memberId and checking error response")
    @Severity(SeverityLevel.NORMAL)
    public void InvalidMemberId() {
        // Create a payload with an invalid memberId
        RequestPayload payload = new RequestPayload();
        payload.setMemberId("x");  // Invalid member ID, should trigger an error
        payload.setSize(50);
        payload.setCurrentPage(1);
        payload.setKeyword("Software Test Engineer");
        payload.setLanguage(Arrays.asList(1, 2));
        payload.setDontShowAppliedJobs(false);

        // Sending the POST request and receiving the response
        Response response = ApiRequestManager.sendPostRequest(payload);

        // Validate that the response contains an error
        ApiResponseManager.validateErrorResponseWithMessage(response, 400, "");
    }

    @Test
    @Description("Scenario for sending an invalid size and checking the total job count")
    @Severity(SeverityLevel.NORMAL)
    public void InvalidSize() {
        // Create a payload with an invalid size (0, which is not allowed)
        RequestPayload payload = new RequestPayload();
        payload.setMemberId(123);
        payload.setSize(0);  // Invalid size, should return 0 job count
        payload.setCurrentPage(1);
        payload.setKeyword("Software Test Engineer");
        payload.setLanguage(Arrays.asList(1));
        payload.setDontShowAppliedJobs(false);

        Response response = ApiRequestManager.sendPostRequest(payload);

        ApiResponseManager.validateSuccessResponse(response);
        ApiResponseManager.validateTotalJobCount(response, 0);
    }

    @Test
    @Description("Scenario for sending a request where applied jobs should not be shown")
    @Severity(SeverityLevel.NORMAL)
    public void DontShowAppliedJobsTrue() {
        // Create a payload where applied jobs should not be shown
        RequestPayload payload = new RequestPayload();
        payload.setMemberId(1);
        payload.setSize(50);
        payload.setCurrentPage(1);
        payload.setKeyword("Software Test Engineer");
        payload.setLanguage(Arrays.asList(1));
        payload.setDontShowAppliedJobs(true);  // Applied jobs should not be displayed

        Response response = ApiRequestManager.sendPostRequest(payload);

        ApiResponseManager.validateSuccessResponse(response);
        ApiResponseManager.validateAppliedJobs(response, true);
    }

    @Test
    @Description("Scenario for sending an empty keyword and validating total job count")
    @Severity(SeverityLevel.NORMAL)
    public void EmptyKeyword() {
        // Create a payload with an empty keyword (searching for all jobs)
        RequestPayload payload = new RequestPayload();
        payload.setMemberId(123);
        payload.setSize(50);
        payload.setCurrentPage(1);
        payload.setKeyword("");  // Empty keyword should return all jobs
        payload.setLanguage(Arrays.asList(1));
        payload.setDontShowAppliedJobs(true);

        Response response = ApiRequestManager.sendPostRequest(payload);

        ApiResponseManager.validateSuccessResponse(response);
        ApiResponseManager.validateTotalJobCountGreaterThan(response, 0);
    }

    @Test
    @Description("Scenario for sending an invalid language parameter")
    @Severity(SeverityLevel.NORMAL)
    public void InvalidLanguage() {
        // Create a payload with an invalid language parameter
        RequestPayload payload = new RequestPayload();
        payload.setMemberId(123);
        payload.setSize(50);
        payload.setCurrentPage(1);
        payload.setKeyword("Software Test Engineer");
        payload.setLanguage(Arrays.asList(3));  // Invalid language, should return 0 jobs
        payload.setDontShowAppliedJobs(false);

        Response response = ApiRequestManager.sendPostRequest(payload);

        ApiResponseManager.validateSuccessResponse(response);
        ApiResponseManager.validateTotalJobCount(response, 0);
    }

    @Test
    @Description("Scenario for sending valid pagination parameters")
    @Severity(SeverityLevel.NORMAL)
    public void ValidPagination() {
        // Create a payload with valid pagination parameters
        RequestPayload payload = new RequestPayload();
        payload.setMemberId(1);
        payload.setSize(50);
        payload.setCurrentPage(2);  // Fetching jobs from the second page
        payload.setKeyword("Software Test Engineer");
        payload.setLanguage(Arrays.asList(1));
        payload.setDontShowAppliedJobs(false);

        Response response = ApiRequestManager.sendPostRequest(payload);

        ApiResponseManager.validateSuccessResponse(response);
        ApiResponseManager.validateCurrentPage(response, 2);
    }

    @Test
    @Description("Scenario for sending a max size parameter to check boundary validation")
    @Severity(SeverityLevel.NORMAL)
    public void MaxSizeParameter() {
        // Create a payload with the maximum allowed size value
        RequestPayload payload = new RequestPayload();
        payload.setMemberId(1);
        payload.setSize(Integer.MAX_VALUE);  // Exceeding maximum allowed size
        payload.setCurrentPage(1);
        payload.setKeyword("Software Test Engineer");
        payload.setLanguage(Arrays.asList(1, 2));
        payload.setDontShowAppliedJobs(false);

        Response response = ApiRequestManager.sendPostRequest(payload);

        ApiResponseManager.validateErrorResponseWithMessage(response, 400, "Size parametresi 0 ile 10000 arasında olmalıdır!");
    }

    @Test
    @Description("Scenario for sending a max currentPage parameter to check boundary validation")
    @Severity(SeverityLevel.NORMAL)
    public void MaxCurrentPageParameter() {
        // Create a payload with the maximum allowed currentPage value
        RequestPayload payload = new RequestPayload();
        payload.setMemberId(1);
        payload.setSize(1);
        payload.setCurrentPage(Integer.MAX_VALUE);  // Exceeding maximum allowed page number
        payload.setKeyword("Software Test Engineer");
        payload.setLanguage(Arrays.asList(1, 2));
        payload.setDontShowAppliedJobs(false);

        Response response = ApiRequestManager.sendPostRequest(payload);

        ApiResponseManager.validateErrorResponseWithMessage(response, 400, "CurrentPage parametresi 0 ile 10000 arasında olmalıdır!");
    }
}
