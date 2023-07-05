package net.sobe.andesitegolem.entity.custom;

import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;

import java.util.Iterator;
import java.util.List;

public class AndesiteGolemRevenge extends RevengeGoal
{
    private Class[] noHelpTypes;

    public AndesiteGolemRevenge(AndesiteGolemEntity mob, Class<?>... noRevengeTypes) {
        super(mob, noRevengeTypes);
        noHelpTypes = noRevengeTypes;
    }

    @Override
    protected void callSameTypeForRevenge() {
        double d = this.getFollowRange();
        Box box = Box.from(this.mob.getPos()).expand(d, 10.0, d);
        List<? extends MobEntity> list = this.mob.world.getEntitiesByClass(this.mob.getClass(), box, EntityPredicates.EXCEPT_SPECTATOR);
        Iterator var5 = list.iterator();

        while(true) {
            MobEntity mobEntity;
            boolean bl;
            do {
                do {
                    do {
                        do {
                            do {
                                if (!var5.hasNext()) {
                                    return;
                                }

                                mobEntity = (MobEntity)var5.next();
                            } while(this.mob == mobEntity);
                        } while(mobEntity.getTarget() != null);
                    } while(this.mob instanceof TameableEntity && ((TameableEntity)this.mob).getOwner() != ((TameableEntity)mobEntity).getOwner());
                } while(mobEntity.isTeammate(this.mob.getAttacker()));

                if (this.noHelpTypes == null) {
                    break;
                }

                bl = false;
                Class[] var8 = this.noHelpTypes;
                int var9 = var8.length;

                for(int var10 = 0; var10 < var9; ++var10) {
                    Class<?> class_ = var8[var10];
                    if (mobEntity.getClass() == class_) {
                        bl = true;
                        break;
                    }
                    else if(mobEntity.getClass() == AndesiteGolemEntity.class) {
                        if(((AndesiteGolemEntity) mobEntity).isPlayerCreated())
                        {
                            bl = true;
                            break;
                    }
                    }
                }
            } while(bl);

            if(!((AndesiteGolemEntity) mobEntity).isPlayerCreated())
            {
            this.setMobEntityTarget(mobEntity, this.mob.getAttacker());
            }
        }
    }
}
