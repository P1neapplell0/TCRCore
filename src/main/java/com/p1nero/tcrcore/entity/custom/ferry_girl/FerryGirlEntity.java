package com.p1nero.tcrcore.entity.custom.ferry_girl;

import artifacts.item.ArtifactItem;
import com.github.alexthe668.domesticationinnovation.server.item.DIItemRegistry;
import com.p1nero.dialog_lib.api.component.DialogueComponentBuilder;
import com.p1nero.dialog_lib.api.component.DialogNode;
import com.p1nero.dialog_lib.api.entity.custom.IEntityNpc;
import com.p1nero.dialog_lib.api.entity.goal.LookAtConservingPlayerGoal;
import com.p1nero.dialog_lib.client.screen.DialogueScreen;
import com.p1nero.dialog_lib.client.screen.builder.StreamDialogueScreenBuilder;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.PlayerDataManager;
import com.p1nero.tcrcore.capability.TCRQuestManager;
import com.p1nero.tcrcore.capability.TCRQuests;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.events.OverworldVillageTeleporter;
import com.p1nero.tcrcore.events.PlayerEventListeners;
import com.p1nero.tcrcore.item.TCRItems;
import com.p1nero.tcrcore.utils.FTBTeamUtils;
import com.p1nero.tcrcore.utils.ItemUtils;
import com.p1nero.tcrcore.utils.WorldUtils;
import net.blay09.mods.waystones.block.ModBlocks;
import net.genzyuro.uniqueaccessories.item.UAUniqueCurioItem;
import net.genzyuro.uniqueaccessories.registry.UAItems;
import net.magister.bookofdragons.entity.ModEntities;
import net.magister.bookofdragons.entity.base.dragon.DragonBase;
import net.magister.bookofdragons.entity.data.DragonVariantConfig;
import net.magister.bookofdragons.event.DragonDiscoveryEventHandler;
import net.magister.bookofdragons.genetics.GeneticsSpawner;
import net.magister.bookofdragons.genetics.config.SpeciesGeneticsConfig;
import net.magister.bookofdragons.genetics.config.SpeciesGeneticsConfigRegistry;
import net.magister.bookofdragons.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class FerryGirlEntity extends PathfinderMob implements IEntityNpc, GeoEntity, Merchant {
    protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Nullable
    private Player tradingPlayer;
    private MerchantOffers offersArtifact = new MerchantOffers();
    private final List<Item> rareArtifactItems;

    public FerryGirlEntity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
        rareArtifactItems = List.of(artifacts.registry.ModItems.CRYSTAL_HEART.get(),
                artifacts.registry.ModItems.FERAL_CLAWS.get(),
                artifacts.registry.ModItems.VAMPIRIC_GLOVE.get(),
                artifacts.registry.ModItems.POWER_GLOVE.get(),
                artifacts.registry.ModItems.NOVELTY_DRINKING_HAT.get(),
                artifacts.registry.ModItems.PLASTIC_DRINKING_HAT.get(),
                UAItems.SUN_STONE.get(),
                UAItems.MOON_STONE.get(),
                UAItems.HERO_EMBLEM.get(),
                UAItems.MASTER_NINJA_TABI.get(),
                UAItems.ENDER_LENS.get(),
                UAItems.SHINY_STONE.get());
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide) {
            if (tickCount % 100 == 0) {
                BlockPos myPos = this.getOnPos();
                if (myPos.getX() != WorldUtils.FERRY_GIRL_POS.getX() || myPos.getZ() != WorldUtils.FERRY_GIRL_POS.getZ()) {
                    this.setPos(new BlockPos(WorldUtils.FERRY_GIRL_POS).getCenter());
                }
            }
            if (getConversingPlayer() != null && (getConversingPlayer().isRemoved() || getConversingPlayer().isDeadOrDying() || getConversingPlayer().distanceTo(this) > 5)) {
                setConversingPlayer(null);
            }
        }
    }

    private void initMerchant() {
        offersArtifact = new MerchantOffers();
        offersArtifact.add(new MerchantOffer(
                new ItemStack(Items.ENDER_PEARL, 1),
                new ItemStack(ModBlocks.waystone, 1),
                142857, 0, 0.02f));

        ForgeRegistries.ITEMS.getValues().forEach(item -> {
            if (PlayerEventListeners.illegalItems.contains(item)) {
                return;
            }
            if (item instanceof ArtifactItem || item instanceof UAUniqueCurioItem) {
                if (!rareArtifactItems.contains(item)) {
                    offersArtifact.add(new MerchantOffer(
                            new ItemStack(TCRItems.ARTIFACT_TICKET.get(), 1),
                            new ItemStack(item, 1),
                            142857, 0, 0.02f));
                }
            }
        });
        rareArtifactItems.forEach(item -> {
            if (PlayerEventListeners.illegalItems.contains(item)) {
                return;
            }
            offersArtifact.add(new MerchantOffer(
                    new ItemStack(TCRItems.RARE_ARTIFACT_TICKET.get(), 1),
                    new ItemStack(item, 1),
                    142857, 0, 0.02f));
        });
    }

    @Override
    public boolean hurt(@NotNull DamageSource damageSource, float p_21017_) {
        if(FMLEnvironment.production) {
            return false;
        }
        if (damageSource.getEntity() instanceof Player player && player.isCreative()) {
            player.displayClientMessage(Component.translatable("/summon " + ForgeRegistries.ENTITY_TYPES.getKey(this.getType())).withStyle(ChatFormatting.RED), false);
            this.discard();
        }
        return false;
    }

    @Override
    protected @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        initMerchant();
        if (player instanceof ServerPlayer serverPlayer) {
            if (!PlayerDataManager.ferryGirlTalked.get(player)) {
                PlayerDataManager.ferryGirlTalked.put(player, true);
            }
            CompoundTag tag = new CompoundTag();
            boolean canGoOverworld = TCRQuestManager.hasFinished(serverPlayer, TCRQuests.TALK_TO_CHRONOS_0);
            //必须追踪现在的任务才能去主世界
            if(TCRQuestManager.hasQuest(serverPlayer, TCRQuests.TALK_TO_FERRY_GIRL_0) && !TCRQuestManager.getCurrentQuest(serverPlayer).equals(TCRQuests.TALK_TO_FERRY_GIRL_0)) {
                canGoOverworld = false;
            }
            tag.putBoolean("can_go_overworld", canGoOverworld);
            this.sendDialogTo(serverPlayer, tag);
        }
        return InteractionResult.sidedSuccess(level().isClientSide);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new LookAtConservingPlayerGoal<>(this));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public DialogueScreen getDialogueScreen(CompoundTag compoundTag) {
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        if (localPlayer == null) {
            return null;
        }
        boolean canGoOverworld = compoundTag.getBoolean("can_go_overworld");
        TCRQuestManager.Quest currentQuest = TCRQuestManager.getCurrentQuest(localPlayer);
        StreamDialogueScreenBuilder treeBuilder = new StreamDialogueScreenBuilder(this, TCRCoreMod.MOD_ID);
        DialogueComponentBuilder dBuilder = treeBuilder.getComponentBuildr();

        DialogNode root = new DialogNode(dBuilder.ans(0), dBuilder.opt(-3));

        if(PlayerDataManager.gameCleared.get(localPlayer)) {
            root = new DialogNode(dBuilder.ans(-1), dBuilder.opt(-3));
        }

        DialogNode whoAreU = new DialogNode(dBuilder.ans(1, TCREntities.CHRONOS_SOL.get().getDescription(), TCRItems.ARTIFACT_TICKET.get().getDescription().copy().withStyle(ChatFormatting.GOLD), TCRItems.ARTIFACT_TICKET.get().getDescription().copy().withStyle(ChatFormatting.GOLD)), dBuilder.opt(0))
                .addChild(root);

        DialogNode aboutChronos = new DialogNode(dBuilder.ans(2), dBuilder.opt(1, TCREntities.CHRONOS_SOL.get().getDescription()))
                .addChild(root);

        DialogNode aboutOrnn = new DialogNode(dBuilder.ans(3, TCREntities.ORNN.get().getDescription()), dBuilder.opt(1, TCREntities.ORNN.get().getDescription()))
                .addChild(root);

        DialogNode tradeArtifact = new DialogNode.FinalNode(dBuilder.opt(2), 1);
        DialogNode goToOverworld = new DialogNode.FinalNode(dBuilder.opt(3), 2);

        DialogNode gift = new DialogNode(dBuilder.ans(4), dBuilder.opt(4))
                .addLeaf(dBuilder.opt(5, ModEntities.NIGHTFURY.get().getDescription()), 3)
                .addLeaf(dBuilder.opt(5, ModEntities.DEADLYNADDER.get().getDescription()), 4)
//                .addLeaf(dBuilder.opt(5, ModEntities.GRONCKLE.get().getDescription()), 5)
                .addLeaf(dBuilder.opt(5, ModEntities.SKRILL.get().getDescription()), 6)
                .addLeaf(dBuilder.opt(5, ModEntities.HIDDEOUS_ZIPPLEBACK.get().getDescription()), 7)
                .addLeaf(dBuilder.opt(5, ModEntities.MONSTROUSNIGHTMARE.get().getDescription()), 8)
//                .addLeaf(dBuilder.opt(5, ModEntities.WHISPERING_DEATH.get().getDescription()), 9)
                ;

        if (currentQuest.equals(TCRQuests.TALK_TO_FERRY_GIRL_0)) {
            root.addChild(whoAreU);
        }

        //将龙养大
        if(currentQuest.equals(TCRQuests.TAME_DRAGON_BACK_TO_FERRY_GIRL)) {
            DialogNode tameDragonFinish = new DialogNode(dBuilder.ans(5), dBuilder.opt(6))
                    .addLeaf(dBuilder.opt(7), 10);
            root.addChild(tameDragonFinish);
            return treeBuilder.buildWith(root);
        }

        if(currentQuest.equals(TCRQuests.TAME_DRAGON_BACK_TO_FERRY_GIRL_2)) {
            Component manageKey = Component.keybind("key.find_me.manage");
            Component mountKey = Component.keybind("key.find_me.mount");

            DialogNode tutorialStep1 = new DialogNode(dBuilder.ans(8), dBuilder.opt(8));
            DialogNode tutorialStep2 = new DialogNode(dBuilder.ans(9, manageKey), dBuilder.opt(-1));
            DialogNode tutorialStep3 = new DialogNode(dBuilder.ans(10, mountKey), dBuilder.opt(-1));
            DialogNode tutorialStep4 = new DialogNode(dBuilder.ans(11, mountKey), dBuilder.opt(-1));
            DialogNode tutorialFinal = new DialogNode(dBuilder.ans(12), dBuilder.opt(-1))
                    .addChild(tutorialStep1)
                    .addLeaf(dBuilder.opt(-2), 11);

            tutorialStep1.addChild(tutorialStep2);
            tutorialStep2.addChild(tutorialStep3);
            tutorialStep3.addChild(tutorialStep4);
            tutorialStep4.addChild(tutorialFinal);

            return treeBuilder.buildWith(tutorialStep1);
        }

        if(!PlayerDataManager.gameCleared.get(localPlayer)) {
            if (PlayerDataManager.chronosTalked.get(localPlayer)) {
                root.addChild(aboutChronos);
            }
        }
        if (PlayerDataManager.ornnTalked.get(localPlayer)) {
            root.addChild(aboutOrnn);
        }

        if (!PlayerDataManager.ferryGirlGiftGet.get(localPlayer)) {
            root.addChild(gift);
        }

        root.addChild(tradeArtifact);

        if(canGoOverworld) {
            root.addChild(goToOverworld);
        }

        root.addLeaf(dBuilder.opt(-2));

        return treeBuilder.buildWith(root);
    }

    @Override
    public void handleNpcInteraction(ServerPlayer serverPlayer, int i) {
        TCRQuestManager.Quest currentQuest = TCRQuestManager.getCurrentQuest(serverPlayer);
        if (i == 1) {
            startTrade(serverPlayer);
        }

        if (i == 2) {
            if (PlayerDataManager.wayStoneInteracted.get(serverPlayer)) {
                //后续的搞到PlayerDimensionChanged事件来
                if (currentQuest.equals(TCRQuests.TALK_TO_FERRY_GIRL_0)) {
                    TCRQuests.TALK_TO_FERRY_GIRL_0.finish(serverPlayer);//先清理任务标记点，再传去主世界
                    TCRQuests.USE_LAND_RESONANCE_STONE.start(serverPlayer);
                    TCRQuests.TALK_TO_BOUNTIFUL_VILLAGER.start(serverPlayer);
                }
                //传送主世界
                ServerLevel level = serverPlayer.server.getLevel(Level.OVERWORLD);
                serverPlayer.changeDimension(level, new OverworldVillageTeleporter());
            } else {
                serverPlayer.displayClientMessage(TCRCoreMod.getInfo("need_to_unlock_waystone").withStyle(ChatFormatting.RED), false);
            }
        }

        EntityType<? extends Mob> type =  ModEntities.NIGHTFURY.get();
        if (i == 3) {
            type =  ModEntities.NIGHTFURY.get();
        }
        if (i == 4) {
            type =  ModEntities.DEADLYNADDER.get();
        }
        if (i == 5) {
            type =  ModEntities.GRONCKLE.get();
        }
        if (i == 6) {
            type =  ModEntities.SKRILL.get();
        }
        if (i == 7) {
            type =  ModEntities.HIDDEOUS_ZIPPLEBACK.get();
        }
        if (i == 8) {
            type =  ModEntities.MONSTROUSNIGHTMARE.get();
        }
        if (i == 9) {
            type =  ModEntities.WHISPERING_DEATH.get();
        }

        //获得龙龙，以及养大龙龙的任务
        if (i >= 3 && i <= 9) {
            EntityType<? extends Mob> finalType = type;
            FTBTeamUtils.onlineTeamMembersDoWithSelf(serverPlayer, member -> {
                Mob babyDragon = finalType.create(member.serverLevel());
                if (babyDragon != null) {
                    if (babyDragon instanceof DragonBase dragonBase) {
                        int inheritedVariant;
                        if (this.random.nextFloat() < 0.1F) {
                            String dragonTypeName = dragonBase.getDragonType().name().toLowerCase();
                            inheritedVariant = DragonVariantConfig.selectWeightedVariant(dragonTypeName, this.random);
                        } else {
                            inheritedVariant = 0;
                        }

                        dragonBase.setVariant(inheritedVariant);
                        try {
                            String dragonType = dragonBase.getDragonType().name().toLowerCase();
                            SpeciesGeneticsConfig geneticsConfig = SpeciesGeneticsConfigRegistry.getConfig(dragonType);
                            GeneticsSpawner spawner = new GeneticsSpawner(this.random);
                            GeneticsSpawner.SpawnResult result = spawner.generateWildGenotype(geneticsConfig, dragonType);
                            dragonBase.setGenotypeAndDraws(result.genotype, result.paletteDraws);
                            dragonBase.recalculateStats();
                        } catch (Exception e) {
                            TCRCoreMod.LOGGER.error("[GENETICS ERROR] Failed to generate genotype for {}: {}", dragonBase.getDragonType(), e.getMessage(), e);
                        }
                        dragonBase.tame(member);
                        dragonBase.setAge(-24000);
                        dragonBase.startSpawnAnimation();
                        dragonBase.setTamingRitualCompleted(true);
                        dragonBase.setAwaitingTamingRitual(false);
                        dragonBase.setTamingRitualTimer(0);
                        dragonBase.setCommand(2);
                        dragonBase.addAffection(member.getUUID(), 10);
                        DragonDiscoveryEventHandler.onDragonTamed(member, dragonBase);
                    }

                    babyDragon.moveTo(member.getX(), member.getY(), member.getZ(), this.random.nextFloat() * 360.0F, 0.0F);
                    member.serverLevel().addFreshEntity(babyDragon);
                    this.level().playSound(null, this.blockPosition(), SoundEvents.TURTLE_EGG_HATCH, SoundSource.NEUTRAL, 1.0F, 1.0F);
                }
                ItemUtils.addItemEntity(member, ModItems.BOOK_OF_DRAGONS.get().getDefaultInstance());
                ItemUtils.addItemEntity(member, TCRItems.DRAGON_FLUTE.get().getDefaultInstance());
                ItemUtils.addItemEntity(member, ModItems.RATTLE_STAFF.get().getDefaultInstance());
            });
            TCRQuests.TAME_DRAGON.start(serverPlayer, false);
            PlayerDataManager.ferryGirlGiftGet.put(serverPlayer, true);
        }

        //将龙养大
        if(i == 10) {
            FTBTeamUtils.onlineTeamMembersDoWithSelf(serverPlayer, member -> {
                ItemUtils.addItemEntity(member, Items.SADDLE.getDefaultInstance());
                ItemUtils.addItemEntity(member, DIItemRegistry.COLLAR_TAG.get().getDefaultInstance());
                ItemUtils.addItemEntity(member, com.kuzhi.findme.common.ModItems.NAME_PAPER.get().getDefaultInstance());
            });
            TCRQuests.TAME_DRAGON_BACK_TO_FERRY_GIRL.finish(serverPlayer);
            TCRQuests.TAME_DRAGON_BIND_DRAGON.start(serverPlayer);
        }

        //教学完成的对话
        if(i == 11) {
            TCRQuests.TAME_DRAGON_BACK_TO_FERRY_GIRL_2.finish(serverPlayer);
        }

        this.setConversingPlayer(null);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, this::deployAnimController));
    }

    protected <E extends FerryGirlEntity> PlayState deployAnimController(final AnimationState<E> state) {
        return state.setAndContinue(IDLE);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    /**
     * 开始交易
     * 需要改变交易表则去重写 {@link #getOffers()}
     */
    public void startTrade(ServerPlayer serverPlayer) {
        setTradingPlayer(serverPlayer);
        openTradingScreen(serverPlayer, Component.empty(), 1);
    }

    @Override
    public void setTradingPlayer(@Nullable Player player) {
        tradingPlayer = player;
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {
        return tradingPlayer;
    }

    @Override
    public @NotNull MerchantOffers getOffers() {
        return offersArtifact == null ? new MerchantOffers() : offersArtifact;
    }

    @Override
    public void overrideOffers(@NotNull MerchantOffers merchantOffers) {

    }

    @Override
    public void notifyTrade(@NotNull MerchantOffer merchantOffer) {

    }

    @Override
    public void notifyTradeUpdated(@NotNull ItemStack itemStack) {

    }

    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int i) {

    }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public @NotNull SoundEvent getNotifyTradeSound() {
        return SoundEvents.EXPERIENCE_ORB_PICKUP;
    }

    @Override
    public boolean isClientSide() {
        return level().isClientSide;
    }

    @Override
    public boolean removeWhenFarAway(double p_21542_) {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }
}
