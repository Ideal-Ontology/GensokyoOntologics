package github.thelawf.gensokyoontology.common.item.touhou;

/*
public class IdealismSword extends SwordItem {
    public IdealismSword() {

         设置冷却请在 CooldownTracker.java 里面查看

        super(GSKOItemTier.IDEALISM, 12, -1.2F, new Item.Properties().group(
                GSKOItemTab.GSKO_ITEM_TAB));
    }

    @Override
    public boolean hitEntity(@Nonnull ItemStack stack,@Nonnull LivingEntity target,@Nonnull LivingEntity attacker) {
        if (attacker instanceof PlayerEntity && attacker.getHeldItemMainhand().getItem() instanceof IdealismSword) {
            double d0 = -MathHelper.sin(attacker.rotationYaw * ((float)Math.PI / 180F));
            double d1 = MathHelper.cos(attacker.rotationYaw * ((float)Math.PI / 180F));
            if (attacker.getEntityWorld() instanceof ServerWorld) {
                SpaceFissureParticleData sfpData = new SpaceFissureParticleData(new Vector3d(0,0,0),new Color(0),0.8F);
                ((ServerWorld)attacker.getEntityWorld()).spawnParticle(sfpData,
                        attacker.getPosX() + d0, attacker.getPosYHeight(0.5D),
                        attacker.getPosZ() + d1, 0, d0, 0.0D, d1, 0.0D);
            }
        }
        return super.hitEntity(stack, target, attacker);
    }


    @Override
    public @NotNull ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.getCooldownTracker().setCooldown(this,200);
        if (playerIn.getCooldownTracker().getCooldown(this,200) == 0) {
            SpaceFissureBlock sfb = new SpaceFissureBlock();
            worldIn.setBlockState(playerIn.getPosition(),
                    BlockRegistry.SPACE_FISSURE_BLOCK.get().getDefaultState());
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        // Minecraft 格式化字符为 \u00A7 - "§"
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip."+
                    GensokyoOntology.MODID +".idealism_sword_shift"));
            tooltip.add(new TranslationTextComponent("description." +
                    GensokyoOntology.MODID + ".idealism_sword_description"));
        }
        else {
            tooltip.add(new TranslationTextComponent("tooltip."+
                    GensokyoOntology.MODID +".idealism_sword_info"));
        }
    }
}

 */
