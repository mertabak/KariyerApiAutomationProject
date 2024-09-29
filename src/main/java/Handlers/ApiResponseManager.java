package Handlers;

import io.restassured.response.Response;
import org.hamcrest.Matchers;

public class ApiResponseManager {

    // Method to validate that the response was successful
    public static void validateSuccessResponse(Response response) {
        response.then()
                .statusCode(200)
                .body("statusCode", Matchers.equalTo("Success"))
                .body("status", Matchers.containsString("Success"))
                .log().all();
    }

    // Method to validate that an error occurred with the expected message
    public static void validateErrorResponseWithMessage(Response response, int expectedStatusCode, String expectedErrorMessage) {
        response.then()
                .statusCode(expectedStatusCode)
                .body("message", Matchers.containsString(expectedErrorMessage))
                .log().all();
    }

    // Method to validate the current page in the response
    public static void validateCurrentPage(Response response, int expectedPage) {
        response.then()
                .body("data.currentPage", Matchers.equalTo(expectedPage))
                .log().all();
    }

    // Method to validate the total number of jobs returned in the response
    public static void validateTotalJobCount(Response response, int expectedCount) {
        response.then()
                .body("data.totalJobCount", Matchers.equalTo(expectedCount))
                .log().all();
    }

    // Method to validate that the total job count is greater than the expected minimum
    public static void validateTotalJobCountGreaterThan(Response response, int expectedMinimum) {
        response.then()
                .body("data.totalJobCount", Matchers.greaterThan(expectedMinimum))
                .log().all();
    }

    // Method to validate whether applied jobs are displayed or not
    public static void validateAppliedJobs(Response response, boolean expectedValue) {
        if (expectedValue) {
            response.then()
                    .body("data.appliedJobs", Matchers.nullValue())
                    .log().all();
        } else {
            response.then()
                    .body("data.appliedJobs", Matchers.notNullValue())
                    .log().all();
        }
    }
}
