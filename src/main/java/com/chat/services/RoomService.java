package com.chat.services;


import com.chat.entities.Room;

public interface RoomService {


    Room findByRoomId(String roomId);

    Room create(Room room);
}
