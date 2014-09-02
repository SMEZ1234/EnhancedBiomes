package enhancedbiomes.handlers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.world.gen.MapGenBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.world.MapGenCavesEnhancedBiomes;
import enhancedbiomes.world.MapGenRavineEnhancedBiomes;

public class DamageHandler 
{
	@SubscribeEvent
	public void damaged(LivingHurtEvent e) {
    	if(e.source != DamageSource.cactus) return;
		System.out.println("Entity Damaged");
	}
	
	@SubscribeEvent
	public void damage(LivingAttackEvent e) {
    	if(e.source != DamageSource.cactus) return;
		System.out.println("Testing");
		if (e.entityLiving.isEntityInvulnerable())
        {
        	System.out.println("Failed at 1");
            return;
        }
        else if (e.entityLiving.worldObj.isRemote)
        {
        	System.out.println("Failed at 2");
            return;
        }
        else
        {
            if (e.entityLiving.getHealth() <= 0.0F)
            {
            	System.out.println("Failed at 3");
                return;
            }
            else if (e.source.isFireDamage() && e.entityLiving.isPotionActive(Potion.fireResistance))
            {
            	System.out.println("Failed at 4");
                return;
            }
            else
            {
            	System.out.println("Success");
            }
        }
		return;
	}
}
