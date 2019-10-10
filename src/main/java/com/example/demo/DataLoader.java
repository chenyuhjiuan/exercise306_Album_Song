package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    AlbumRepository albumRepository;
    @Override

    public void run(String... strings) throws Exception {
        Album album;

        album= new Album("Album1",  "Company1");
        albumRepository.save(album);

        album= new Album("Album2",  "Company2");
        albumRepository.save(album);
        album= new Album("Album3",  "Company3");
        albumRepository.save(album);

    }
}
