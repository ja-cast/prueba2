package com.lifebank.controller;


import com.lifebank.pojo.request.login.LoginRequest;
import com.lifebank.pojo.response.LoginResponse;
import com.lifebank.process.ILoginProcess;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class LoginTest {
    private MockMvc mockMvc;

    @Autowired
    private LoginController loginController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();

    }

    /*
    ===============================================
    UNIT TEST
    ===============================================
    */
    @Test
    public void loginControllerTest() throws Exception{

        //Dependencies
        LoginResponse res = new LoginResponse("aidfjioasjfdiajsodjdisajdfoasojosf");
        LoginRequest req = new LoginRequest("jacastellanos","Lifemiles10");

        ILoginProcess process = Mockito.mock(ILoginProcess.class);

        LoginController controller = new LoginController(process);

        //Mocking dependencies
        ResponseEntity<LoginResponse> resEntity = new ResponseEntity<LoginResponse>(res, HttpStatus.OK);
        Mockito.when(process.process(req)).thenReturn(resEntity);

        //Test execution
        ResponseEntity<?> httpResponse = controller.login(req);

        //IValidation
        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertFalse(((LoginResponse)httpResponse.getBody()).getJwt().isEmpty());
    }

    /*
    ===============================================
    INTEGRATION TEST
    ===============================================
    */
    @Test
    public void loginTestSuccess() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"user\" : \"jacastellanos\", \"password\" : \"Lifemiles10\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("jwt").exists())
        ;
    }

    @Test
    public void loginTestBlocked() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"user\" : \"icastellanos\", \"password\" : \"Lifemiles10\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("jwt").doesNotExist());
    }

    @Test
    public void loginTestInvalidPwd() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"user\" : \"jacastellanos\", \"password\" : \"wrongpwd\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.jsonPath("jwt").doesNotExist());
    }



}
