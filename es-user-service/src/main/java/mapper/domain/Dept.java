package mapper.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName dept
 */
@Data
public class Dept {
    /**
     * 部门id
     */
    private Integer deptid;

    /**
     * 部门名称
     */
    private String deptname;

    /**
     * 部门职称
     */
    private String deptitle;

    private static final long serialVersionUID = 1L;
}