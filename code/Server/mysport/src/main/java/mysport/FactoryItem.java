package mysport;

public class FactoryItem {

    public static Item getInstanceItem(String str){

        if (str.equals("Terrain"))
            return new Terrain();
        return null;
    }
}
