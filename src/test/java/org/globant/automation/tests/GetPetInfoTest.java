package org.globant.automation.tests;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.globant.automation.config.TestRunner;
import org.globant.automation.model.PetResponseDTO;
import org.globant.automation.model.UserResponseDTO;
import org.globant.automation.request.RequestBuilder;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Slf4j
public class GetPetInfoTest extends TestRunner {

    @DataProvider(name="Valid pet ids")
    public Object[][] validPetIdsData(){
        return new Object[][] {
                {"1"},
                {"2"},
                {"3"},
        };
    }

    @DataProvider(name="Invalid pet ids")
    public Object[][] invalidPetIdsData(){
        return new Object[][] {
                {"-2"},
                {"-10"},
                {"a"},
        };
    }

    @Test(testName = "Query pet details", dataProvider = "Valid pet ids")
    public void getPetDetailsTest(String petId){
        Response petResponse = RequestBuilder.getRequest(getBaseUrl(), "/pet/" + petId);
        PetResponseDTO petResponseDTO = petResponse.as(PetResponseDTO.class);
        assertEquals(petResponse.statusCode(), 200, "Status code doesn't match");
        log.info(petResponseDTO.getName());
        log.info(petResponseDTO.getStatus());
    }

    @Test(testName = "Query pet details with invalid id", dataProvider = "Invalid pet ids")
    public void getInvalidPetDetailsTest(String petId){
        Response petResponse = RequestBuilder.getRequest(getBaseUrl(), "/pet/" + petId);
        UserResponseDTO invalidResponseDTO = petResponse.as(UserResponseDTO.class);
        assertEquals(petResponse.statusCode(), 404, "Status code doesn't match");
        log.info(invalidResponseDTO.getMessage());
    }
}
