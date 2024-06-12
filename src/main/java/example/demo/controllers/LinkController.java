package example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import example.demo.entities.Link;
import example.demo.services.LinkService;

import java.util.List;

@RestController
@RequestMapping("/api/links")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping
    public List<Link> getAllLinks() {
        return linkService.getAllLinks();
    }

    @PostMapping
    public Link createLink(@RequestParam String url) {
        return linkService.createLink(url);
    }
}


