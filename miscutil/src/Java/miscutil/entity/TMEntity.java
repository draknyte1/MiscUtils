package miscutil.entity;

import net.minecraft.entity.EntityList;
import miscutil.MiscUtils;
import cpw.mods.fml.common.registry.EntityRegistry;

public class TMEntity {
	
	public static void mainRegistry(){
		registerEntity();
	}
	
	public static void registerEntity(){
		createEntity(EntityBloodSteelMob.class, "BloodSteelMob", 0xEC4545, 0x001EFF);
		createEntity(EntityBloodSteelHostileMob.class, "BloodSteelHostileMob", 0xE15050, 0xFFF700);
		
		//createEntity(EntityGrenade.class, "BloodSteelGrenade", 0x008521, 0x00FF08);
	}
	
	@SuppressWarnings("unchecked")
	public static void createEntity(@SuppressWarnings("rawtypes") Class entityClass, String entityName, int solidColour, int spotColour){
		int randomId = EntityRegistry.findGlobalUniqueEntityId();
		try {
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, randomId);
		EntityRegistry.registerModEntity(entityClass, entityName, randomId, MiscUtils.instance, 64, 1, true);
		createEgg(randomId, solidColour, spotColour);
		}
		catch (NullPointerException e){
			System.out.println("Entity Registration Failed");
		}
	}

	@SuppressWarnings("unchecked")
	private static void createEgg(int randomId, int solidColour, int spotColour) {
		EntityList.entityEggs.put(Integer.valueOf(randomId), new EntityList.EntityEggInfo(randomId, solidColour, spotColour));
	}
}
