package com.mattt90.vanillaextras.datagen;

import com.mattt90.vanillaextras.Registration;
import com.mattt90.vanillaextras.VanillaExtras;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class VeLanguageProvider extends LanguageProvider {

    public VeLanguageProvider(PackOutput output, String locale) {
        super(output, VanillaExtras.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(Registration.TEST_BLOCK.get(), "Test Block");
        add("tab.vanillaextras", "Vanilla Extras");
    }
}
