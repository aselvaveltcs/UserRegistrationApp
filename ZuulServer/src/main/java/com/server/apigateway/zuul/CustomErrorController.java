package com.server.apigateway.zuul;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomErrorController implements ErrorController {

    //@RequestMapping(value = "/error", produces = "application/json")
	@RequestMapping(value = "/error" )
    public @ResponseBody
    ResponseEntity error(HttpServletRequest request) {
        // consider putting these in a try catch
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        Throwable exception = (Throwable)request.getAttribute("javax.servlet.error.exception");

        // maybe add some error logging here, e.g. original status code, exception, traceid, etc.

        // consider a better error to the user here
        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(exception);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}