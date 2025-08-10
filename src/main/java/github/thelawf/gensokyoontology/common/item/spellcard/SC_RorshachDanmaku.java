package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.combat.RorschachDanmakuEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class SC_RorshachDanmaku extends SpellCardItem{

    @Override
    protected void applySpell(World worldIn, PlayerEntity playerIn) {
        RorschachDanmakuEntity rorschach = new RorschachDanmakuEntity(worldIn, playerIn);
        worldIn.addEntity(rorschach);
    }
}
