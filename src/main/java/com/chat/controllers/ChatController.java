package com.chat.controllers;

import com.chat.config.AppConstants;
import com.chat.entities.Message;
import com.chat.entities.Room;
import com.chat.payload.MessageRequest;
import com.chat.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
@CrossOrigin(AppConstants.FRONTEND_APP_URL)
public class ChatController {

    @Autowired
    private RoomService roomService;


    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest request
    ) throws Exception{


        Room room = roomService.findByRoomId(request.getRoomId());

        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());

        if(room != null){
            room.getMessages().add(message);
            roomService.create(room);
        }else{
            throw new RuntimeException("Room not found !!");
        }

        return message;

    }


}
