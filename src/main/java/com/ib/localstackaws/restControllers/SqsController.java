package com.ib.localstackaws.restControllers;

import com.ib.localstackaws.sqsServices.SqsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sqs")
public class SqsController {

    private final SqsService sqsService;
    Map<String, Object> response = new HashMap<>();

    public SqsController(SqsService sqsService) {
        this.sqsService = sqsService;
    }

    @GetMapping("/read")
    public ResponseEntity<?> readMessage() {

        response.clear();
        response.put("result: ", sqsService.receiveMessages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
