package github.thelawf.gensokyoontology.common.container;

import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class RailAdjustContainer extends Container {
    private Vector3f rotation;
    private BlockPos railPos;
    public static final TranslationTextComponent NAME = GSKOUtil.fromLocaleKey("container.", ".rail_dash_board.title");
    public RailAdjustContainer(int windowId, World world, BlockPos pos) {
        super(ContainerRegistry.RAIL_DASHBOARD_CONTAINER.get(), windowId);
        this.railPos = pos;
        RailTileEntity railTile = (RailTileEntity) world.getTileEntity(pos);
        if (railTile != null) this.rotation = new Vector3f(railTile.getRoll(), railTile.getYaw(), railTile.getPitch());
    }

    @Override
    public boolean canInteractWith(@NotNull PlayerEntity playerIn) {
        return true;
    }

    public Vector3f getRotation () {
        return this.rotation;
    }

    public BlockPos getRailPos() {
        return this.railPos;
    }

    public static INamedContainerProvider create(World world, BlockPos pos) {
        return new SimpleNamedContainerProvider((id, inventory, player) ->
                new RailAdjustContainer(id, world, pos), NAME);
    }
}
