package github.thelawf.gensokyoontology.common.libs.logoslib.annotations;

import java.lang.annotation.*;

/**
 * 仅作为一个提示，提示本参数或值需要提供弧度值
 * @author F定律
 */
@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Radian {
}
