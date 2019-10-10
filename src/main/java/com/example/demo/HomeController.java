package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    AlbumRepository albumRepository;

    @Autowired
     SongRepository songRepository;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("albums", albumRepository.findAll());
        model.addAttribute("songs", songRepository.findAll());

        return "index";
    }



    @GetMapping("/addalbum")

    public String albumForm(Model model){
        model.addAttribute("album", new Album());
        return "albumform";
    }


    @PostMapping("/processalbum")
    public String processAlbumForm(@Valid Album album, BindingResult result){

        if (result.hasErrors()){
            return "albumform"; }

        albumRepository.save(album);

        return "redirect:/albumlist";
    }


    @RequestMapping("/albumlist")

    public String albumList(Model model){

        model.addAttribute("albums", albumRepository.findAll());

        return "albumlist";
    }


    @GetMapping("/addsong")

    public String songForm(Model model){

        model.addAttribute("albums", albumRepository.findAll());

        model.addAttribute("song", new Song());

        return "songform";
    }


    @PostMapping("/processSong")

    public String processSongForm(@Valid Song song, BindingResult result){

        if (result.hasErrors()){

            return "songform";
        }
        songRepository.save(song);

        return "redirect:/songlist";
    }


    @RequestMapping("/songlist")

    public String songList(Model model){

        model.addAttribute("songs", songRepository.findAll());

        return "songlist";
    }


    @RequestMapping("/detail_album/{id}")

    public String showAlbum(@PathVariable("id") long id, Model model){

        model.addAttribute("album", albumRepository.findById(id).get());
        return "showalbum";
    }


    @RequestMapping("/update_album/{id}")

    public String updateAlbum(@PathVariable("id") long id, Model model){

        model.addAttribute("album", albumRepository.findById(id).get());

        return "albumform";
    }


    @RequestMapping("/delete_album/{id}")

    public String delAlbum(@PathVariable("id") long id){

        albumRepository.deleteById(id);
        return "redirect:/";
    }


    @RequestMapping("/detail_song/{id}")

    public String showSong(@PathVariable("id") long id, Model model){

        model.addAttribute("song", songRepository.findById(id).get());

        return "showsong";

    }


    @RequestMapping("/update_song/{id}")

    public String updateSong(@PathVariable("id") long id, Model model){

        model.addAttribute("song", songRepository.findById(id).get());

        model.addAttribute("albums",albumRepository.findAll());

        return "songform";

    }



    @RequestMapping("/delete_song/{id}")

    public String delSong(@PathVariable("id") long id){

        songRepository.deleteById(id);

        return "redirect:/";

    }
}
