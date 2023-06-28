package net.sobe.andesitegolem.entity.client;

import net.minecraft.util.Identifier;
import net.sobe.andesitegolem.AndesiteGolem;
import net.sobe.andesitegolem.entity.custom.AndesiteGolemEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AndesiteGolemModel extends AnimatedGeoModel<AndesiteGolemEntity> {
    @Override
    public Identifier getModelLocation(AndesiteGolemEntity object) {
        return new Identifier(AndesiteGolem.MOD_ID, "geo/andesitegolem.geo.json");
    }

    @Override
    public Identifier getTextureLocation(AndesiteGolemEntity object) {
        return new Identifier(AndesiteGolem.MOD_ID, "textures/entity/andesitegolem/andesitegolem.png");
    }

    @Override
    public Identifier getAnimationFileLocation(AndesiteGolemEntity animatable) {
        return new Identifier(AndesiteGolem.MOD_ID, "animations/andesitegolem.json");
    }
}
