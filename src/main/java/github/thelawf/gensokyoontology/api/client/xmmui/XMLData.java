package github.thelawf.gensokyoontology.api.client.xmmui;

public class XMLData {
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

    public XMLData(String id, String className, String tileEntityId, String containerClass, int inventoryX, int inventoryY,
                   int titleX, int titleY, boolean hasInventory, boolean hasTileEntity, boolean hasContainer) {
        this.id = id;
        this.className = className;
        this.tileEntityId = tileEntityId;
        this.containerClass = containerClass;
        this.inventoryX = inventoryX;
        this.inventoryY = inventoryY;
        this.titleX = titleX;
        this.titleY = titleY;
        this.hasInventory = hasInventory;
        this.hasTileEntity = hasTileEntity;
        this.hasContainer = hasContainer;
    }

    public XMLData(String id, String className) {
        this.id = id;
        this.className = className;
        this.tileEntityId = null;
        this.containerClass = null;
        this.inventoryX = 0;
        this.inventoryY = 0;
        this.titleX = 0;
        this.titleY = 0;
        this.hasInventory = false;
        this.hasTileEntity = false;
        this.hasContainer = false;
    }
}
