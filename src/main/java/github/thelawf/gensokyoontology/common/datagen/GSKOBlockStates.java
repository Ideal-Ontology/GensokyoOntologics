package github.thelawf.gensokyoontology.common.datagen;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.renderer.model.Variant;
import net.minecraft.data.BlockStateProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.util.LazyValue;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.util.LazyOptional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GSKOBlockStates extends BlockStateProvider {
    public static final Logger LOGGER = LogManager.getLogger();

    public GSKOBlockStates(DataGenerator p_i232520_1_) {
        super(p_i232520_1_);
    }

}
