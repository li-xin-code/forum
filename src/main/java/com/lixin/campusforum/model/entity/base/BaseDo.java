package com.lixin.campusforum.model.entity.base;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 与数据表一一对应
 * Do -> Data Object
 *
 * @author lixin
 */
@Data
public abstract class BaseDo implements Serializable {
    private static final long serialVersionUID = 598489697309893157L;
    private Long id;
    private LocalDateTime createTime;
}
