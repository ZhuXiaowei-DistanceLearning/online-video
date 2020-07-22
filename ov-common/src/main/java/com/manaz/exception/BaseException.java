package com.manaz.exception;

import com.manaz.enums.ExceptionEnums;
import com.manaz.vo.JsonResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zxw
 * @date 2020/7/19 12:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends RuntimeException {
    private JsonResult jsonResult;
}
