package me.asakura_kukii.siegeweapon.utility.coodinate;

public class Vector3D {
    public double x;
    public double y;
    public double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3D string2Vector(String s) {
        double x = 0.0;
        double y = 0.0;
        double z = 0.0;
        if (s.contains("^")) {
            if (s.split("\\^").length==3) {
                int count = 0;
                for (String s2 : s.split("\\^")) {
                    try {
                        if (count == 0) {
                            x = Double.parseDouble(s2);
                        }
                        if (count == 1) {
                            y = Double.parseDouble(s2);
                        }
                        if (count == 2) {
                            z = Double.parseDouble(s2);
                        }
                        count++;
                    } catch (Exception ignored) {
                        return null;
                    }
                }
                return new Vector3D(x,y,z);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
