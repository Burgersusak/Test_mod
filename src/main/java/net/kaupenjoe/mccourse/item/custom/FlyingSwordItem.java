package net.kaupenjoe.mccourse.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class FlyingSwordItem extends SwordItem {
    public FlyingSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (entity instanceof LivingEntity livingEntity && !player.hasEffect(MobEffects.LEVITATION) &&
                !((LivingEntity) entity).hasEffect(MobEffects.LEVITATION)) {

            livingEntity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20, 99), player);
            player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20, 99), player);
        }

        return super.onLeftClickEntity(stack, player, entity);
    }
}
