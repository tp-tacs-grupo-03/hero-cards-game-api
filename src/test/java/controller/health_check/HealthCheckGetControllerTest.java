package controller.health_check;

import controller.RequestTestCase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import utn.tacs.TacsApplication;

@SpringBootTest(classes = {TacsApplication.class})
final class HealthCheckGetControllerTest extends RequestTestCase {

    @Test
    void test_health_check_working() throws Exception{
        assertRequestWithContent("GET","/health-check",200, "{'status':'ok'}");
    }
}
