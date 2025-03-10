package org.globant.automation.tests;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.globant.automation.config.TestRunner;
import org.globant.automation.model.PetResponseDTO;
import org.globant.automation.request.RequestBuilder;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Slf4j
public class GetPetInfoTest extends TestRunner {

    @DataProvider(name="Valid pet ids")
    public Object[][] loginUserData(){
        return new Object[][] {
                {"1"},
                {"2"},
                {"3"},
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
}
