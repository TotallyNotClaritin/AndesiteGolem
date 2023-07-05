package net.sobe.andesitegolem.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Iterator;
import java.util.List;


@SuppressWarnings("ALL")
public class AndesiteGolemEntity extends GolemEntity implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);

    private boolean playerCreation;

    public AndesiteGolemEntity(EntityType<? extends GolemEntity> entityType, World world)
    {
        super(entityType, world);
        this.playerCreation = false;
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23f);
    }

    protected void initGoals()
    {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.5, true));
        this.goalSelector.add(2, new WanderNearTargetGoal(this, 0.6, 32.0F));
        this.goalSelector.add(2, new WanderAroundPointOfInterestGoal(this, 0.6, false));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 0.7));
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.targetSelector.add(1, (new AndesiteGolemRevenge(this, new Class[0])).setGroupRevenge(new Class[0]));
    }

    public boolean canTarget(EntityType<?> type) {
        if (this.isPlayerCreated() && type == EntityType.PLAYER) {
            return false;
        } else {
            return type == EntityType.CREEPER ? false : super.canTarget(type);
        }
    }

    public boolean isPlayerCreated() {
        return playerCreation;
    }

    public void setPlayerCreated()
    {
        this.playerCreation = true;
    }


    private  <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.andesitegolem.walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.andesitegolem.idle", true));
        return PlayState.CONTINUE;

    }

    private PlayState attackPredicate(AnimationEvent event)
    {
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationState.Stopped))
        {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.andesitegolem.attack", false));
            this.handSwinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData)
    {
        animationData.addAnimationController(new AnimationController(this, "controller", 2, this::predicate));
        animationData.addAnimationController(new AnimationController(this, "attackController", 0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

}