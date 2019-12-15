package mysport;

public class FactoryItem {

    public Item getInstanceItem(String str){

        if (str.equals("Terrain"))
            return new Terrain();
        if( str.endsWith("Equipement")){
            return new Equipement();
        }
        return null;
    }
}
