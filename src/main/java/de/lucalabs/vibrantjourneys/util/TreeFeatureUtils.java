package de.lucalabs.vibrantjourneys.util;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class TreeFeatureUtils {

    public static boolean isIn(Set<ChanceBiomeEntry> Set, Identifier biome) {
        for (ChanceBiomeEntry entry : Set) {
            if (entry.biomeName().equals(biome.toString())) {
                return true;
            }
        }
        return false;
    }

    public static ChanceBiomeEntry entry(String biomeName, int chance) {
        return new ChanceBiomeEntry(biomeName, chance);
    }

    public static int getChance(String biomeName, Set<ChanceBiomeEntry> data) {
        for (ChanceBiomeEntry entry : data) {
            if (entry.biomeName().equals(biomeName))
                return entry.chance();
        }
        return -1;
    }

    public static void serializeAndLoad(String name, String loc, Set<ChanceBiomeEntry> defaults, Set<ChanceBiomeEntry> data) {
        Path path = FabricLoader.getInstance()
                .getConfigDir()
                .resolve(ProjectVibrantJourneys.MOD_ID)
                .resolve(loc)
                .resolve(name + ".json");

        if (!path.toFile().exists()) {
            try {
                Files.createDirectories(path.getParent());
                Files.write(path, new GsonBuilder().setPrettyPrinting().create().toJson(defaults).getBytes());
            } catch (Exception e) {
                ProjectVibrantJourneys.LOGGER.error(e.toString());
            }
        }

        try {
            String input = Files.readString(path);
            JsonArray array = JsonParser.parseString(input).getAsJsonArray();

            array.forEach((element) -> {
                JsonObject object = element.getAsJsonObject();
                String biomeName = object.getAsJsonPrimitive("biomeName").getAsString();
                int chance = object.getAsJsonPrimitive("chance").getAsInt();

                data.add(new ChanceBiomeEntry(biomeName, chance));
            });
        } catch (Exception e) {
            ProjectVibrantJourneys.LOGGER.error(e.toString());
        }
    }

    public record ChanceBiomeEntry(String biomeName, int chance) {
        public static final Codec<ChanceBiomeEntry> CODEC = RecordCodecBuilder.create((builder) -> builder.group(
                Codec.STRING.fieldOf("biomeName").forGetter(ChanceBiomeEntry::biomeName),
                Codec.intRange(0, 100).fieldOf("chance").forGetter(ChanceBiomeEntry::chance)
        ).apply(builder, ChanceBiomeEntry::new));
    }
}