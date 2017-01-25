package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tbradfute on 1/24/17.
 */
@RestController
@RequestMapping("/widget")
public class WidgetController {

    @Autowired
    private WidgetRepository repository;

    // @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Widget> index() {
        //return new Widget("Green", 10, 7);
        List<Widget> l = new ArrayList<Widget>();
        for (Widget w: repository.findAll()) {
            l.add(w);
        }
        return l;
    }

    @RequestMapping(value="/color/{color}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Widget setWidgetColor(@PathVariable String color) {
        return repository.save(new Widget(color, 10, 7));
    }

    @RequestMapping(value = "/position", method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Widget setWidgetAll(@RequestBody Widget w) {
        return repository.save(w);
    }
}
