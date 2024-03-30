package com.clubmanagement.model.dtos;

import com.clubmanagement.model.pojos.Member;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("社长查询成员信息返回参数")
public class MemberDetailDTO extends Member implements Serializable {
    private String username;
    private String email;
}
