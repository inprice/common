package io.inprice.common.meta;

public enum Position {
  
  LOWEST,
  LOWER,
  AVERAGE,
  HIGHER,
  HIGHEST;

  public static Position getByOrdinal(int ordinal) {
    return Position.values()[ordinal];
  }

}