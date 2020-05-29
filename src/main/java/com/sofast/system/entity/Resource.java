package com.sofast.system.entity;

import com.sofast.common.Entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>
 * 系统资源表
 * </p>
 *
 * @author ekko
 * @since 2020-05-29
 */
@TableName("t_system_resource")
@ApiModel(value="Resource对象", description="系统资源表")
public class Resource extends BaseEntity implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "URL")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "类型 字典类型")
    @TableField("type")
    private Long type;

    @ApiModelProperty(value = "权限")
    @TableField("permission")
    private String permission;

    @ApiModelProperty(value = "排序")
    @TableField("orders")
    private Integer orders;

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "父级ID")
    @TableField("parent_id")
    private Long parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Resource{" +
        "name=" + name +
        ", url=" + url +
        ", type=" + type +
        ", permission=" + permission +
        ", orders=" + orders +
        ", icon=" + icon +
        ", parentId=" + parentId +
        "}";
    }

    @Override
    public String getAuthority() {
        return permission;
    }
}
