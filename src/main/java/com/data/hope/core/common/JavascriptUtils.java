

package com.data.hope.core.common;

import com.data.hope.core.base.exception.Exceptions;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JavascriptUtils {

    private static final ScriptEngineFactory engineFactory;

    static {
        engineFactory = new NashornScriptEngineFactory();
    }

    public static Object invoke(Invocable invocable, String functionName, Object... args) throws Exception {
        if (invocable != null) {
            return invocable.invokeFunction(functionName, args);
        }
        return null;
    }

    public static Invocable load(String path) throws IOException, ScriptException {
        InputStream stream = JavascriptUtils.class.getClassLoader().getResourceAsStream(path);
        if (stream == null) {
            Exceptions.notFound(path);
        }
        try (InputStreamReader reader = new InputStreamReader(stream)) {
            ScriptEngine engine = engineFactory.getScriptEngine();
            engine.eval(reader);
            if (engine instanceof Invocable) {
                return (Invocable) engine;
            }
            return null;
        }
    }

}
