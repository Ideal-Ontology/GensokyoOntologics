package github.thelawf.gensokyoontology.common.client.model;

import com.mojang.datafixers.util.Either;
import net.minecraft.client.renderer.model.*;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class RailTrackNodeModel extends BlockModel {
    public RailTrackNodeModel(@Nullable ResourceLocation parentLocation, List<BlockPart> elements, Map<String, Either<RenderMaterial, String>> textures, boolean ambientOcclusion, @Nullable BlockModel.GuiLight guiLight3d, ItemCameraTransforms cameraTransforms, List<ItemOverride> overrides) {
        super(parentLocation, elements, textures, ambientOcclusion, guiLight3d, cameraTransforms, overrides);
    }
}
