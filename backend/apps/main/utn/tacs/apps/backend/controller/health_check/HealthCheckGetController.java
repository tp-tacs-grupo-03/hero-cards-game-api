package utn.tacs.apps.backend.controller.health_check;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Api(tags = "Health")
public class HealthCheckGetController {

    @GetMapping("/health-check")
    public HashMap<String, String> handle(){
        HashMap<String, String> status = new HashMap<>();
        status.put("status", "ok");
        return status;
    }

}
