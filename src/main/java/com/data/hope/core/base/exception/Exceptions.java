

package com.data.hope.core.base.exception;


import com.data.hope.core.common.MessageResolver;

import java.lang.reflect.Constructor;

public class Exceptions {

    public static void msg(String msg, String... code) {
        tr(BaseException.class, msg, code);
    }

    public static void base(String msg) {
        throw new BaseException(msg);
    }

    public static void notFound(String... msg) {
        tr(NotFoundException.class, "base.not.exists", msg);
    }

    public static void exists(String... msg) {
        tr(ParamException.class, "base.not.exists", msg);
    }

    public static void e(Exception e) {
        throw new BaseException(e);
    }

    public static void tr(Class<? extends BaseException> clz, String messageCode, String... codes) throws RuntimeException {
        BaseException throwable;
        try {
            String message = MessageResolver.getMessages(messageCode, (Object[]) codes);
            Constructor<? extends BaseException> constructor = clz.getConstructor(String.class);
            throwable = constructor.newInstance(message);
        } catch (Exception e) {
            throwable = new BaseException(messageCode);
        }
        throw throwable;
    }


}
