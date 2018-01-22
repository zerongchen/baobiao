package com.aotain.baobiao.model.test;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class User implements Serializable {
    private Long userGroupId;

    private String userGroupName;

    private String userGroupDesc;

    private Integer userGroupType;

    private Long totalUserNum;

    private Long groupUserNum;

    private Long errorUserNum;

    private String logfilePath;

    private Integer status;

    private String createOper;

    private String modifyOper;

    private Date createTime;

    private Date modifyTime;

    private Integer userGroupMark;

    private Long messageNo;

    private Integer userGroupMold;

    private Long areaCode;

    private String houseIdstr;

    private Byte operateType;


}