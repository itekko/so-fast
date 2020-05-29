package com.sofast.system.entity;

import com.sofast.common.Entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 角色资源中间表
 * </p>
 *
 * @author ekko
 * @since 2020-05-29
 */
@TableName("t_system_role_resource")
@ApiModel(value="RoleResource对象", description="角色资源中间表")
public class RoleResource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty(value = "资源ID")
    @TableField("resource_id")
    private Long resourceId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "RoleResource{" +
        "roleId=" + roleId +
        ", resourceId=" + resourceId +
        "}";
    }
}
