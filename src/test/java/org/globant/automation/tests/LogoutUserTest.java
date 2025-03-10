package org.globant.automation.tests;

import io.restassured.response.Response;
import org.globant.automation.config.TestRunner;
import org.globant.automation.model.UserResponseDTO;
import org.globant.automation.request.RequestBuilder;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.globant.automation.tests.CreateUserTest.createUserRequest;
import static org.testng.Assert.assertEquals;

public class LogoutUserTest extends TestRunner {

    @DataProvider(name="User creation for logout")
    public Object[][] loginUserData(){
        return new Object[][] {
                {
                        571,"userlogout1","Logout","User1","userlogout1@gmail.com","12345234","91283918",0
                },
                {
                        617,"userlogout2","Logout","User2","userlogout2@gmail.com","12345234","91283918",0
                },
                {
                        23,"userlogout3","Logout","User3","userlogout3@gmail.com","12345234","91283918",0
                },
        };
    }

    @Test(testName = "Validate logout with existing user", dataProvider = "User creation for logout")
    public void logoutUserTest(int id, String username, String firstName, String lastName, String email, String password, String phone, int status){
        //First we have to create the user
        UserResponseDTO createUserResponseDTO = createUserRequest(id, username, firstName, lastName, email, password, phone, status);
        //Now we're ready to log the user in
        String loginPath = "/user/login?" + username + "&password=" + password;
        Response loginResponse = RequestBuilder.getRequest(getBaseUrl(), loginPath);
        UserResponseDTO loginUserResponseDTO = loginResponse.as(UserResponseDTO.class);
        //Now we're ready to log the user out
        Response logoutResponse = RequestBuilder.getRequest(getBaseUrl(), "/user/logout");
        UserResponseDTO logoutUserResponseDTO = logoutResponse.as(UserResponseDTO.class);
        assertEquals(logoutUserResponseDTO.getCode(), 200, "Status code doesn't match");
    }
}
