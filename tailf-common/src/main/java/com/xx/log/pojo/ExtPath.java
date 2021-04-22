package com.xx.log.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExtPath implements Comparable<ExtPath> {

    private String id;

    // 标记唯一节点
    private String flag;

    private String text;

    private String absPath;

    private String leaf;

    @Override
    public int compareTo(ExtPath o) {
        return o.getText().compareTo(this.getText());
    }
}
