package com.mattt90.vanillaextras.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import com.mattt90.vanillaextras.Registration;

public class VeRecipes extends RecipeProvider {

    public VeRecipes(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.TEST_BLOCK.get(), 4)
                .pattern("ddd")
                .pattern("ddd")
                .pattern("ddd")
                .define('d', Items.DIRT)
                .group("vanillaextras")
                .unlockedBy("has_dirt", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(Items.DIRT).build()))
                .save(consumer);
    }
}
