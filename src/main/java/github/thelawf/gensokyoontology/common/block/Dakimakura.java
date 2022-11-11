package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.client.model.obj.MaterialLibrary;
import net.minecraftforge.client.model.obj.OBJLoader;

public class Dakimakura extends Block {
    public Dakimakura() {
        super(Properties.create(Material.WOOL).hardnessAndResistance(1.f));
    }
}
