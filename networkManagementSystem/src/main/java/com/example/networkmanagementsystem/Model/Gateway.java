package com.example.networkmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Table(name = "Gateway")
public class Gateway {
    @Id
    private String serialNumber;
    private String name;
    @Pattern(regexp = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$", message = "Invalid IPv4Address")
    private String ipv4Address;
    @OneToMany(mappedBy = "gateway", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Peripheral> peripherals;
}
