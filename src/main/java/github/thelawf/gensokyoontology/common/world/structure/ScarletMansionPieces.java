package github.thelawf.gensokyoontology.common.world.structure;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class ScarletMansionPieces {
    public static final IStructurePieceType TYPE = IStructurePieceType.register(MansionTemplate::new, "scarlet_devil_mansion");

    public static final IGenerator MANSION_000 = new IGenerator() {
        @Override
        public void init() {}
        @Override
        public boolean generate(TemplateManager manager, int recursiveDepth, MansionTemplate template,
                                BlockPos pos, List<StructurePiece> pieces) {
            Rotation rotation = template.placeSettings.getRotation();
            MansionTemplate mansion = ScarletMansionPieces.addHelper(pieces, ScarletMansionPieces.addPiece(
                    manager, template, new BlockPos(-3, 4, -3), rotation, "mansion_0_0_0", true));
            ScarletMansionPieces.addHelper(pieces, mansion);
            return true;
        }

    };

    public static void startScarletMansion(TemplateManager templateManager, BlockPos pos, Rotation rotation,
                                           List<StructurePiece> pieces) {
        MANSION_000.init();
        MansionTemplate mansion = addHelper(pieces, new MansionTemplate(templateManager, "mansion_0_0_0", pos, rotation, Mirror.NONE));
    }

    private static MansionTemplate addPiece(TemplateManager templateManager, MansionTemplate templateIn,
                                            BlockPos pos, Rotation rotation, String name,  boolean overwrite) {
        MansionTemplate mansion = new MansionTemplate(templateManager, name, templateIn.templatePosition, rotation, Mirror.NONE);
        BlockPos blockpos = templateIn.template.calculateConnectedPos(templateIn.placeSettings, pos, mansion.placeSettings, BlockPos.ZERO);
        mansion.offset(blockpos.getX(), blockpos.getY(), blockpos.getZ());
        return mansion;
    }

    private static MansionTemplate addHelper(List<StructurePiece> pieces, MansionTemplate template) {
        pieces.add(template);
        return template;
    }

    public static class MansionTemplate extends TemplateStructurePiece {
        private final String templateName;
        private final Rotation rotation;
        private final Mirror mirror;

        public MansionTemplate(TemplateManager templateManager, String templateName, BlockPos pos, Rotation rotation, Mirror mirror) {
            super(TYPE, 0);
            this.templateName = templateName;
            this.rotation = rotation;
            this.mirror = mirror;
            this.loadTemplate(templateManager);
        }

        public MansionTemplate(TemplateManager templateManager, CompoundNBT nbt) {
            super(TYPE, nbt);
            this.templateName = nbt.getString("Template");
            this.rotation = Rotation.NONE; // Rotation.valueOf(nbt.getString("Rot"));
            this.mirror = Mirror.NONE; // Mirror.valueOf(nbt.getString("Mi"));
            this.loadTemplate(templateManager);
        }

        private void loadTemplate(TemplateManager templateManager) {
            Template template = templateManager.getTemplate(GensokyoOntology.withRL("scarlet_devil_mansion/" + this.templateName));
            PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(true).setRotation(this.rotation).setMirror(this.mirror).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            this.template = template;
            this.templatePosition = this.template.getSize();
            this.boundingBox = template.getMutableBoundingBox(placementsettings, this.templatePosition);
            // this.setup(template, this.templatePosition, placementsettings);
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand, MutableBoundingBox sbb) {

        }
    }

    public interface IGenerator{
        void init();
        boolean generate(TemplateManager manager, int recursiveDepth, MansionTemplate template,
                         BlockPos pos, List<StructurePiece> pieces);
    }
}
