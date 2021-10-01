package io.github.jasonheo;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;

@QuarkusTest
public class InjectMockTest {

    @InjectMock
    EmailService emailService;

    @InjectMock
    DbService dbService;

    @BeforeEach
    public void setup() {
        doNothing().when(emailService).sendEmail(Mockito.anyString());

        // case 1: argument와 상관없이 항상 동일한 값을 return
        // Mockito.when(dbService.getName(Mockito.anyString())).thenReturn("my id");

        // case 2: argument에 의존적인 값을 return
        Mockito.when(dbService.getName(Mockito.anyString())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();

                return "name of id '" + args[0] + "'";
            }
        });
    }

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/hello/id/{id}", 1)
                .then()
                .statusCode(200)
                .body(is("Hello name of id '1'"));
    }
}
