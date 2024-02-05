package com.example.springexample.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
@Setter
@Getter
@AllArgsConstructor

public class NewsDto {

    private Long id;
    private String title;
    private String text;
    private Instant date;

    @Override
    public String toString() {
        return "NewsDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }

}
