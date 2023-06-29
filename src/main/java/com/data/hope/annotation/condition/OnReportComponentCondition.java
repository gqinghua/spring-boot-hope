package com.data.hope.annotation.condition;

import com.data.hope.constant.ReportConstant;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * 订阅条件注解
 * @author guoqinghua
 * @since 2022-03-01
 */
public class OnReportComponentCondition extends SpringBootCondition {

    /**
     * 订阅组件的前缀
     */
    private String prefix = ReportConstant.COMPONENT_PREFIX;

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {

        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnReportComponent.class.getName());

        //组件名称
        String componentName = String.valueOf(annotationAttributes.get("value"));

        ConditionOutcome endpointOutcome = getEndpointOutcome(context, componentName);

        if (endpointOutcome != null) {
            return endpointOutcome;
        }

//        Binder binder = Binder.get(context.getEnvironment());
//
//        List<String> subscribes = binder.bind(this.prefix, Bindable.listOf(String.class)).get();
//
//        for (String subscribe: subscribes) {
//            if (StringUtils.equals(value, subscribe)) {
//                return ConditionOutcome.match(ConditionMessage.forCondition(ConditionalOnreportComponent.class, "metadata").because("matched"));
//            }
//        }
//
        return ConditionOutcome.noMatch(ConditionMessage.of("Report，not load "));
    }

    protected ConditionOutcome getEndpointOutcome(ConditionContext context, String componentName) {
        Environment environment = context.getEnvironment();
        String enabledProperty = this.prefix + componentName + ".enabled";
        if (environment.containsProperty(enabledProperty)) {
            boolean match = environment.getProperty(enabledProperty, Boolean.class, true);
            return new ConditionOutcome(match, ConditionMessage.forCondition(ConditionalOnReportComponent.class)
                    .because(this.prefix + componentName + ".enabled is " + match));
        }
        return null;
    }
}
