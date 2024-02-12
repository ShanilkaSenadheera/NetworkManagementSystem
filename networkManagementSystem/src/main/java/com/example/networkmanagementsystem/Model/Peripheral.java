package com.example.networkmanagementsystem.Model;

import com.example.networkmanagementsystem.Validation.StatusType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "peripheral")
public class Peripheral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UID;
    private String vendor;
    private Date createdDate;
    @NotNull(message = "Status can not be null.")
    @StatusType
    private String status;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "gatewayNumber")
    private Gateway gateway;
}
