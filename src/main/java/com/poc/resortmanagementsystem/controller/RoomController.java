package com.poc.resortmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.poc.resortmanagementsystem.entity.Resort;
import com.poc.resortmanagementsystem.entity.Room;
import com.poc.resortmanagementsystem.repository.ResortRepository;
import com.poc.resortmanagementsystem.repository.RoomRepository;

import lombok.AllArgsConstructor;

@Controller

public class RoomController {
	
	@Autowired
	private ResortRepository resortRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	
	
//	public RoomController(ResortRepository resortRepository, RoomRepository roomRepository) {
//		
//		this.resortRepository = resortRepository;
//		this.roomRepository = roomRepository;
//	}

	@GetMapping("/resort/{resortId}/rooms")
    public String getRoomPage(@PathVariable Long resortId, Model model) {
        Resort resort = resortRepository.findById(resortId).get();
        model.addAttribute("resort", resort);
        return "add-room-form";
    }
	
	@PostMapping("/resort/{resortId}/rooms")
    public String addRoom(@PathVariable Long resortId, @ModelAttribute Room room) {
		Resort resort = resortRepository.findById(resortId).get();
        room.setResort(resort);
        roomRepository.save(room);
        return "redirect:/resort/{resortId}/rooms";
    }
	
	@GetMapping("/resort/{resortId}/roomlist")
    public ModelAndView showRoomList(@PathVariable("resortId") Long resortId) {
        ModelAndView mav = new ModelAndView("list-rooms");
        Resort resort = resortRepository.findById(resortId).orElse(null);
        if (resort != null) {
            List<Room> rooms = roomRepository.findByResort(resort);
            mav.addObject("resort", resort);
            mav.addObject("rooms", rooms);
        }
        return mav;
    }

    @GetMapping("/resort/{resortId}/rooms/{roomId}/edit")
    public String editRoomForm(@PathVariable Long resortId, @PathVariable Long roomId, Model model) {
        Resort resort = resortRepository.findById(resortId).orElse(null);
        Room room = roomRepository.findById(roomId).orElse(null);

        if (resort != null && room != null && room.getResort().getId().equals(resort.getId())) {
            model.addAttribute("resort", resort);
            model.addAttribute("room", room);
            return "edit-room-form";
        } else {
            // Handle invalid resort or room ID scenario
            return "redirect:/resort/{resortId}/roomlist";
        }
    }

    @PostMapping("/resort/{resortId}/rooms/{roomId}/edit")
    public String updateRoom(@PathVariable Long resortId, @PathVariable Long roomId, @ModelAttribute Room updatedRoom) {
        Resort resort = resortRepository.findById(resortId).orElse(null);
        Room room = roomRepository.findById(roomId).orElse(null);

        if (resort != null && room != null && room.getResort().getId().equals(resort.getId())) {
            // Update room properties with values from updatedRoom
//            room.setId(updatedRoom.getId());
            room.setDescription(updatedRoom.getDescription());
            // ... Update other room properties ...

            roomRepository.save(room);
        }

        return "redirect:/resort/{resortId}/roomlist";
    }

    @GetMapping("/resort/{resortId}/rooms/{roomId}/delete")
    public String deleteRoom(@PathVariable Long resortId, @PathVariable Long roomId) {
        Resort resort = resortRepository.findById(resortId).orElse(null);
        Room room = roomRepository.findById(roomId).orElse(null);

        if (resort != null && room != null && room.getResort().getId().equals(resort.getId())) {
            roomRepository.delete(room);
        }

        return "redirect:/resort/{resortId}/roomlist";
    }


}
