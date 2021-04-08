package com.emeims.entity.count;

import com.emeims.entity.base.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCount {
    private Integer userNumber;
    private Position position;
}
