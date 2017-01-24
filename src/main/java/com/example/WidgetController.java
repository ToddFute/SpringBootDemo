package com.example;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tbradfute on 1/24/17.
 */
@RestController
@RequestMapping("/widget")
public class WidgetController {
    // @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Widget index() {
        return new Widget("Green", 10, 7);
    }

    @RequestMapping(value="/color/{color}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Widget setWidgetColor(@PathVariable String color) {
        return new Widget(color, 10, 7);
    }

}
