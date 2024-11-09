package com.linkedin.connections_service.controller;

import com.linkedin.connections_service.entity.Person;
import com.linkedin.connections_service.service.ConnectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionsController {

    private final ConnectionsService connectionsService;

    @GetMapping("/{name}")
    public ResponseEntity<Person> getPersonByName(@PathVariable String name) {
        return ResponseEntity.ok(connectionsService.getPersonByName(name));
    }
    @GetMapping("/{userId}/first-degree")
    public ResponseEntity<List<Person>> getFirstConnections(@PathVariable Long userId) {
        return ResponseEntity.ok(connectionsService.getFirstDegreeConnections(userId));
    }
}
