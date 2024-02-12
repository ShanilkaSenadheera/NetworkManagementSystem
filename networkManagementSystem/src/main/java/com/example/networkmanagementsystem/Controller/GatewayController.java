package com.example.networkmanagementsystem.Controller;

import com.example.networkmanagementsystem.Exception.ResourceNotFoundException;
import com.example.networkmanagementsystem.Model.Gateway;
import com.example.networkmanagementsystem.Repository.GatewayRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/v1")
public class GatewayController {

    @Autowired
    private GatewayRepository gatewayRepository;

    //Get all the gateways
    @GetMapping("/getAllGateways")
    public List<Gateway> getAllGateways(){
        return gatewayRepository.findAll();
    }

    //Get gateway by the serial number
    @GetMapping("/getGatewayBySerialNumber/{serialNumber}")
    public ResponseEntity<Gateway> getGatewayBySerialNumber(@PathVariable String serialNumber){
        Gateway gateway = gatewayRepository.findById(serialNumber)
                .orElseThrow(()-> new ResourceNotFoundException("There is no gateway for this serial number."));
        return ResponseEntity.ok(gateway);
    }

    //Create a new gateway
    @PostMapping("/createGateway")
    public Gateway createGateway(@RequestBody @Valid Gateway gateway){
        return gatewayRepository.save(gateway);
    }

    //Update a gateway
    @PutMapping("/updateGateway/{serialNumber}")
    public ResponseEntity<Gateway> updateGateway(@PathVariable String serialNumber, @RequestBody @Valid Gateway gatewayDetails){
        Gateway gateway = gatewayRepository.findById(serialNumber)
                .orElseThrow(()-> new ResourceNotFoundException("There is no gateway for this serial number."));
        gateway.setSerialNumber(gatewayDetails.getSerialNumber());
        gateway.setName(gatewayDetails.getName());
        gateway.setIpv4Address(gatewayDetails.getIpv4Address());

        Gateway updatedGateway = gatewayRepository.save(gateway);
        return ResponseEntity.ok(updatedGateway);
    }

    @PatchMapping("/updateGatewayName/{serialNumber}")
    public ResponseEntity<Gateway> updateGatewayName(@PathVariable String serialNumber, @RequestBody Map<String, String> nameMap){
        Gateway gateway = gatewayRepository.findById(serialNumber)
                .orElseThrow(()-> new ResourceNotFoundException("There is no gateway for this serial number."));
        if (nameMap.containsKey("name")){
            gateway.setName(nameMap.get("name"));
        }
        Gateway updatedGateway = gatewayRepository.save(gateway);
        return ResponseEntity.ok(updatedGateway);
    }

    //Delete a gateway
    @DeleteMapping("/deleteGateway/{serialNumber}")
    public ResponseEntity<Map<String, Boolean>> deleteGateway(@PathVariable String serialNumber){
        Gateway gateway = gatewayRepository.findById(serialNumber)
                .orElseThrow(()-> new ResourceNotFoundException("There is no gateway for this serial number."));

        gatewayRepository.delete(gateway);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
