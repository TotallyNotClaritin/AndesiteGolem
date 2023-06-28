package net.sobe.andesitegolem.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.sobe.andesitegolem.entity.ModEntities;

import static software.bernie.example.registry.RegistryUtils.registerItem;

public class ModItems {

    public static final Item ANDESITE_GOLEM_SPAWN_EGG = registerItem("andesite_golem_spawn_egg",
            new SpawnEggItem(ModEntities.ANDESITEGOLEM,0x948e8d, 0x3b3635,
            new FabricItemSettings().group(ModItemGroup.ANDESITEGOLEM).maxCount(1)));

}
