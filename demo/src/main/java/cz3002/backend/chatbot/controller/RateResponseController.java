package cz3002.backend.chatbot.controller;

import cz3002.backend.chatbot.pojo.RateResponse;
import cz3002.backend.chatbot.service.RateResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Rate Response")
@RestController
@RequestMapping(value = "RateResponse")
public class RateResponseController
{
    @Autowired
    RateResponseService rateResponseService;

    @ApiOperation(value = "api Rate Response")
    @PostMapping("/rate")

    public Object editProfile(@RequestBody RateResponse rateResponse) throws Exception {
        var message = rateResponseService.save(rateResponse);
        return new ResponseEntity(message, HttpStatus.OK);
    }

}
