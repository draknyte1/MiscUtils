package binnie.extrabees.genetics.effect;

import binnie.Binnie;
import binnie.core.liquid.ManagerLiquid;
import binnie.extrabees.ExtraBees;
import binnie.extrabees.genetics.ExtraBeesFlowers;
import binnie.extrabees.proxy.ExtraBeesProxy;
import cofh.api.energy.IEnergyReceiver;
import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.IAlleleBeeEffect;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.apiculture.IArmorApiarist;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IBeeModifier;
import forestry.api.apiculture.IBeeRoot;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAlleleRegistry;
import forestry.api.genetics.IEffectData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;

public enum ExtraBeesEffect
  implements IAlleleBeeEffect
{
  ECTOPLASM,  ACID,  SPAWN_ZOMBIE,  SPAWN_SKELETON,  SPAWN_CREEPER,  LIGHTNING,  RADIOACTIVE,  METEOR,  HUNGER,  FOOD,  BLINDNESS,  CONFUSION,  FIREWORKS,  FESTIVAL,  BIRTHDAY,  TELEPORT,  GRAVITY,  THIEF,  WITHER,  WATER,  SLOW,  BonemealSapling,  BonemealFruit,  BonemealMushroom,  Power;
  
  String fx;
  public boolean combinable;
  public boolean dominant;
  public int id;
  private String uid;
  static List<Birthday> birthdays;
  
  private ExtraBeesEffect()
  {
    this.fx = "";
    this.uid = toString().toLowerCase();
    this.combinable = false;
    this.dominant = true;
  }
  
  public static void doInit()
  {
    BLINDNESS.setFX("blindness");
    FOOD.setFX("food");
    GRAVITY.setFX("gravity");
    THIEF.setFX("gravity");
    TELEPORT.setFX("gravity");
    LIGHTNING.setFX("lightning");
    METEOR.setFX("meteor");
    RADIOACTIVE.setFX("radioactive");
    WATER.setFX("water");
    WITHER.setFX("wither");
    for (ExtraBeesEffect effect : values()) {
      effect.register();
    }
  }
  
  private void setFX(String string)
  {
    this.fx = ("particles/" + string);
  }
  
  public void register()
  {
    AlleleManager.alleleRegistry.registerAllele(this);
  }
  
  public boolean isCombinable()
  {
    return this.combinable;
  }
  
  public IEffectData validateStorage(IEffectData storedData)
  {
    return storedData;
  }
  
  public String getName()
  {
    return ExtraBees.proxy.localise("effect." + name().toString().toLowerCase() + ".name");
  }
  
  public boolean isDominant()
  {
    return this.dominant;
  }
  
  public void spawnMob(World world, int x, int y, int z, String name)
  {
    if (anyPlayerInRange(world, x, y, z, 16))
    {
      double var1 = x + world.rand.nextFloat();
      double var2 = y + world.rand.nextFloat();
      double var3 = z + world.rand.nextFloat();
      world.spawnParticle("smoke", var1, var2, var3, 0.0D, 0.0D, 0.0D);
      world.spawnParticle("flame", var1, var2, var3, 0.0D, 0.0D, 0.0D);
      EntityLiving var4 = (EntityLiving)EntityList.createEntityByName(name, world);
      if (var4 == null) {
        return;
      }
      int var5 = world.getEntitiesWithinAABB(var4.getClass(), AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1).expand(8.0D, 4.0D, 8.0D)).size();
      if (var5 >= 6) {
        return;
      }
      if (var4 != null)
      {
        double var6 = x + (world.rand.nextDouble() - world.rand.nextDouble()) * 4.0D;
        double var7 = y + world.rand.nextInt(3) - 1;
        double var8 = z + (world.rand.nextDouble() - world.rand.nextDouble()) * 4.0D;
        var4.setLocationAndAngles(var6, var7, var8, world.rand.nextFloat() * 360.0F, 0.0F);
        if (var4.getCanSpawnHere())
        {
          world.spawnEntityInWorld(var4);
          world.playAuxSFX(2004, x, y, z, 0);
          var4.spawnExplosionParticle();
        }
      }
    }
  }
  
  private boolean anyPlayerInRange(World world, int x, int y, int z, int distance)
  {
    return world.getClosestPlayer(x + 0.5D, y + 0.5D, z + 0.5D, distance) != null;
  }
  
  public static void doAcid(World world, int x, int y, int z)
  {
    Block block = world.getBlock(x, y, z);
    if ((block == Blocks.cobblestone) || (block == Blocks.stone)) {
      world.setBlock(x, y, z, Blocks.gravel, 0, 0);
    } else if (((block == Blocks.dirt ? 1 : 0) | (block == Blocks.grass ? 1 : 0)) != 0) {
      world.setBlock(x, y, z, Blocks.sand, 0, 0);
    }
  }
  
  public String getUID()
  {
    return "extrabees.effect." + this.uid;
  }
  
  public IEffectData doEffect(IBeeGenome genome, IEffectData storedData, IBeeHousing housing)
  {
    World world = housing.getWorld();
    ChunkCoordinates housingCoords = housing.getCoordinates();
    int xHouse = housingCoords.posX;
    int yHouse = housingCoords.posY;
    int zHouse = housingCoords.posZ;
    int[] area = getModifiedArea(genome, housing);
    int xd = 1 + area[0] / 2;
    int yd = 1 + area[1] / 2;
    int zd = 1 + area[2] / 2;
    int x1 = xHouse - xd + world.rand.nextInt(2 * xd + 1);
    int y1 = yHouse - yd + world.rand.nextInt(2 * yd + 1);
    int z1 = zHouse - zd + world.rand.nextInt(2 * zd + 1);
    EntityLivingBase entity;
    EntityFireworkRocket var11;
    switch (1.$SwitchMap$binnie$extrabees$genetics$effect$ExtraBeesEffect[ordinal()])
    {
    case 3: 
      if (world.rand.nextInt(100) < 4)
      {
        if ((world.isAirBlock(x1, y1, z1)) && ((world.isBlockNormalCubeDefault(x1, y1 - 1, z1, false)) || (world.getBlock(x1, y1 - 1, z1) == ExtraBees.ectoplasm))) {
          world.setBlock(x1, y1, z1, ExtraBees.ectoplasm, 0, 0);
        }
        return null;
      }
      break;
    case 4: 
      if (world.rand.nextInt(100) < 6) {
        doAcid(world, x1, y1, z1);
      }
      break;
    case 5: 
      if (world.rand.nextInt(200) < 2) {
        spawnMob(world, x1, y1, z1, "Zombie");
      }
      break;
    case 6: 
      if (world.rand.nextInt(200) < 2) {
        spawnMob(world, x1, y1, z1, "Skeleton");
      }
      break;
    case 7: 
      if (world.rand.nextInt(200) < 2) {
        spawnMob(world, x1, y1, z1, "Creeper");
      }
      break;
    case 8: 
      if ((world.rand.nextInt(100) < 1) && (world.canBlockSeeTheSky(x1, y1, z1)) && ((world instanceof WorldServer))) {
        ((WorldServer)world).addWeatherEffect(new EntityBeeLightning(world, x1, y1, z1));
      }
      break;
    case 9: 
      if ((world.rand.nextInt(100) < 1) && (world.canBlockSeeTheSky(x1, y1, z1))) {
        ((WorldServer)world).spawnEntityInWorld(new EntitySmallFireball(world, x1, y1 + 64, z1, 0.0D, -0.6D, 0.0D));
      }
      break;
    case 10: 
      for (EntityLivingBase entity : getEntities(EntityLivingBase.class, genome, housing))
      {
        int damage = 4;
        if ((entity instanceof EntityPlayer))
        {
          int count = wearsItems((EntityPlayer)entity);
          if (count > 3) {
            continue;
          }
          if (count > 2) {
            damage = 1;
          } else if (count > 1) {
            damage = 2;
          } else if (count > 0) {
            damage = 3;
          }
        }
        entity.attackEntityFrom(DamageSource.generic, damage);
      }
      break;
    case 11: 
      for (EntityLivingBase entity : getEntities(EntityLivingBase.class, genome, housing)) {
        if ((entity instanceof EntityPlayer))
        {
          EntityPlayer player = (EntityPlayer)entity;
          player.getFoodStats().addStats(2, 0.2F);
        }
      }
      break;
    case 12: 
      for (EntityLivingBase entity : getEntities(EntityLivingBase.class, genome, housing)) {
        if ((entity instanceof EntityPlayer))
        {
          EntityPlayer player = (EntityPlayer)entity;
          if (world.rand.nextInt(4) >= wearsItems(player))
          {
            player.getFoodStats().addExhaustion(4.0F);
            player.addPotionEffect(new PotionEffect(Potion.hunger.id, 100));
          }
        }
      }
      break;
    case 13: 
      for (EntityLivingBase entity : getEntities(EntityLivingBase.class, genome, housing)) {
        if ((entity instanceof EntityPlayer))
        {
          EntityPlayer player = (EntityPlayer)entity;
          if (world.rand.nextInt(4) >= wearsItems(player)) {
            player.addPotionEffect(new PotionEffect(Potion.blindness.id, 200));
          }
        }
      }
      break;
    case 14: 
      for (EntityLivingBase entity : getEntities(EntityLivingBase.class, genome, housing)) {
        if ((entity instanceof EntityPlayer))
        {
          EntityPlayer player = (EntityPlayer)entity;
          if (world.rand.nextInt(4) >= wearsItems(player)) {
            player.addPotionEffect(new PotionEffect(Potion.weakness.id, 200));
          }
        }
      }
      break;
    case 15: 
      for (??? = getEntities(EntityLivingBase.class, genome, housing).iterator(); ???.hasNext();)
      {
        entity = (EntityLivingBase)???.next();
        if ((entity instanceof EntityPlayer))
        {
          EntityPlayer player = (EntityPlayer)entity;
          if (world.rand.nextInt(4) >= wearsItems(player)) {
            player.addPotionEffect(new PotionEffect(Potion.confusion.id, 200));
          }
        }
      }
      break;
    case 1: 
    case 2: 
    case 16: 
      if (world.rand.nextInt(this == FIREWORKS ? 8 : 12) < 1)
      {
        FireworkCreator.Firework firework = new FireworkCreator.Firework();
        switch (1.$SwitchMap$binnie$extrabees$genetics$effect$ExtraBeesEffect[ordinal()])
        {
        case 1: 
          firework.setShape(FireworkCreator.Shape.Star);
          firework.addColor(16768256);
          for (Birthday birthday : birthdays) {
            if (birthday.isToday())
            {
              firework.addColor(16711680);
              firework.addColor(65280);
              firework.addColor(255);
              firework.setTrail();
              break;
            }
          }
        case 2: 
          firework.setShape(FireworkCreator.Shape.Ball);
          firework.addColor(genome.getPrimary().getIconColour(0));
          firework.addColor(genome.getPrimary().getIconColour(0));
          firework.addColor(genome.getPrimary().getIconColour(1));
          firework.addColor(genome.getSecondary().getIconColour(0));
          firework.addColor(genome.getSecondary().getIconColour(0));
          firework.addColor(genome.getPrimary().getIconColour(1));
          firework.setTrail();
        }
        var11 = new EntityFireworkRocket(world, x1, y1, z1, firework.getFirework());
        if (world.canBlockSeeTheSky(x1, y1, z1)) {
          ((WorldServer)world).spawnEntityInWorld(var11);
        }
      }
      break;
    case 17: 
      Object entities2 = getEntities(Entity.class, genome, housing);
      for (Entity entity2 : (List)entities2)
      {
        float entityStrength = 1.0F;
        if ((entity2 instanceof EntityPlayer)) {
          entityStrength *= 100.0F;
        }
        double dx = x1 - entity2.posX;
        double dy = y1 - entity2.posY;
        double dz = z1 - entity2.posZ;
        if (dx * dx + dy * dy + dz * dz < 2.0D) {
          return null;
        }
        double strength = 0.5D / (dx * dx + dy * dy + dz * dz) * entityStrength;
        entity2.addVelocity(dx * strength, dy * strength, dz * strength);
      }
      break;
    case 18: 
      Object entities3 = getEntities(EntityPlayer.class, genome, housing);
      for (EntityPlayer entity3 : (List)entities3)
      {
        double dx = x1 - entity3.posX;
        double dy = y1 - entity3.posY;
        double dz = z1 - entity3.posZ;
        if (dx * dx + dy * dy + dz * dz < 2.0D) {
          return null;
        }
        double strength = 0.5D / (dx * dx + dy * dy + dz * dz);
        entity3.addVelocity(-dx * strength, -dy * strength, -dz * strength);
      }
      break;
    case 19: 
      if (world.rand.nextInt(80) > 1) {
        return null;
      }
      Object entities4 = getEntities(Entity.class, genome, housing);
      if (((List)entities4).size() == 0) {
        return null;
      }
      Entity entity4 = (Entity)((List)entities4).get(world.rand.nextInt(((List)entities4).size()));
      if (!(entity4 instanceof EntityLiving)) {
        return null;
      }
      float jumpDist = 5.0F;
      if (y1 < 4) {
        y1 = 4;
      }
      if ((!world.isAirBlock(x1, y1, z1)) || (!world.isAirBlock(x1, y1 + 1, z1))) {
        return null;
      }
      ((EntityLiving)entity4).setPositionAndUpdate(x1, y1, z1);
      ((EntityLiving)entity4).addPotionEffect(new PotionEffect(Potion.confusion.id, 160, 10));
      break;
    case 20: 
      if (world.rand.nextInt(120) > 1) {
        return null;
      }
      TileEntity tile = world.getTileEntity(x1, y1, z1);
      if ((tile instanceof IFluidHandler)) {
        ((IFluidHandler)tile).fill(ForgeDirection.UP, Binnie.Liquid.getLiquidStack("water", 100), true);
      }
      break;
    case 21: 
      if (world.rand.nextInt(20) > 1) {
        return null;
      }
      if (ExtraBeesFlowers.Sapling.isAcceptedFlower(world, null, x1, y1, z1)) {
        ItemDye.applyBonemeal(new ItemStack(Blocks.dirt, 1), world, x1, y1, z1, (EntityPlayer)null);
      }
      break;
    case 22: 
      if (world.rand.nextInt(20) > 1) {
        return null;
      }
      if (ExtraBeesFlowers.Fruit.isAcceptedFlower(world, null, x1, y1, z1)) {
        ItemDye.applyBonemeal(new ItemStack(Blocks.dirt, 1), world, x1, y1, z1, (EntityPlayer)null);
      }
      break;
    case 23: 
      if (world.rand.nextInt(20) > 1) {
        return null;
      }
      if ((world.getBlock(x1, y1, z1) == Blocks.brown_mushroom) || (world.getBlock(x1, y1, z1) == Blocks.red_mushroom)) {
        ItemDye.applyBonemeal(new ItemStack(Blocks.dirt, 1), world, x1, y1, z1, (EntityPlayer)null);
      }
      break;
    case 24: 
      TileEntity tile2 = world.getTileEntity(x1, y1, z1);
      if ((tile2 instanceof IEnergyReceiver)) {
        ((IEnergyReceiver)tile2).receiveEnergy(ForgeDirection.getOrientation(0), 5, true);
      }
      break;
    }
    return null;
  }
  
  protected int[] getModifiedArea(IBeeGenome genome, IBeeHousing housing)
  {
    int[] territory;
    int[] area = territory = genome.getTerritory();
    int n = 0;
    IBeeModifier beeModifier = BeeManager.beeRoot.createBeeHousingModifier(housing);
    territory[0] *= (int)(beeModifier.getTerritoryModifier(genome, 1.0F) * 3.0F);
    int[] array = area;
    int n2 = 1;
    array[1] *= (int)(beeModifier.getTerritoryModifier(genome, 1.0F) * 3.0F);
    int[] array2 = area;
    int n3 = 2;
    array2[2] *= (int)(beeModifier.getTerritoryModifier(genome, 1.0F) * 3.0F);
    if (area[0] < 1) {
      area[0] = 1;
    }
    if (area[1] < 1) {
      area[1] = 1;
    }
    if (area[2] < 1) {
      area[2] = 1;
    }
    return area;
  }
  
  public IEffectData doFX(IBeeGenome genome, IEffectData storedData, IBeeHousing housing)
  {
    int[] territory;
    int[] area = territory = genome.getTerritory();
    int n = 0;
    IBeeModifier beeModifier = BeeManager.beeRoot.createBeeHousingModifier(housing);
    territory[0] *= (int)beeModifier.getTerritoryModifier(genome, 1.0F);
    int[] array = area;
    int n2 = 1;
    array[1] *= (int)beeModifier.getTerritoryModifier(genome, 1.0F);
    int[] array2 = area;
    int n3 = 2;
    array2[2] *= (int)beeModifier.getTerritoryModifier(genome, 1.0F);
    if (area[0] < 1) {
      area[0] = 1;
    }
    if (area[1] < 1) {
      area[1] = 1;
    }
    if (area[2] < 1) {
      area[2] = 1;
    }
    ChunkCoordinates coords = housing.getCoordinates();
    

    return storedData;
  }
  
  public String getFX()
  {
    return this.fx;
  }
  
  public <T extends Entity> List<T> getEntities(Class<T> eClass, IBeeGenome genome, IBeeHousing housing)
  {
    int[] area = genome.getTerritory();
    ChunkCoordinates coords = housing.getCoordinates();
    int[] offset = { -Math.round(area[0] / 2), -Math.round(area[1] / 2), -Math.round(area[2] / 2) };
    int[] min = { coords.posX + offset[0], coords.posY + offset[1], coords.posZ + offset[2] };
    int[] max = { coords.posX + offset[0] + area[0], coords.posY + offset[1] + area[1], coords.posZ + offset[2] + area[2] };
    AxisAlignedBB box = AxisAlignedBB.getBoundingBox(min[0], min[1], min[2], max[0], max[1], max[2]);
    return housing.getWorld().getEntitiesWithinAABB(eClass, box);
  }
  
  public static boolean wearsHelmet(EntityPlayer player)
  {
    ItemStack armorItem = player.inventory.armorInventory[3];
    return (armorItem != null) && ((armorItem.getItem() instanceof IArmorApiarist));
  }
  
  public static boolean wearsChest(EntityPlayer player)
  {
    ItemStack armorItem = player.inventory.armorInventory[2];
    return (armorItem != null) && ((armorItem.getItem() instanceof IArmorApiarist));
  }
  
  public static boolean wearsLegs(EntityPlayer player)
  {
    ItemStack armorItem = player.inventory.armorInventory[1];
    return (armorItem != null) && ((armorItem.getItem() instanceof IArmorApiarist));
  }
  
  public static boolean wearsBoots(EntityPlayer player)
  {
    ItemStack armorItem = player.inventory.armorInventory[0];
    return (armorItem != null) && ((armorItem.getItem() instanceof IArmorApiarist));
  }
  
  public static int wearsItems(EntityPlayer player)
  {
    int count = 0;
    if (wearsHelmet(player)) {
      count++;
    }
    if (wearsChest(player)) {
      count++;
    }
    if (wearsLegs(player)) {
      count++;
    }
    if (wearsBoots(player)) {
      count++;
    }
    return count;
  }
  
  public String getUnlocalizedName()
  {
    return getUID();
  }
  
  static
  {
    (ExtraBeesEffect.birthdays = new ArrayList()).add(new Birthday(3, 10, "Binnie", null));
  }
  
  public static class Birthday
  {
    int day;
    int month;
    String name;
    
    public boolean isToday()
    {
      return (Calendar.getInstance().get(5) == this.month) && (Calendar.getInstance().get(2) == this.day);
    }
    
    public String getName()
    {
      return this.name;
    }
    
    private Birthday(int day, int month, String name)
    {
      this.day = day;
      this.month = (month + 1);
      this.name = name;
    }
  }
}
