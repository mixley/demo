/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.mixley.cloud.common.core.exception;

import com.mixley.cloud.common.core.constant.ErrorType;
import com.mixley.cloud.common.core.utils.Checks;
import lombok.Getter;

/**
 * 自定义异常
 *
 * @author sunny
 * @date 2020-1-2
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    @Getter
    private ErrorType error;
    @Getter
    private String message;
    @Getter
    private String outMsg;
    @Getter
    private Throwable cause = this;

    public BaseException(ErrorType error, String message, Throwable cause) {
        fillInStackTrace();
        this.error = error;
        this.message = message;
        this.outMsg = getOutMessage(error,message,cause);
        if (this.message==null){
            this.message=this.outMsg;
        }
        if (cause!=null){
            this.cause = cause;
        }
        printStackTrace();
    }
    public BaseException(ErrorType error, Throwable e) {
        this(error, null, e);
    }
    public BaseException(ErrorType error, String message) {
        this(error, message, null);
    }
    public BaseException(ErrorType error) {
        this(error, null, null);
    }

    /**
     * 获取输出的消息
     *
     * @param error 错误
     * @param message   消息
     * @param cause     异常
     * @return {@link String}
     */
    private String getOutMessage(ErrorType error, String message, Throwable cause) {
        StringBuilder messageBuilder = new StringBuilder();
        if (null != cause){
            messageBuilder.append(getRealCall(cause));
        }else{
            messageBuilder.append(getRealCall(this));
        }
        messageBuilder.append("[");
        messageBuilder.append(error.getClass().getSimpleName());
        messageBuilder.append(":");
        messageBuilder.append(error.getCode());
        messageBuilder.append(":");
        messageBuilder.append(error.getDescribe());
        messageBuilder.append("]");
        if (!Checks.isEmpty(message)){
            messageBuilder.append("(");
            messageBuilder.append("msg:");
            messageBuilder.append(message);
            messageBuilder.append(")");
        }
        return messageBuilder.toString();
    }

    public String getRealCall(Throwable cause) {
        StackTraceElement[] stackTrace = cause.getStackTrace();
        return getRealCall(stackTrace);
    }

    public String getRealCall(StackTraceElement[] stackTrace) {
        StackTraceElement temp = null;
        for (int i = 0; i < stackTrace.length; i++) {
            StackTraceElement element = stackTrace[i];
            try {
                Class<?> aClass = Class.forName(element.getClassName());
                if (Exception.class.isAssignableFrom(aClass)) {
                    continue;
                }
                if (Enum.class.isAssignableFrom(aClass)) {
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            temp = element;
            break;
        }
        if (null == temp) {
            return "";
        }
        return new StringBuilder()
                .append("{")
                .append(temp.getFileName())
                .append(":")
                .append(temp.getLineNumber())
                .append("}")
                .toString();
    }

}
