package net.sobe.andesitegolem.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.sobe.andesitegolem.AndesiteGolem;
import net.sobe.andesitegolem.entity.custom.AndesiteGolemEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AndesiteGolemRenderer extends GeoEntityRenderer<AndesiteGolemEntity> {
    public AndesiteGolemRenderer(EntityRendererFactory.Context ctx){
        super(ctx, new AndesiteGolemModel());
    }

    @Override
    public Identifier getTextureLocation(AndesiteGolemEntity instance)
    {
        return new Identifier(AndesiteGolem.MOD_ID, "textures/entity/andesitegolem/andesitegolem.png");

    }
}
