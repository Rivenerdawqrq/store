package com.lhj.controller;

import com.lhj.controller.ex.*;
import com.lhj.service.ex.*;
import com.lhj.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {
    //操作成功的状态码
    public static final  int OK = 200;
    @ExceptionHandler({ServiceException.class,FileUploadException.class})//用于统一处理抛出的异常
    public JsonResult<Void> handlerException(Throwable e){
        JsonResult<Void> voidJsonResult = new JsonResult<>();
        if (e instanceof UsernameDuplicateException){
            voidJsonResult.setMessage("用户名被占用的异常");
            voidJsonResult.setState(4000);
        }else if (e instanceof InsertException){
            voidJsonResult.setMessage("插入数据时产生未知异常");
            voidJsonResult.setState(5000);
        }else if (e instanceof UserNotFoundException){
            voidJsonResult.setMessage("用户数据不存在的异常");
            voidJsonResult.setState(5001);
        }else if (e instanceof PasswordNotMatchException){
            voidJsonResult.setMessage("用户名和密码错误的异常");
            voidJsonResult.setState(5002);
        }else if (e instanceof UpdateException){
            voidJsonResult.setMessage("更新数据时产生未知的异常");
        }else if (e instanceof FileEmptyException) {
            voidJsonResult.setState(6000);
        } else if (e instanceof FileSizeException) {
            voidJsonResult.setState(6001);
        } else if (e instanceof FileTypeException) {
            voidJsonResult.setState(6002);
        } else if (e instanceof FileStateException) {
            voidJsonResult.setState(6003);
        } else if (e instanceof FileUploadIOException) {
            voidJsonResult.setState(6004);
        }
        return voidJsonResult;
    }

    protected final Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }

}
