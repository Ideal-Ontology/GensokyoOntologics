package github.thelawf.gensokyoontology.api.client.xmmui.data;

import com.mojang.datafixers.util.Pair;
import org.dom4j.Element;

import java.util.List;

public class XMMUIInputData extends XMMUIWidgetData{
    public List<Pair<String, String>> highlights;
    public XMMUIInputData(Element element) {
        super(element);
    }
}
