package net.sobe.andesitegolem.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.sobe.andesitegolem.AndesiteGolem;

public class ModItemGroup {
    public static final ItemGroup ANDESITEGOLEM = FabricItemGroupBuilder.create(new Identifier(AndesiteGolem.MOD_ID, "Andesite Golem")).build();

}
