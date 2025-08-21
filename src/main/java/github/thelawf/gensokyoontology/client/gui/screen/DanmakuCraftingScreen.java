package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.container.DanmakuCraftingContainer;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2i;

import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class DanmakuCraftingScreen extends ContainerScreen<DanmakuCraftingContainer> {

    protected int centerX = 79;
    protected int centerY = 32;
    protected int jigsawU = 176;
    protected int jigsawV = 0;
    protected int jigsawX = 48;
    protected int jigsawY = 3;
    protected int jigsawSize = 16;
    protected int slotX = 176;
    protected int slotY = 80;
    protected int slotSize = 18;

    public static final Map<Block, Vector2i> BLOCK_UV_OFFS = Util.make(() -> {
       Map<Block, Vector2i> map = new HashMap<>();
       map.put(null, new Vector2i(240, 240));
       map.put(Blocks.AIR , new Vector2i(240, 240));

       map.put(BlockRegistry.TOTEM_BRICKS.get(), new Vector2i(0,0));
       map.put(BlockRegistry.HEMP_PATTERN_BRICKS.get(), new Vector2i(0, 1));
       map.put(BlockRegistry.SAKURA_PATTERN_BRICKS.get(), new Vector2i(0, 2));
       map.put(BlockRegistry.ARROW_PATTERN_BRICKS.get(), new Vector2i(0, 3));
       map.put(BlockRegistry.TURTLE_SHELL_PATTERN_BRICKS.get(), new Vector2i(0, 4));

       return map;
    });
    public static final ResourceLocation DANMAKU_CRAFTING_TEXTURE = new ResourceLocation(
            GensokyoOntology.MODID, "textures/gui/danmaku_crafting.png"
    );

    public DanmakuCraftingScreen(DanmakuCraftingContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = 175;
        this.ySize = 165;

        this.playerInventoryTitleX = 7;
        this.playerInventoryTitleY = 68;

    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(@NotNull MatrixStack matrixStack, float partialTicks, int x, int y) {
        if (this.minecraft == null) return;
        this.minecraft.getTextureManager().bindTexture(DANMAKU_CRAFTING_TEXTURE);
        int left = this.guiLeft;
        int top = this.guiTop;
        this.blit(matrixStack, left, top, 0, 0, 175, 165);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (this.container.getJigsawPart(i, j) == Blocks.AIR) return;
                Vector2i uv = BLOCK_UV_OFFS.get(this.container.getJigsawPart(i, j));
                this.blit(matrixStack, this.jigsawX + 16 * i, this.jigsawY + 16 * j, this.jigsawU + 16 * uv.x, this.jigsawV + 16 * uv.y, 16, 16);
            }
        }
    }
}
