package com.zensar.room.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.room.bean.Room;
import com.zensar.room.dao.RoomDAO;

@Service
public class RoomServiceImpl {

	@Autowired
	private RoomDAO dao;

	public Room findRoomById(int id) {
		Optional<Room> optional;
		optional = this.dao.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			return null;
	}
	public  boolean deleteRoomById(int id) {
		boolean result = true;
		try {
			this.dao.deleteById(id);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
}
