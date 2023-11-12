package github.thelawf.gensokyoontology.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import org.lwjgl.opengl.GL11;

import java.util.OptionalDouble;

public class GSKORenderTypes extends RenderType {

    public GSKORenderTypes(String nameIn, VertexFormat formatIn, int drawModeIn, int bufferSizeIn, boolean useDelegateIn, boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
        super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
    }

    public GSKORenderTypes(String nameIn, VertexFormat formatIn, int drawModeIn, int bufferSizeIn,
                           boolean useDelegateIn, boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn,
                           double width) {
        super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);

    }

    public static final RenderType LASER_LINE_THICK = makeType("laser_line_thick",
            DefaultVertexFormats.POSITION_COLOR, GL11.GL_LINES, 256,
            RenderType.State.getBuilder().line(getLaserLine(5.0))
                    .layer(VIEW_OFFSET_Z_LAYERING)
                    .transparency(TRANSLUCENT_TRANSPARENCY)
                    .depthTest(DEPTH_ALWAYS)
                    .cull(CULL_DISABLED)
                    .lightmap(LIGHTMAP_DISABLED)
                    .build(false));

    public static final RenderType LASER_BEAM = makeType("laser_beam",
            DefaultVertexFormats.POSITION_TEX, GL11.GL_QUADS, 256,
            RenderType.State.getBuilder().texture(BLOCK_SHEET)
                    .layer(VIEW_OFFSET_Z_LAYERING)
                    .transparency(TRANSLUCENT_TRANSPARENCY)
                    .depthTest(DEPTH_ALWAYS)
                    .cull(CULL_ENABLED)
                    .lightmap(LIGHTMAP_DISABLED)
                    .build(false));

    public static LineState getLaserLine(double width) {
        return new LineState(OptionalDouble.of(width));
    }
}
