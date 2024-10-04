package org.desonglll.sdget.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: mikeshinoda
 * @date: 2024/10/1
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;
    private String status;
    private String message;
    private Object data;


    //查询 成功响应
    public static Result success(String message, Object data) {
        return new Result(1, "success", message, data);
    }
}
