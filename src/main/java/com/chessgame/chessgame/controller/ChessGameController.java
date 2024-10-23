package com.chessgame.chessgame.controller;

import com.chessgame.chessgame.model.ChessMove;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ChessGameController {

    private Map<String, String> assignedRoles = new HashMap<>();
    private String whitePlayerId = null;
    private String blackPlayerId = null;

    private final SimpMessagingTemplate messagingTemplate;

    public ChessGameController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/connect")
    @SendTo("/topic/game")
    public Map<String, String> assignRole(String playerId) {
        String assignedRole;

        // Assign the first player as white, and the second as black
        if (whitePlayerId == null) {
            whitePlayerId = playerId;
            assignedRole = "white";
        } else if (blackPlayerId == null && !whitePlayerId.equals(playerId)) {
            blackPlayerId = playerId;
            assignedRole = "black";
        } else {
            assignedRole = "spectator"; // If there are already two players
        }

        // Send the role back to the player
        assignedRoles.put(playerId, assignedRole);
        Map<String, String> response = new HashMap<>();
        response.put("playerId", playerId);
        response.put("role", assignedRole);

        return response;
    }

    @MessageMapping("/move")
    @SendTo("/topic/game")
    public ChessMove processMove(ChessMove move) {
        // Logic to process the move could go here (validation, etc.)
        System.out.println("Received move: " + move);
        return move; // Send the move to all subscribed clients
    }
}
