package com.lihuajian.configer;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainErrorController implements ErrorController {

    private static final String errorUrl="/error";

    @RequestMapping(value=errorUrl)
    public String handleError(){
        return "404";
    }

    @Override
    public String getErrorPath() {
        return errorUrl;
    }
}
