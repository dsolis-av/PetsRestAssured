package org.globant.automation.tests;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.globant.automation.config.TestRunner;
import org.globant.automation.model.PetResponseDTO;
import org.globant.automation.request.RequestBuilder;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

@Slf4j
public class GetAvailablePetsTest extends TestRunner {

    //Test to get available pets
    @Test(testName = "Get available pets")
    public void getAvailablePetsTest(){
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/pet/findByStatus?status=available");
        PetResponseDTO[] availablePetsArray = response.as(PetResponseDTO[].class);
        assertEquals(response.statusCode(), 200, "Status code doesn't match");
        log.info("Amount of available pets: " + availablePetsArray.length);
    }
}
