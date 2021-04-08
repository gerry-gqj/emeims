package com.emeims.entity.count;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockCount {
    private String stockMotorType;
    private Integer stockMotorNumber;
}
