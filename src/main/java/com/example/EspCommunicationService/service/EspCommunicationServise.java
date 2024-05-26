package com.example.EspCommunicationService.service;

import arduino.Arduino;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.Socket;
@Slf4j
@Service
public class EspCommunicationServise {
    @Value("${esp.connect.port}")
    private String portDescriptor;
    private Arduino esp;

    public void createConnection(){
        esp = new Arduino(portDescriptor, 9600);
        boolean connection = esp.openConnection();
        if (!connection) {
            log.info("Failed to connect to ESP on port " + portDescriptor);
        } else {
            log.info("Connection to esp on " + portDescriptor + " = " + connection);
        }
        esp.serialWrite('1');
    }

    public String readDataFromCOM(){
        String data = "";
        createConnection();
        esp.serialWrite('1');
        data = esp.serialRead();
        log.info("Read data from " + portDescriptor + " = " + data);
        esp.closeConnection();
        return data;
    }

    public String sendDataToCOM(String data){
        createConnection();
        esp.serialWrite(data);
        String approve = esp.serialRead();
        log.info("Sed data to " + portDescriptor + " = " + approve);
        esp.closeConnection();
        return approve;
    }

}
