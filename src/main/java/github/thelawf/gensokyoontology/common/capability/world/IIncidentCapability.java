package github.thelawf.gensokyoontology.common.capability.world;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * 幻想乡的异变系统使用Forge的能力系统实现，为此，我们需要实现自定义NBT标签的序列化器。
 */
public interface IIncidentCapability extends INBTSerializable<CompoundNBT> {

    /**
     * 设置异变事件是否被触发
     */
    void setTriggered(boolean triggered);

    /**
     * 获取异变事件是否被触发
     */
    boolean isTriggered();

}
