package com.example.cleanmaster.RestApi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeguridadRestApiController {

    @PostMapping("/api/viewpage")
    public String viewPage(@RequestBody String[] page) {
        return page.toString();
    }
}
