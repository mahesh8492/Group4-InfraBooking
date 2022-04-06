package com.zensar.room.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.room.bean.Room;
import com.zensar.room.service.RoomServiceImpl;

@RestController
public class RoomRestController {
	
	@Autowired
	RoomServiceImpl service;
	
	@GetMapping("/room/{id}")
	public Room findRoomById(@PathVariable(name="id")int id) {
		return this.service.findRoomById(id);
	}
	
	@DeleteMapping("/room/{id}")
	public ResponseEntity<Boolean> deleteRoomById(@PathVariable(name="id")int id) {
		return new ResponseEntity<Boolean>(this.service.deleteRoomById(id),HttpStatus.NO_CONTENT);
	}
}
