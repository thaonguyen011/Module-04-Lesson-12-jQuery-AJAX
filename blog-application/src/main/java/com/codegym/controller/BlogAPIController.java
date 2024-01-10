package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Catalog;
import com.codegym.model.PageCount;
import com.codegym.service.IBlogService;
import com.codegym.service.ICatalogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/blogs")
public class BlogAPIController {
    private final IBlogService blogService;
    private final ICatalogService catalogService;

    public BlogAPIController(IBlogService blogService, ICatalogService catalogService) {
        this.blogService = blogService;
        this.catalogService = catalogService;
    }

    @ModelAttribute("catalogs")
    private Iterable<Catalog> catalog() {
        return catalogService.findAll();
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Blog>> findAllBlog() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Blog> blogs = blogService.findAll(pageable);
        if (blogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogs, HttpStatus.OK);
//        Iterable<Blog> blogs = blogService.findAll();
//        return new ResponseEntity<>(blogs, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> findBlogById(@PathVariable("id") Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);

        return blogOptional.map(blog -> new ResponseEntity<>(blog, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Blog>> searchBlog(@RequestParam("word") String word) {
        Iterable<Blog> blogs = blogService.findAllContainingTitleOrContainingAuthor(word, word);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/load")
    public ResponseEntity<Iterable<Blog>> load() {
        Pageable pageable = PageRequest.of(0, PageCount.count());
        System.out.println( PageCount.count());
        Page<Blog> blogs = blogService.findAll(pageable);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }
}
