package github.thelawf.gensokyoontology.common.entity.passive;

import github.thelawf.gensokyoontology.common.entity.AbstractHumanEntity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CitizenEntity extends AbstractHumanEntity {

    public CitizenEntity(EntityType<? extends AgeableEntity> type, World worldIn) {
        super(type, worldIn);
    }

}
