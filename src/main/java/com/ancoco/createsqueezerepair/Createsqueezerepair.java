package com.ancoco.createsqueezerepair;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(Createsqueezerepair.MODID)
public class Createsqueezerepair {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "createsqueezerepair";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "createsqueezerepair" namespace
    // Add the example block item to the building blocks tab


    // You can use SubscribeEvent and let the Event Bus discover methods to call


    public Createsqueezerepair() {
        LOGGER.info("CreateSqueezeRepair initialized with Mixin for Create mod");
    }

}
