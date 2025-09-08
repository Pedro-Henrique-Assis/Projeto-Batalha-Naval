package utils;

public class Ansi {
    public static final String RESET = "\u001B[0m";
    public static final String RED   = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BOLD  = "\u001B[1m";

    public static String red(String s){   return RED + s + RESET; }
    public static String green(String s){ return GREEN + s + RESET; }
    public static String bold(String s){  return BOLD + s + RESET; }
}
