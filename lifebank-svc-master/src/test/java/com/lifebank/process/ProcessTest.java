package com.lifebank.process;

import com.lifebank.component.JWT.JWTManager;
import com.lifebank.component.validator.loginValidator.ILoginValidator;
import com.lifebank.component.validator.validationChain.IValidation;
import com.lifebank.factory.responseFactory.LoginResponseFactory;
import com.lifebank.pojo.request.login.LoginRequest;
import com.lifebank.pojo.request.validator.ValidationData;
import com.lifebank.pojo.response.LoginResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessTest {
        /*
        ===============================================
        UNIT TEST
        ===============================================
        */

        @Test
        public void loginProcessTest() throws Exception{
            //////Dependencias//////
            LoginRequest req = new LoginRequest("jacastellanos","Lifemiles10");
            ILoginValidator loginValidator = Mockito.mock(ILoginValidator.class);
            LoginResponseFactory responseFactory = new LoginResponseFactory(new JWTManager());

            LoginProcess process = new LoginProcess(loginValidator, responseFactory);

            //////Mocking dependencies//////
            Mockito.when(loginValidator.validate(req)).thenReturn(new ValidationData(IValidation.SUCCESS));

            //////Test execution//////
            ResponseEntity<?> httpResponse = process.process(req);

            //////IValidation//////
            Assert.assertEquals(HttpStatus.OK,httpResponse.getStatusCode());
            Assert.assertFalse(((LoginResponse)httpResponse.getBody()).getJwt().isEmpty());
        }

}
