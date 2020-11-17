package com.lc.pojo;

import java.util.List;

public class SysRoleMenuVo {

    private Integer id;

    private String name;

    private String note;
    /**
     * 角色对应的菜单id
     */
    private List<Integer> menuIds;

    public Integer getId() {
        return id;

    }

    public void setId(Integer id) {
        this.id = id;

    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;

    }

    public void setNote(String note) {
        this.note = note;
    }


    public List<Integer> getMenuIds() {
        return menuIds;

    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }
}
