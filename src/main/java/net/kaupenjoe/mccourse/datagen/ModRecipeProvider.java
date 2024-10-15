package net.kaupenjoe.mccourse.datagen;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> ALEXANDRITE_SMELTABLES = List.of(ModItems.RAW_ALEXANDRITE.get(),
            ModBlocks.ALEXANDRITE_ORE.get(), ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(), ModBlocks.NETHER_ALEXANDRITE_ORE.get(),
            ModBlocks.END_STONE_ALEXANDRITE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        //Shaped recipes here

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ALEXANDRITE.get())
                .unlockedBy("has_alexandrite", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModItems.ALEXANDRITE.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.METAL_DETECTOR.get())
                .pattern(" A ")
                .pattern("A  ")
                .pattern("BB ")
                .define('A', Items.STICK)
                .define('B', Items.IRON_INGOT)
                .unlockedBy("has_iron_ingot", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Items.IRON_INGOT).build()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.ALEXANDRITE_PAXEL.get())
                .pattern("ABC")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('A', ModItems.ALEXANDRITE_AXE.get())
                .define('B', ModItems.ALEXANDRITE_SHOVEL.get())
                .define('C', ModItems.ALEXANDRITE_PICKAXE.get())
                .unlockedBy("has_iron_ingot", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Items.IRON_INGOT).build()))
                .save(pWriter);

        //Shapeless recipes here

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 9)
                .requires(ModBlocks.ALEXANDRITE_BLOCK.get())
                .unlockedBy("has_alexandrite_block", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModBlocks.ALEXANDRITE_BLOCK.get()).build()))
                .save(pWriter);

        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.RAW_ALEXANDRITE.get(), RecipeCategory.MISC, ModBlocks.RAW_ALEXANDRITE_BLOCK.get(),
                "mccourse:raw_alexandrite", "alexandrite","mccourse:raw_alexandrite_block", "alexandrite");

        oreSmelting(pWriter, ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 0.25f, 200, "alexandrite");
        oreBlasting(pWriter, ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 0.25f, 100, "alexandrite");

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALEXANDRITE_SLAB.get(), Ingredient.of(ModBlocks.ALEXANDRITE_SLAB.get()));
        stairBuilder(ModBlocks.ALEXANDRITE_STAIRS.get(), Ingredient.of(ModBlocks.ALEXANDRITE_BLOCK.get()));
        buttonBuilder(ModBlocks.ALEXANDRITE_BUTTON.get(), Ingredient.of(ModBlocks.ALEXANDRITE_BLOCK.get()));
        pressurePlateBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALEXANDRITE_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.ALEXANDRITE_BLOCK.get()));
        fenceBuilder(ModBlocks.ALEXANDRITE_FENCE.get(), Ingredient.of(ModBlocks.ALEXANDRITE_BLOCK.get()));
        fenceGateBuilder(ModBlocks.ALEXANDRITE_FENCE_GATE.get(), Ingredient.of(ModBlocks.ALEXANDRITE_BLOCK.get()));
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALEXANDRITE_FENCE_GATE.get(), Ingredient.of(ModBlocks.ALEXANDRITE_BLOCK.get()));
        doorBuilder(ModBlocks.ALEXANDRITE_DOOR.get(), Ingredient.of(ModBlocks.ALEXANDRITE_BLOCK.get()));
        trapdoorBuilder(ModBlocks.ALEXANDRITE_TRAPDOOR.get(), Ingredient.of(ModBlocks.ALEXANDRITE_BLOCK.get()));

        shovelBuilder(pWriter, ModItems.ALEXANDRITE.get(), "has_alexandrite", ModItems.ALEXANDRITE_SHOVEL.get());
        swordBuilder(pWriter, ModItems.ALEXANDRITE.get(), "has_alexandrite", ModItems.ALEXANDRITE_SWORD.get());
        axeBuilder(pWriter, ModItems.ALEXANDRITE.get(), "has_alexandrite", ModItems.ALEXANDRITE_AXE.get());
        pickaxeBuilder(pWriter, ModItems.ALEXANDRITE.get(), "has_alexandrite", ModItems.ALEXANDRITE_PICKAXE.get());
        hoeBuilder(pWriter, ModItems.ALEXANDRITE.get(), "has_alexandrite", ModItems.ALEXANDRITE_HOE.get());
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }
    protected static void swordBuilder(Consumer<FinishedRecipe> pWriter, ItemLike ingredient, String pCriterionName, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" S ")
                .define('A', ingredient)
                .define('S', Items.STICK)
                .unlockedBy(pCriterionName, inventoryTrigger(ItemPredicate.Builder.item().
                        of(ingredient).build()))
                .save(pWriter);
    }
    protected static void pickaxeBuilder(Consumer<FinishedRecipe> pWriter, ItemLike ingredient, String pCriterionName, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("AAA")
                .pattern(" S ")
                .pattern(" S ")
                .define('A', ingredient)
                .define('S', Items.STICK)
                .unlockedBy(pCriterionName, inventoryTrigger(ItemPredicate.Builder.item().
                        of(ingredient).build()))
                .save(pWriter);
    }
    protected static void axeBuilder(Consumer<FinishedRecipe> pWriter, ItemLike ingredient, String pCriterionName, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("AA ")
                .pattern("AS ")
                .pattern(" S ")
                .define('A', ingredient)
                .define('S', Items.STICK)
                .unlockedBy(pCriterionName, inventoryTrigger(ItemPredicate.Builder.item().
                        of(ingredient).build()))
                .save(pWriter);
    }
    protected static void shovelBuilder(Consumer<FinishedRecipe> pWriter, ItemLike ingredient, String pCriterionName, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern(" A ")
                .pattern(" S ")
                .pattern(" S ")
                .define('A', ingredient)
                .define('S', Items.STICK)
                .unlockedBy(pCriterionName, inventoryTrigger(ItemPredicate.Builder.item().
                        of(ingredient).build()))
                .save(pWriter);
    }
    protected static void hoeBuilder(Consumer<FinishedRecipe> pWriter, ItemLike ingredient, String pCriterionName, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("AA ")
                .pattern(" S ")
                .pattern(" S ")
                .define('A', ingredient)
                .define('S', Items.STICK)
                .unlockedBy(pCriterionName, inventoryTrigger(ItemPredicate.Builder.item().
                        of(ingredient).build()))
                .save(pWriter);
    }
    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer,
                                     List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime,
                            pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, MCCourseMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

}
