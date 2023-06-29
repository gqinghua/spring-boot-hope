package com.data.hope.core.provider.processor;


import com.data.hope.core.base.processor.ExtendProcessor;
import com.data.hope.core.base.processor.ProcessorResponse;
import com.data.hope.core.provider.DataProviderSource;
import com.data.hope.core.provider.Dataframe;
import com.data.hope.core.provider.ExecuteParam;
import com.data.hope.core.provider.QueryScript;

public interface DataProviderPostProcessor extends ExtendProcessor {
    ProcessorResponse postRun(Dataframe frame, DataProviderSource config, QueryScript script, ExecuteParam executeParam);
}
