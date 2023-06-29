

package com.data.hope.core.provider;

import java.util.Map;

public interface ConnectParams extends Map<String,String> {

    String setConfig(String config);

    String getConfig();

}
