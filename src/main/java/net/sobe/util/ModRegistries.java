package net.sobe.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.sobe.andesitegolem.entity.ModEntities;
import net.sobe.andesitegolem.entity.custom.AndesiteGolemEntity;

public class ModRegistries {
    public static void registerMods()
    {
        registerAttributes();
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntities.ANDESITEGOLEM, AndesiteGolemEntity.setAttributes());
    }
}

