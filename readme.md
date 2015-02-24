This foray into Java/Minecraft development has stopped for the time being due to work committments.

      Ever decide you hate the biome that you are in after you built your entire base?  Don't you hate how the ores that you have mined don't regenerate?
   Never fear TerraCraft is here!
   
   But seriously, this mod is designed in mind to replace the biome that you are with the corresponding blocks that match the newly chosen biome.  This mod is designed
   using the CoFHLib and the design and implementation is inspired by KingLemming and ThermalExpansion.  The goal of this mod is to give the players a properly balanced,
   grief proof way to alter the biomes that they have created.  Additionally, my goal is to provide unique tools to the players that aren't based on power gen or ore processing.
   
   Currently I am in an extremely alpha state, however I have already created the algorithm that replaces the biome.  The next month or so will be flushing out the mod to include
   all the tools, blocks, textures and gui's to make this a first class mod.  Once the first build goes Alpha.  I will be closing the source of the mod.  I have been in contact with
   a texture guy so hopefully I will have an alpha build within a week or so.
   
   Furthermore, I will be creating a 1.7.2 server that people are welcome to come and play one once I get an Alpha version of the mod up.  Now to the good stuff

Current Features:

	- Two new ores:
		- Ecopoiesis - this ore provides the structure for regenerating the terrain.  Even the word itself means "house building" and will provide the 
					   stone, dirt and sand necessary to terraform the world of Minecraft.
		- Genesis - this ore provides the goodies, or the upper tier of terraforming.  This ore has base properties of every registered ore in the dictionary
					and provide chances of replacing parts of the structure with these new properties.
		
	- New tools
		- Each ore has the whole complement of tools that can be made out of them.  
		
	- New Fluids
		- Each ore can be turned into a fluid which will be consumed by the biome changing blocks.  
		
	- Basic Biome Changing Block
		- The algorithm that changes the biomes has been developed and inserted into this recipeless block.  This block in its current form has been removed and will replaced with
		  a single block that requires power, eco fluid to simply replace the biome into a random choice of four biome types.
		  
	- Config Customizing
		- Each feature/block/effect will be customizable via the server config.  THis is to allow server operators the ability to tweak and alter this mod to their needs.
		
In Development:

	- Basic Biome Changing Block
		- A single block that requires power, eco ore to simply replace the biome into a random choice of four biome types.
	
	- Tool Effects
		- Each tool of each ore type will have special effects that will be implemented to provide significant incentive for the player to use the tools.
		
	- Fluid Effects:
        - Each fluid will have effects that will alter the landscape in some terraforming way.
    
    - Terraforming Effects:
        - While terraforming is in progress certain effects (sky color/lightning) will be present to the user as well.
        
    - Armor:
        - While each of the ores have their own toolset, I will be creating ONE armor set that is a combination of each ore.  This armor set will have special effects that complement the tools as well.
    

Upcoming features:

	- Advanced Biome Changing Block
		- This will the middle tier of the biome changing block.  This will be a multiblock structure that takes in power and eco fluid to regenerate biomes.  Custom
		  renderings and guis will be provided to the user to select the biome types that it can be terraformed into.
		  
	- Extreme Biome Changing Block 
		- This is essentially the same as the block above, however it will intake Genesis fluid to allow the user to regenerate the ore nodes in each chunk/biome.  The one caveat
		  is that for each time this block is ran, the number of nodes for each ore type will have a chance to decrease by one.  Essentially giving diminishing returns.
	
		
	- Thermal Foundation Integration:
		- If the mod detects that thermal foundation is integrated, I plan on providing a config option to integrate my recipes with TF to utilize their ores.
		
		
	     
    - Fluid container:
        - Each fluid will need to have a fluid container to hold the fluid before use in the more advanced blocks.
        
    - Fluid maker:
        - A fluid maker block will be needed to convert the ores into workable fluids for the machine.
    
    - Fluid pipes:
        - Need a set of pipes to go from fluid maker -> fluid container -> fluid tank.
		

Potential Features:

	- Baubles integration:
		- Now I know everyone won't want to use the armor because they would like to use a plethora of alternatives, so I am considering adding the Baubles API such that trinkets can be created to 
		  complement the players favorite armor with the benefits of TerraCraft effects.  The will of course be of a lesser potency to not render the armor useless.
		  
		
		
Current textures:

	- Genesis Tools
		- Hoe
			- Effect: When harvesting crops, each crop has a 3% chance of dropping a golden version, (if exists) or doubles the seed output.  
		- Sword
			- Effect: When attacking an entity, has a 3% chance of applying an enchantment effect to any unenchanted armor player.
		- Sickle
			- Effect: When harvesting leaves, each leaf has a 3% chance of dropping a golden fruit in addition to a 30% chance of dropping random dye colors.
		- Axe
			- Effect: On right click, the axe will spawn & plant a random sapling if available on that block.
		- Pickaxe
			- Effect: When mining genesis ore, each block in a 3x3 pattern around the ore has a 3% chance to turn into a block of ingots. (DONE)
		- Shovel
			- Effect: When shoveling has 3% chance to turn natural dirt blocks around it into blocks of ore. (DONE)
		
	- Ecopoiesis Tools
		- Hoe
			- Effect: TBA
		- Sword
			- Effect: TBA
		- Sickle
			- Effect: TBA
		- Axe
			- Effect: TBA
		- Pickaxe
			- Effect: TBA
		- Shovel
			- Effect: TBA
	
	- Genesis Ore-related
		- Ore Block
		- Block
		- Ingot
		- Dust
		- Nugget
		- Gear
		- Fluid
			- Effect: TBA
		- Bucket
	
	- Ecopoiesis Ore-related
		- Ore Block
		- Block
		- Ingot
		- Dust
		- Nugget
		- Gear
		- Fluid
			- Effect:  When a bucket of the fluid is dropped into the world, the fluid will turn to stone and generate random pockets of dirt/gravel/clay/coal/bedrock(if less than y-level 5). (DONE)
		- Bucket

	Ore Processing Thoughts:
		- Make the ore more common in the world, but add levels between the genesis/eco ingots/fluid and the ore itself.  The reasoning for this is that the effects are dependent upon using tools of the various ores. 
		  If the ore is so rare, then it would be used for only terraforming and not actually the tools.  My current thought is to allow the tools to be made out of "raw <ore name> ingot" as well as a diamond embedded
		  stick. Then allow the user to smelt the raw genesis ore 
		



