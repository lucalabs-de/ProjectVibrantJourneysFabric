package de.lucalabs.vibrantjourneys.blocks.properties;


import net.minecraft.util.StringIdentifiable;

public enum BeachedKelpShape implements StringIdentifiable {
  TOP("top"),
  STRAIGHT("straight"),
  CURVED("curved"),
  END("end");

  private final String name;

  private BeachedKelpShape(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }

  @Override
  public String asString() {
    return this.name;
  }
}