package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.ILayoutScreen;
import github.thelawf.gensokyoontology.common.container.DanmakuCraftingContainer;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2i;

import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class DanmakuCraftingScreen extends ContainerScreen<DanmakuCraftingContainer> implements ILayoutScreen {

    public static final int JIGSAW_SIZE = 16 * 5;
    protected int jigsawU = 176;
    protected int jigsawV = 0;
    protected int jigsawX = 48;
    protected int jigsawY = 3;

    protected int piLength = 0;
    protected int cdLength = 0;
    protected int cpLength = 0;

    protected float storedPower;
    protected float consumedPower;
    protected int consumedDanmakuShot;

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

    public static final TranslationTextComponent POWER_INFO = GensokyoOntology.translate("gui.", ".power");
    public static final TranslationTextComponent CONSUMED_POWER = GensokyoOntology.translate("gui.", ".power_consumed");
    public static final TranslationTextComponent CONSUMED_DANMAKU_SHOT = GensokyoOntology.translate("gui.", ".danmaku_shot_consumed");

    public static final ResourceLocation DANMAKU_CRAFTING_TEXTURE = new ResourceLocation(
            GensokyoOntology.MODID, "textures/gui/danmaku_crafting.png"
    );

    public DanmakuCraftingScreen(DanmakuCraftingContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = 175;
        this.ySize = 191;

        this.playerInventoryTitleX = 7;
        this.playerInventoryTitleY = 90;

    }

    @Override
    protected void init() {
        super.init();
        // xsize = 175
        // jigsawsize = 125
        // (175 - 90) / 2 + (wid - 175) / 2
        this.jigsawX = this.guiLeft + 93;
        this.jigsawY = this.guiTop + 12;

        this.piLength =  this.guiLeft + 4 + this.getStrLength(this.font, POWER_INFO);
        this.cpLength =  this.guiLeft + 4 + this.getStrLength(this.font, CONSUMED_POWER);
        this.cdLength =  this.guiLeft + 4 + this.getStrLength(this.font, CONSUMED_DANMAKU_SHOT);

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

        this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, 175, 191);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (this.container.getJigsawPart(i, j) == Blocks.AIR) return;
                Vector2i uv = BLOCK_UV_OFFS.get(this.container.getJigsawPart(i, j));
                this.blit(matrixStack, this.jigsawX + 16 * i, this.jigsawY + 16 * j, this.jigsawU + 16 * uv.x, this.jigsawV + 16 * uv.y, 16, 16);
            }
        }

        int powerInfoY = this.centeredStrYOffset(this.font, this.height, -40);
        int consumptionPY = this.centeredStrYOffset(this.font, this.height, -30);
        int consumptionDY = this.centeredStrYOffset(this.font, this.height, -20);

        this.font.drawText(matrixStack, POWER_INFO, this.guiLeft + 8, powerInfoY, 0x111111);
        this.font.drawString(matrixStack, String.format("%.2f", this.getStoredPower()),
                this.piLength, powerInfoY, 0x111111);

        this.font.drawText(matrixStack,  CONSUMED_POWER, this.guiLeft + 8, consumptionPY, 0x111111);
        this.font.drawString(matrixStack, String.format("%.2f", this.getConsumedPower()),
                this.cpLength, consumptionPY, 0x111111);

        this.font.drawText(matrixStack, CONSUMED_DANMAKU_SHOT, this.guiLeft + 8, consumptionDY, 0x111111);
        this.font.drawString(matrixStack, String.valueOf(this.getConsumedDanmakuShot()),
                this.cdLength, consumptionDY, 0x111111);
    }

    public void setStoredPower(float storedPower) {
        this.storedPower = storedPower;
    }

    public float getStoredPower() {
        return this.storedPower;
    }

    public void setConsumedPower(float consumedPower) {
       this.consumedPower = consumedPower;
    }

    public float getConsumedPower() {
        return this.consumedPower;
    }

    public void setConsumedDanmakuShot(int consumedDanmakuShot) {
        this.consumedDanmakuShot = consumedDanmakuShot;
    }

    public int getConsumedDanmakuShot() {
        return this.consumedDanmakuShot;
    }
}
