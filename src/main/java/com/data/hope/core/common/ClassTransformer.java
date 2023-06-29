
package com.data.hope.core.common;


import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class ClassTransformer {

    public static void transform() {
        transformSqlWriter();
    }

    private static void transformSqlWriter() {
        try {
            ClassPool classPool = ClassPool.getDefault();
            CtClass ctClass = classPool.get("org.apache.calcite.sql.pretty.SqlPrettyWriter");
            CtMethod keyword = ctClass.getDeclaredMethod("keyword");
            keyword.setBody("{    maybeWhitespace($1);" +
                    "    buf.append($1);" +
                    "    if (!$1.equals(\"\")) {" +
                    "      setNeedWhitespace(needWhitespaceAfter($1));" +
                    "    } " +
                    "return;} ");
            ctClass.toClass();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
