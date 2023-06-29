

package com.data.hope.core.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ClassUtils {

    public static String getAbsoluteClassLocation(Class<?> clz) {
        return clz.getProtectionDomain().getCodeSource().getLocation().getFile();
    }

    public static File getAbsoluteClasspathFile(String name, Class<?> clz) throws IOException {
        return new File(getAbsoluteClassLocation(clz), name);
    }

    public static Properties getClassProperties(String name, Class<?> clz) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(getAbsoluteClassLocation(clz), name)));
        return properties;
    }

}
