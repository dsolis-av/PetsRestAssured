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

    //First we define our data providers to execute multiple tests
    @DataProvider(name="User creation")
    public Object[][] createUserData(){
        return new Object[][] {
                {
                    198,"testuser19","Test","User","testuser@gmail.com","12345234","91283918",0
                },
                {
                    231,"anotheruser","Mario","Pérez","marioperez@gmail.com","jwhd3aaL","323828",0
                },
                {
                    939,"thirduser","John","Wick","johnwick@gmail.com","kaUaslM","277272",0
                },
        };
    }

    @DataProvider(name="User creation with malformed data")
    public Object[][] createUserMalformedData(){
        return new Object[][]{
                {
                        191,"sauhdua","Login","User1","___@___.com","12345234","91283918",0
                },
                {
                        3993,"asdkljak","Login sjadkj","User malformed test","userlogin2_gmail.com","$%&A(a1","91283918",0
                },
                {
                        81,"userlogsaksaj","Login","malformed user","userlogin3_gmail.com ñ","ñ-1!-.s","91283918",0
                },
        };
    }

    //Reusable method to send a post request to create a user
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

    //Creation of a user with VALID data
    @Test(testName = "Validate creation of a user", dataProvider = "User creation")
    public void createUserTest(int id, String username, String firstName, String lastName, String email, String password, String phone, int status){

        UserResponseDTO createUserResponseDTO = createUserRequest(id, username, firstName, lastName, email, password, phone, status);
        assertEquals(createUserResponseDTO.getCode(), 200, "Status code doesn't match. User creation failed.");
    }

    //Creation of a user with INVALID data
    @Test(testName = "Reject creation of malformed user", dataProvider = "User creation with malformed data")
    public void createInvalidUserTest(int id, String username, String firstName, String lastName, String email, String password, String phone, int status){

        UserResponseDTO createUserResponseDTO = createUserRequest(id, username, firstName, lastName, email, password, phone, status);
        assertEquals(createUserResponseDTO.getCode(), 400, "Status code doesn't match. User creation should be rejected.");
    }
}
