package utn.tacs.common.correlation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class RestHeaderInterceptor implements HandlerInterceptor {

    private final String key = "x-request-id";

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logRequest(request);
        logResponse(response);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logRequest(request);
        logResponse(response);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            String value = request.getHeader(key);
            response.setHeader(key,value == null ? UUID.randomUUID().toString() : value);
        }
        return true;
    }


    private void logRequest(HttpServletRequest request) throws IOException {

        if (log.isDebugEnabled()) {
            String xRequestId = request.getHeader(key);
            log.debug("===========================request begin================================================");
            log.debug("URI              : {}", request.getRequestURI());
            log.debug("Method           : {}", request.getMethod());
            log.debug("x-request-id     : {}", xRequestId != null ? xRequestId : "Node");
            log.debug("==========================request end================================================");
        }
    }

    private void logResponse(HttpServletResponse response) throws IOException {

        if (log.isDebugEnabled()) {
            log.debug("============================response begin==========================================");
            log.debug("Status code  : {}", response.getStatus());
            log.debug("x-request-id : {}", response.getHeader(key));
            log.debug("=======================response end=================================================");
        }
    }


}
