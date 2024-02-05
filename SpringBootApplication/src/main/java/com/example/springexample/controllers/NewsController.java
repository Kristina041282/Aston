package com.example.springexample.controllers;
import com.example.springexample.dto.NewsDto;
import com.example.springexample.services.NewsCRUDService;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;


@RestController
@RequestMapping("/comment")
public class NewsController {

    private final NewsCRUDService newsService;

    public NewsController(NewsCRUDService newsService) {//для внедрения зависимостей (DI) создали конструктор и передали ему класс NewsCRUDService
        this.newsService = newsService;
    }


    @GetMapping("/{id}")
    public NewsDto getNewsById(@PathVariable Long id) {
        return newsService.getById(id);
    }


    @GetMapping
    public Collection<NewsDto> getAllNews() {
        return newsService.getAll();
    }


    @PostMapping
    public void createNews(@RequestBody NewsDto newsDto) {
        newsService.create(newsDto);//получив в параметры объект newsDto мы можем здесь
    }


    @PutMapping("/{id}")
    public void updateNewsDto(@PathVariable Long id, @RequestBody NewsDto newsDto) {

        newsService.update(id, newsDto);
    }


    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable Long id) {
        newsService.delete(id);

    }
}
