package com.example.springexample.services;
import com.example.springexample.dto.NewsDto;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.TreeMap;


@Service
public class NewsCRUDService implements CrudService<NewsDto> {

    private final TreeMap<Long, NewsDto> storage = new TreeMap<>();


    @Override
    public NewsDto getById(Long id) {
        if (!storage.containsKey(id)) {
            System.out.println("Новость с ID " + id + " не найдена.");
        }
        System.out.println("Get by id: " + id);
        return storage.get(id);
    }


    @Override
    public Collection<NewsDto> getAll() {
        System.out.println("Get all");
        return storage.values();
    }


    @Override

    public void create(NewsDto item) {
        System.out.println("Create" );
        Long nextId = (storage.isEmpty() ? 0 : storage.lastKey()) + 1;
        item.setId(nextId);
        storage.put(nextId, item);
    }


    @Override

    public void update(Long id, NewsDto item) {
        System.out.println("Update: " + item);
        if (!storage.containsKey(id)) {
            return;
        }
        item.setId(id);
        storage.put(id, item);
    }


    @Override
    public void delete(Long id) {
        System.out.println("Delete: " + id);
        storage.remove(id);

    }
}
