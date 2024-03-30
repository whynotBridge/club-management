package com.clubmanagement.model.dtos;

import com.clubmanagement.model.enums.PositionEnum;
import com.clubmanagement.model.pojos.Club;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@ApiModel("查询我的社团返回信息")
public class QueryMyclubDTO extends Club implements Serializable {
    @Enumerated(EnumType.STRING)
    private PositionEnum position;
}
