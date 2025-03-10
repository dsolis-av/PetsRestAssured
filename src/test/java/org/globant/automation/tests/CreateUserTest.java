package org.globant.automation.tests;
import io.restassured.response.Response;
import org.globant.automation.config.TestRunner;
import org.globant.automation.model.CreateUserDTO;
import org.globant.automation.model.UserResponseDTO;
import org.globant.automation.request.RequestBuilder;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;


public class CreateUserTest extends TestRunner {

    @DataProvider(name="User creation")
    public Object[][] createUserData(){
        return new Object[][] {
                {
                    198,"testuser19","Test","User","testuser@gmail.com","12345234","91283918",0
                },
                {
                    231,"anotheruser","Mario","Pérez","marioperez@gmail.com","12345234","91283918",0
                },
                {
                    939,"thirduser","John","Wick","johnwick@gmail.com","12345234","91283918",0
                },
        };
    }

    public static UserResponseDTO createUserRequest(int id, String username, String firstName, String lastName, String email, String password, String phone, int status){
        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .id(id)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .phone(phone)
                .userStatus(status)
                .build();
        Response response = RequestBuilder.postRequest(getBaseUrl(), "/user", createUserDTO);
        UserResponseDTO createUserResponseDTO = response.as(UserResponseDTO.class);
        return createUserResponseDTO;
    }

    @Test(testName = "Validate creation of a user", dataProvider = "User creation")
    public void createUserTestAssertion(int id, String username, String firstName, String lastName, String email, String password, String phone, int status){

        UserResponseDTO createUserResponseDTO = createUserRequest(id, username, firstName, lastName, email, password, phone, status);
        assertEquals(createUserResponseDTO.getCode(), 200, "Status code doesn't match");

    }
}
