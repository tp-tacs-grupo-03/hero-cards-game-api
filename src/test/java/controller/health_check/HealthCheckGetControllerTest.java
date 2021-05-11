package controller.health_check;

import controller.RequestTestCase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import utn.tacs.TacsApplication;

@SpringBootTest(classes = {TacsApplication.class})
final class HealthCheckGetControllerTest extends RequestTestCase {

    @Test
    void testHealthCheckWorking() throws Exception{
        assertRequestWithContent("GET","/actuator/health",200, "{'status':'UP'}");
    }
}
