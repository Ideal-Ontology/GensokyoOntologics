package github.thelawf.gensokyoontology.api.client.xmmui.data;

import org.dom4j.Element;

public class XMMUIData {
    public final String id;
    public final String className;
    public final String tileEntityId;
    public final String containerClass;
    public final int inventoryX;
    public final int inventoryY;
    public final int titleX;
    public final int titleY;
    public final boolean hasInventory;
    public final boolean hasTileEntity;
    public final boolean hasContainer;

    public XMMUIData(Element rootElement) {
        this.id = rootElement.attributeValue("id");
        this.className = rootElement.attributeValue("class");
        this.tileEntityId = rootElement.attributeValue("tile_entity");
        this.containerClass = rootElement.attributeValue("container");

        this.inventoryX = Integer.parseInt(rootElement.attributeValue("inventory_x"));
        this.inventoryY = Integer.parseInt(rootElement.attributeValue("inventory_y"));
        this.titleX = Integer.parseInt(rootElement.attributeValue("title_x"));
        this.titleY = Integer.parseInt(rootElement.attributeValue("title_y"));

        this.hasInventory = Boolean.parseBoolean(rootElement.attributeValue("has_inventory"));
        this.hasTileEntity = Boolean.parseBoolean(rootElement.attributeValue("has_tile_entity"));
        this.hasContainer = Boolean.parseBoolean(rootElement.attributeValue("has_container"));
    }

}
