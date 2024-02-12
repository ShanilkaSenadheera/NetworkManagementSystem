package com.example.networkmanagementsystem.Controller;

import com.example.networkmanagementsystem.Exception.ResourceNotFoundException;
import com.example.networkmanagementsystem.Model.Gateway;
import com.example.networkmanagementsystem.Model.Peripheral;
import com.example.networkmanagementsystem.Repository.GatewayRepository;
import com.example.networkmanagementsystem.Repository.PeripheralRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class PeripheralController {

    @Autowired
    private PeripheralRepository peripheralRepository;

    @Autowired
    private GatewayRepository gatewayRepository;

    //Get all the peripherals
    @GetMapping("/getAllPeripherals")
    public List<Peripheral> getAllPeripherals() {
        return peripheralRepository.findAll();
    }

    //Get peripheral by UID
    @GetMapping("/getPeripheralByUID/{UID}")
    public ResponseEntity<Peripheral> getPeripheralByUID(@PathVariable int UID){
        Peripheral peripheral = peripheralRepository.findById(UID)
                .orElseThrow(()-> new ResourceNotFoundException("There is no peripheral for this UID."));
        return ResponseEntity.ok(peripheral);
    }

    //Create a new peripheral
//    @PostMapping("/createPeripheral")
//    public Peripheral createPeripheral(@RequestBody @Valid Peripheral peripheral){
//        return peripheralRepository.save(peripheral);
//    }

    @PostMapping("/createPeripheral/{serialNumber}")
    public ResponseEntity<?> createPeripheral(@PathVariable String serialNumber, @RequestBody @Valid Peripheral peripheral){
        Gateway gateway = gatewayRepository.findById(serialNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Gateway not found for this serial number."));
        List<Peripheral> peripherals = gateway.getPeripherals();
        if(peripherals.size() >= 10){
            return ResponseEntity.badRequest().body("Gateway has reached the limit of 10.");
        }

        peripheral.setGateway(gateway);
        Peripheral savedPeripheral = peripheralRepository.save(peripheral);
        peripherals.add(savedPeripheral);
        gateway.setPeripherals(peripherals);
        gatewayRepository.save(gateway);
        return ResponseEntity.ok("Peripheral created successfully.");
    }

    //Update a peripheral
    @PutMapping("/updatePeripheral/{UID}")
    public ResponseEntity<Peripheral> updatePeripheral(@PathVariable int UID, @RequestBody @Valid Peripheral peripheralDetails){
        Peripheral peripheral = peripheralRepository.findById(UID)
                .orElseThrow(()-> new ResourceNotFoundException("There is no peripheral for this UID."));
        peripheral.setUID(peripheralDetails.getUID());
        peripheral.setVendor(peripheralDetails.getVendor());
        peripheral.setCreatedDate(peripheralDetails.getCreatedDate());
        peripheral.setStatus(peripheralDetails.getStatus());

        Peripheral updatedPeripheral = peripheralRepository.save(peripheral);
        return ResponseEntity.ok(updatedPeripheral);
    }

    //Update the peripheral vendor
    @PatchMapping("updatePeripheralStatus/{UID}")
    public ResponseEntity updatePeripheralStatus(@PathVariable int UID, @RequestBody @Valid Map<String, String> statusMap){
        Peripheral peripheral = peripheralRepository.findById(UID)
                .orElseThrow(()-> new ResourceNotFoundException("There is no peripheral for this UID."));
        if(statusMap.containsKey("status")){
            peripheral.setStatus(statusMap.get("status"));
        }
        Peripheral updatedPeripheral = peripheralRepository.save(peripheral);
        return ResponseEntity.ok(updatedPeripheral);
    }

    //Delete a peripheral
    @DeleteMapping("/deletePeripheral/{UID}")
    public ResponseEntity<Map<String, Boolean>> deletePeripheral(@PathVariable int UID){
        Peripheral peripheral = peripheralRepository.findById(UID)
                .orElseThrow(()-> new ResourceNotFoundException("There is no peripheral for this serial number."));

        peripheralRepository.delete(peripheral);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
