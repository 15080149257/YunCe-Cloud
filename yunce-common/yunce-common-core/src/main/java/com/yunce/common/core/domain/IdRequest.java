package com.yunce.common.core.domain;

import com.yunce.common.core.group.Type;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class IdRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(message = "接口id不能为空", groups = {Type.Edit.class})
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
