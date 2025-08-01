package com.chat.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "rooms")
public class Room {

    @Id
    private String id;//mongoDB identifier
    private String roomId;//for use of user;

    private List<Message> messages = new ArrayList<>();


}
