package com.movies.MoviesBackend.controller;


import com.movies.MoviesBackend.entities.Actor;
import com.movies.MoviesBackend.enums.ImageType;
import com.movies.MoviesBackend.service.ActorService;
import com.movies.MoviesBackend.utility.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping("/add")
    public ResponseEntity<String> addActor(@RequestBody Actor actor) {
        actorService.saveActor(actor);
        return new ResponseEntity<>("Actor added successfully", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Actor>> getAllActors() {
        List<Actor> actors = actorService.getActors();
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @GetMapping("/{actorId}")
    public ResponseEntity<Actor> getActorById(@PathVariable String actorId) throws Exception {
        Actor actor = actorService.getActorById(actorId);
        if (actor != null) {
            return new ResponseEntity<>(actor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{actorId}")
    public ResponseEntity<String> deleteActor(@PathVariable String actorId) {
        actorService.deleteActor(actorId);
        return new ResponseEntity<>("Actor deleted successfully", HttpStatus.OK);
    }














//    @RequestMapping("/")
//    public String index(Model model) {
//        model.addAttribute("actors", actorService.getActors());
//        return "admin/movie/actor/actors";
//    }
//
//    @RequestMapping(value = "/create", method = RequestMethod.GET)
//    public String createActorForm(Model model) {
//        //model.addAttribute("actorForm", new Actor());
//        return "admin/movie/actor/actor_form";
//    }
//
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public String createActor(@Validated @ModelAttribute("actorForm") Actor actor, BindingResult result,
//                              @RequestParam(value = "file") MultipartFile file, RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            redirectAttributes.addFlashAttribute(actor);
//            return "admin/movie/actor/actor_form";
//        }
//        try {
//            String path = FileUpload.saveImage(ImageType.CAST_DP, actor.getName(), file);
//            actor.setImageUrl(path);
//            actorService.saveActor(actor);
//
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute(actor);
//            return "admin/movie/actor/actor_form";
//        }
//        return "redirect:/admin/actors/";
//    }
//
//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//    public String updateActorForm(@PathVariable("id") String id, Model model) {
//        try {
//            Actor actor = actorService.getActorById(id);
//            model.addAttribute("actorForm", actor);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "admin/movie/actor/actor_form";
//    }
//
//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
//    public String updateActor(@PathVariable("id") String id, @Validated @ModelAttribute("actorForm") Actor actor,
//                              BindingResult result, Model model, @RequestParam(value = "file") MultipartFile file,
//                              RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            actor.setId(id);
//            redirectAttributes.addFlashAttribute(actor);
//            return "admin/movie/actor/actor_form";
//        }
//
//        try {
//            if (!file.isEmpty()) {
//                String path = FileUpload.saveImage(ImageType.CAST_DP, actor.getName(), file);
//                actor.setImageUrl(path);
//            } else {
//                actor.setImageUrl(actorService.getActorById(id).getImageUrl());
//            }
//            actorService.saveActor(actor);
//
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute(actor);
//            return "admin/movie/actor/actor_form";
//        }
//        return "redirect:/admin/actors/";
//    }
//
//    @RequestMapping(value = "/delete/{id}")
//    public String deleteActor(@PathVariable("id") String id, RedirectAttributes redirAttr) {
//        try {
//            Actor actor = actorService.getActorById(id);
//           // actorService.deleteActor(actor);
//        } catch (Exception e) {
//            redirAttr.addFlashAttribute("error", "Unable to delete cast assigned in a movie.");
//            return "redirect:/admin/actors/";
//        }
//        return "redirect:/admin/actors/";
//    }

}
