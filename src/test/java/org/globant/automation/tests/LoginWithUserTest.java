package org.globant.automation.tests;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.globant.automation.config.TestRunner;
import org.globant.automation.model.UserResponseDTO;
import org.globant.automation.request.RequestBuilder;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.globant.automation.tests.CreateUserTest.createUserRequest;

import static org.testng.Assert.assertEquals;

@Slf4j
public class LoginWithUserTest extends TestRunner {

    //First we define our data providers to execute multiple tests
    @DataProvider(name="User creation for login")
    public Object[][] loginUserData(){
        return new Object[][] {
                {
                        191,"userlogin1","Login","User1","userlogin1@gmail.com","12345234","91283918",0
                },
                {
                        3993,"userlogin2","Login","User2","userlogin2@gmail.com","12345234","91283918",0
                },
                {
                        81,"userlogin3","Login","User3","userlogin3@gmail.com","12345234","91283918",0
                },
        };
    }

    //Validate login with existing user
    @Test(testName = "Validate login with existing user", dataProvider = "User creation for login")
    public void loginWithUserTest(int id, String username, String firstName, String lastName, String email, String password, String phone, int status){
        //First we have to create the user by sending the appropiate request
        UserResponseDTO createUserResponseDTO = createUserRequest(id, username, firstName, lastName, email, password, phone, status);
        //Now we're ready to log the user in
        String loginPath = "/user/login?" + username + "&password=" + password;
        Response loginResponse = RequestBuilder.getRequest(getBaseUrl(), loginPath);
        UserResponseDTO loginUserResponseDTO = loginResponse.as(UserResponseDTO.class);
        assertEquals(loginUserResponseDTO.getCode(), 200, "Status code doesn't match");
        log.info(loginUserResponseDTO.getMessage());
    }
}
