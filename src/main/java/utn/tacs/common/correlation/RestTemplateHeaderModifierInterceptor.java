package utn.tacs.common.correlation;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class RestTemplateHeaderModifierInterceptor implements HandlerInterceptor {

    private final String key = "x-request-id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            String value = request.getHeader(key);
            response.setHeader(key,value == null ? UUID.randomUUID().toString() : value);
        }
        return true;
    }


}
