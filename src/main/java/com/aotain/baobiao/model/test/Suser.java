package com.aotain.baobiao.model.test;

import java.io.Serializable;

public class Suser implements Serializable {
    private String susername;

    private static final long serialVersionUID = 1L;

    public String getSusername() {
        return susername;
    }

    public void setSusername(String susername) {
        this.susername = susername == null ? null : susername.trim();
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
        Suser other = (Suser) that;
        return (this.getSusername() == null ? other.getSusername() == null : this.getSusername().equals(other.getSusername()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSusername() == null) ? 0 : getSusername().hashCode());
        return result;
    }
}