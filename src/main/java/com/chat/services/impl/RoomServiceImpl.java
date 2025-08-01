package com.chat.services.impl;

import com.chat.entities.Room;
import com.chat.repositories.RoomRepository;
import com.chat.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepo;

    @Override
    public Room findByRoomId(String roomId) {
        return roomRepo.findByRoomId(roomId);
    }

    @Override
    public Room create(Room room) {
        return roomRepo.save(room);
    }

}
