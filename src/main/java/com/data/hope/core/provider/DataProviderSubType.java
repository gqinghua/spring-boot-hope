
package com.data.hope.core.provider;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class DataProviderSubType extends DataProviderInfo {

    private Map<String, String> properties;

}