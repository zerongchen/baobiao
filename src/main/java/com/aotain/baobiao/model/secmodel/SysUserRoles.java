package com.aotain.baobiao.model.secmodel;

import java.io.Serializable;

public class SysUserRoles implements Serializable {
    private Long id;

    private Long rolesId;

    private Long sysUserId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRolesId() {
        return rolesId;
    }

    public void setRolesId(Long rolesId) {
        this.rolesId = rolesId;
    }

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysUserRoles other = (SysUserRoles) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRolesId() == null ? other.getRolesId() == null : this.getRolesId().equals(other.getRolesId()))
            && (this.getSysUserId() == null ? other.getSysUserId() == null : this.getSysUserId().equals(other.getSysUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRolesId() == null) ? 0 : getRolesId().hashCode());
        result = prime * result + ((getSysUserId() == null) ? 0 : getSysUserId().hashCode());
        return result;
    }
}