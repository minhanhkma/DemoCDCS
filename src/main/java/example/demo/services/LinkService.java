package example.demo.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.demo.entities.Link;
import example.demo.repositories.LinkRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LinkService {
    @Autowired
    private LinkRepository linkRepository;

    public List<Link> getAllLinks() {
        return linkRepository.findAll();
    }

    public Link createLink(String url) {
        Link link = new Link();
        link.setUrl(url);
        link.setCreatedAt(LocalDateTime.now());
        return linkRepository.save(link);
    }
}

