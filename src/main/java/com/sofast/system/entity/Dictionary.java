package com.sofast.system.entity;

import com.sofast.common.Entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author ekko
 * @since 2020-06-24
 */
@TableName("t_system_dictionary")
@ApiModel(value="Dictionary对象", description="字典表")
public class Dictionary extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模块名")
    @TableField("module")
    private String module;

    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "编码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "字典名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "父级ID")
    @TableField("parent_id")
    private String parentId;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
        "module=" + module +
        ", type=" + type +
        ", name=" + name +
        ", parentId=" + parentId +
        "}";
    }
}
