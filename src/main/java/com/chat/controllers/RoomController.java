package com.chat.controllers;

import com.chat.entities.Message;
import com.chat.entities.Room;
import com.chat.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    //Create Room Method
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomId){
        if(roomService.findByRoomId(roomId) != null){
            //room with roomId is already created
            return ResponseEntity.badRequest().body("Room already exist !! ");

        }

        //create new room

        Room room = new Room();
        room.setRoomId(roomId);
        Room savedRoom = roomService.create(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    //get Room by roomId
    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable("roomId") String roomId){
        Room roomById = roomService.findByRoomId(roomId);

        if(roomById == null){
            return ResponseEntity.badRequest().body("Room not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(roomById);

    }

    //get messages of particular room

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String roomId,
                                                     @RequestParam(value = "page" , defaultValue = "0",required = false) int page,
                                                     @RequestParam(value = "size" , defaultValue = "20" , required = false) int size){

        Room room = roomService.findByRoomId(roomId);

        if(room == null){
            return ResponseEntity.badRequest().build();
        }
        //get messages
        List<Message> messages = room.getMessages();

        //pagination

        int start = Math.max(0, messages.size() - (page+1)*size);
        int end = Math.min(messages.size(), start+size);
        List<Message> paginatedMessages  = messages.subList(start,end);
        return ResponseEntity.ok(paginatedMessages);
    }



}
