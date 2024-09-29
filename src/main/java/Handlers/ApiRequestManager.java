package Handlers;

import Config.ConfigManager;
import Helpers.JSONObjectBuilder;
import Models.RequestPayload;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

public class ApiRequestManager {

    // Method to send a POST request to the API with the provided payload
    public static Response sendPostRequest(RequestPayload payload) {
        // Fetch the base URL from the properties file
        String url = ConfigManager.getProperty("base.url");

        // Build the JSON object with the necessary parameters from the payload
        JSONObjectBuilder jsonObjectBuilder = new JSONObjectBuilder()
                .addParameter("memberId", payload.getMemberId())
                .addParameter("size", payload.getSize())
                .addParameter("currentPage", payload.getCurrentPage())
                .addParameter("keyword", payload.getKeyword())
                .addParameter("language", payload.getLanguage())
                .addParameter("dontShowAppliedJobs", payload.isDontShowAppliedJobs());

        // Convert the JSON object to a string
        String jsonRequest = jsonObjectBuilder.build();

        // Send the POST request and return the response
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonRequest)
                .post(url);
    }
}
