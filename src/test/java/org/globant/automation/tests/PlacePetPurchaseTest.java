package org.globant.automation.tests;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.globant.automation.config.TestRunner;
import org.globant.automation.model.PetPurchaseDTO;
import org.globant.automation.request.RequestBuilder;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Slf4j
public class PlacePetPurchaseTest extends TestRunner {

    //First we define our data providers to execute multiple tests
    @DataProvider(name="Pet purchase orders")
    public Object[][] createUserData(){
        return new Object[][] {
                {
                        200,1,3,"2025-10-10T00:37:35.196Z","placed",true
                },
                {
                        212,6,5,"2030-03-10T00:39:35.196Z","pending",false
                },
                {
                        200,10,10,"2032-22-10T00:31:35.196Z","invalid",false
                },
        };
    }

    //Place a pet purchase order
    @Test(testName = "Place a pet purchase order", dataProvider = "Pet purchase orders")
    public void DplacePetPurchaseTest(int id, int petId, int quantity, String shipDate, String status, boolean complete){
        PetPurchaseDTO petPurchaseDTO = PetPurchaseDTO.builder()
                .id(id)
                .petId(petId)
                .quantity(quantity)
                .shipDate(shipDate)
                .status(status)
                .complete(complete)
                .build();

        Response response = RequestBuilder.postRequest(getBaseUrl(), "/store/order", petPurchaseDTO);
        PetPurchaseDTO petPurchaseResponseDTO = response.as(PetPurchaseDTO.class);
        assertEquals(response.statusCode(), 200, "Status code doesn´t match");
        log.info(petPurchaseResponseDTO.getStatus());
    }
}
