package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuMuzzle;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import java.util.List;

public class WaveAndParicleSpellEntity extends SpellCardEntity{

    public WaveAndParicleSpellEntity(World worldIn, List<DanmakuMuzzle<? extends AbstractDanmakuEntity>> muzzles, Entity owner) {
        super(worldIn, muzzles, owner);
    }

    @Override
    public void tick() {
        super.tick();
    }
}
