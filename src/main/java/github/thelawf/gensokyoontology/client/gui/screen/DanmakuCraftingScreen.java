package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.ILayoutScreen;
import github.thelawf.gensokyoontology.common.container.DanmakuCraftingContainer;
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

    public static final int JIGSAW_SIZE = 18 * 5;
    protected int jigsawU = 176;
    protected int jigsawV = 0;
    protected int jigsawX = 48;
    protected int jigsawY = 3;

    protected int powerInfoX = 8;
    protected int consumedDanX = 0;
    protected int consumedPowerX = 0;

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
        this.playerInventoryTitleY = 88;

    }

    @Override
    protected void init() {
        super.init();
        this.jigsawX = this.getCenteredWidgetX(JIGSAW_SIZE, this.guiLeft, this.guiLeft);
        this.jigsawY = this.getCenteredWidgetY(JIGSAW_SIZE, this.guiTop, this.guiTop);

        this.powerInfoX = this.getCenteredWidgetX(this.getStrLength(this.font, POWER_INFO), this.guiLeft, 0);
        this.consumedPowerX = this.getCenteredWidgetX(this.getStrLength(this.font, CONSUMED_POWER), this.guiLeft, 0);
        this.consumedDanX = this.getCenteredWidgetX(this.getStrLength(this.font, CONSUMED_DANMAKU_SHOT), this.guiLeft, 0);

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

        int powerInfoX = this.centeredStrXOffset(this.font, POWER_INFO, this.width, this.powerInfoX);
        int powerInfoY = this.centeredStrYOffset(this.font, this.height, -20);

        int consumptionPX = this.centeredStrXOffset(this.font, CONSUMED_POWER, this.width, this.consumedPowerX);
        int consumptionPY = this.centeredStrYOffset(this.font, this.height, -10);

        int consumptionDX = this.centeredStrXOffset(this.font, CONSUMED_DANMAKU_SHOT, this.width, this.consumedDanX);
        int consumptionDY = this.centeredStrYOffset(this.font, this.height, 0);

        this.font.drawText(matrixStack, POWER_INFO, powerInfoX, powerInfoY, 0x111111);
        this.font.drawString(matrixStack, String.format("%.2f", this.storedPower),
                powerInfoX, powerInfoY, 0x111111);

        this.font.drawText(matrixStack,  CONSUMED_POWER, consumptionPX, consumptionPY, 0x111111);
        this.font.drawString(matrixStack, String.format("%.2f", this.consumedPower),
                consumptionPX, consumptionPY, 0x111111);

        this.font.drawText(matrixStack, POWER_INFO, consumptionDX, consumptionDY, 0x111111);
        this.font.drawString(matrixStack, String.valueOf(this.consumedDanmakuShot),
                consumptionDX, consumptionDY, 0x111111);
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
