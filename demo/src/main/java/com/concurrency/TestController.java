package com.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/test")
public class TestController {

    //@RequestMapping(value = "/test",method = RequestMethod.GET)
    //@PostMapping
    //@GetMapping
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "test";
    }
}
