package github.thelawf.gensokyoontology.client.gui.screen.script;

import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.NumberNBT;

public enum ConstType {
    INT("int"),
    LONG("long"),
    FLOAT("float"),
    DOUBLE("double");
    public final String key;

    ConstType(String key) {
        this.key = key;
    }

    public String  getKey() {
        return this.key;
    }
}
