package net.sobe.andesitegolem.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sobe.andesitegolem.AndesiteGolem;
import net.sobe.andesitegolem.entity.custom.AndesiteGolemEntity;

public class ModEntities {
    public static final EntityType<AndesiteGolemEntity> ANDESITEGOLEM = Registry.register
            (
                    Registry.ENTITY_TYPE, new Identifier(AndesiteGolem.MOD_ID, "andesitegolem"),
                    FabricEntityTypeBuilder.create(SpawnGroup.MISC, AndesiteGolemEntity::new)
                            .dimensions(EntityDimensions.fixed(0.5f,0.5f)).build());

}
