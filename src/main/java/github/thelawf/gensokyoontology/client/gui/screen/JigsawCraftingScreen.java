package github.thelawf.gensokyoontology.client.gui.screen;

import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.JigsawScreen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class JigsawCraftingScreen<C extends Container> extends ContainerScreen<C> {

    protected int jigsawU = 176;
    protected int jigsawV = 0;
    protected int jigsawX = 48;
    protected int jigsawY = 3;

    protected float storedPower;
    protected List<Block> jigsawBlocks = new ArrayList<>();


    public static final Map<Block, Vector2i> BLOCK_UV_OFFS = Util.make(() -> {
        Map<Block, Vector2i> map = new HashMap<>();
        map.put(null, new Vector2i(240, 240));
        map.put(Blocks.AIR , new Vector2i(240, 240));

        map.put(BlockRegistry.TOTEM_BRICKS.get(), new Vector2i(0,0));
        map.put(BlockRegistry.TURTLE_SHELL_PATTERN_BRICKS.get(), new Vector2i(0, 1));
        map.put(BlockRegistry.SAKURA_PATTERN_BRICKS.get(), new Vector2i(0, 2));
        map.put(BlockRegistry.ARROW_PATTERN_BRICKS.get(), new Vector2i(0, 3));
        map.put(BlockRegistry.HEMP_PATTERN_BRICKS.get(), new Vector2i(0, 4));
        map.put(BlockRegistry.ICHIMATSU_PATTERN_BRICKS.get(), new Vector2i(1, 0));
        map.put(BlockRegistry.WAVE_PATTERN_BRICKS.get(),  new Vector2i(1, 1));

        return map;
    });


    public JigsawCraftingScreen(C container, PlayerInventory playerInv, ITextComponent title) {
        super(container, playerInv, title);
    }

    public void setRenderedBlocks(List<Block> blocks) {
        this.jigsawBlocks = blocks;
    }

    public void setStoredPower(float storedPower) {
        this.storedPower = storedPower;
    }

    public float getStoredPower() {
        return this.storedPower;
    }
}
