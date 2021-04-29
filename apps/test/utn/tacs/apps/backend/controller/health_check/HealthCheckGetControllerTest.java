package utn.tacs.apps.backend.controller.health_check;

import org.junit.jupiter.api.Test;
import utn.tacs.apps.backend.controller.RequestTestCase;
import org.springframework.boot.test.context.SpringBootTest;
import utn.tacs.apps.TacsApplication;

@SpringBootTest(classes = {TacsApplication.class})
final class HealthCheckGetControllerTest extends RequestTestCase {

    @Test
    void test_health_check_working() throws Exception{
        assertResponse("/health-check",200, "{'status':'ok'}");
    }
}