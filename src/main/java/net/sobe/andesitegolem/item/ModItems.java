package net.sobe.andesitegolem.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sobe.andesitegolem.AndesiteGolem;
import net.sobe.andesitegolem.entity.ModEntities;

public class ModItems {

    public static final Item ANDESITE_GOLEM_SPAWN_EGG = registerItem("andesite_golem_spawn_egg",
            new SpawnEggItem(ModEntities.ANDESITEGOLEM,0x948e8d, 0x3b3635,
            new FabricItemSettings().group(ModItemGroup.ANDESITEGOLEM).maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(AndesiteGolem.MOD_ID, name), item);
    }

    public static void registerModItems() {
        AndesiteGolem.LOGGER.info("Registering Mod Items for " + AndesiteGolem.MOD_ID);
    }
}
