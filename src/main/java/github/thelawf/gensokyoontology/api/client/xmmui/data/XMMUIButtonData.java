package github.thelawf.gensokyoontology.api.client.xmmui.data;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.dom4j.Element;

import java.lang.reflect.Method;

public class XMMUIButtonData extends XMMUIWidgetData{
    public Class<?> listenerClass;
    public Method onClick;
    public XMMUIButtonData(Element element) {
        super(element);
        try {
            this.listenerClass = Class.forName(element.attributeValue("listener"));
            this.onClick = ObfuscationReflectionHelper.findMethod(this.listenerClass, element.attributeValue("on_click"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
