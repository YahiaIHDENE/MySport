package mysport;

public class FactoryItem {

    public Item getInstanceItem(String str){

        if (str.equals("Terrain"))
            return new Terrain();
        if( str.equals("Equipement")){
            return new Equipement();
        }
        return null;
    }
}
