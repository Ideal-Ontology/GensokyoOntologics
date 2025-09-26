package github.thelawf.gensokyoontology.common.item;

import github.thelawf.gensokyoontology.common.entity.RailEntity;
import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import github.thelawf.gensokyoontology.common.util.math.EulerAngle;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class TrackPlacer extends Item {
    public static final HashMap<Vector2f, Float> VECTOR2F_MAPPING = Util.make(new HashMap<>(), map -> {
        map.put(new Vector2f(-22.5F, 22.5F), 270F);
        map.put(new Vector2f(22.5F, 45F + 22.5F), 45F);
        map.put(new Vector2f(45F + 22.5F, 90F + 22.5F), 0F);
        map.put(new Vector2f(90F + 22.5F, 135F + 22.5F), 135F);
        map.put(new Vector2f(135F + 22.5F, 180F), 90F);
        map.put(new Vector2f(-180F, -180F + 22.5F), 90F);
        map.put(new Vector2f(-180F + 22.5F, -135F + 22.5F), 225F);
        map.put(new Vector2f(-135F + 22.5F, -90F + 22.5F), 180F);
        map.put(new Vector2f(-90F + 22.5F, -45F + 22.5F), 315F);
    });

    public TrackPlacer(Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    public ActionResultType onItemUse(@NotNull ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        ItemStack stack = context.getItem();
        RailEntity rail = RailEntity.place(world, pos);

        if (player == null) return ActionResultType.FAIL;
        float yaw = GSKOMathUtil.getEulerAngle(player.getLookVec()).yaw();
        VECTOR2F_MAPPING.entrySet().stream().map((entry) -> {
            if (yaw > entry.getKey().x && yaw <= entry.getKey().y) {
                return entry.getValue();
            }
            return 0;
        }).findFirst().ifPresent(value -> {
            rail.setRotation(EulerAngle.of(0, yaw, 0).toQuaternion());
        });
        stack.shrink(1);
        return ActionResultType.CONSUME;
    }
}
