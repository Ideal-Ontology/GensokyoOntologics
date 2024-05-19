package github.thelawf.gensokyoontology.common.capability.special;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;

public interface IInputEntry extends INBTSerializable<CompoundNBT> {
    List<InputType> getEntries();
    void addEntry(InputType type);
    void addEntries(InputType... types);
}
