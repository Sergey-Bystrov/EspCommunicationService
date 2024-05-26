package com.example.EspCommunicationService.controller;

import arduino.Arduino;
import com.example.EspCommunicationService.service.EspCommunicationServise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/esp")
public class EspController {

    @Value("${esp.connect.port}")
    private String portDescriptor;

    @Autowired
    private EspCommunicationServise communicationServise;

    @GetMapping("/read")
    public String readData() {
        String message = communicationServise.readDataFromCOM();
        log.info("Read data from " + portDescriptor + " = " + message);
        return message;
    }

    @GetMapping("/write")
    public String writeData(@RequestParam String data) {
        communicationServise.sendDataToCOM(data);
        return "Data written: " + data;
    }
}
