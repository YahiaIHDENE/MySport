package mysport;

public class RechercheBuilder {

    private String querry;
    private String typeItem;
    int count = 0;

    public RechercheBuilder(){
        count = 0;
        querry = " SELECT * FROM myDb.t_annonce INNER JOIN";
    }

    private String whereORand() {
        if (count == 0) {
            return " where ";
        } else {
            return " and ";
        }
    }
    // obliger davoir cette fonction sinon le querry ne fonctionne pas
    public RechercheBuilder setTypeItem(String typeItem){

        //TODO id.terrain changer en id.item
        // exemple  SELECT * FROM t_annonce INNER JOIN t_terrain on t_terrain.id_terrain = t_annonce.id_terrain
        this.typeItem=typeItem;
        querry += " myDb.t_"+typeItem.toLowerCase()+" on myDb.t_annonce.id_item = myDb.t_"+typeItem.toLowerCase()+".id_"+typeItem.toLowerCase();
        return this;
    }

    public RechercheBuilder whereDate(String date){
        querry += whereORand();
        querry += "jour_debut ='";
        querry += date+"'";
        count++;
        return this;
    }
    public RechercheBuilder whereVille ( String ville){
        querry += whereORand();
        querry += "ville = '";
        querry += ville+"'";
        count++;
        return this;
    }
    public RechercheBuilder whereTypeSport(String type_sport){
        querry += whereORand();
        querry += "type_sport='";
        querry += type_sport+"'";
        count++;        return this;

    }
    public RechercheBuilder whereCreneau(String heure_debut){
        querry += whereORand();
        querry += "heure_debut ='";
        querry += heure_debut + "'";
        count++;        return this;

    }
    public RechercheBuilder whereNombreDePlaceRestant(int placeRestant){
        querry += whereORand();
        querry += "place_restant >= ";
        querry += placeRestant;
        count++;        return this;

    }

    public RechercheBuilder whereIdAnnonce(int id_annonce){

        querry += whereORand();
        querry += "id_annonce = ";
        querry += id_annonce;
        count++;
        return this;
    }

    public String getQuerry(){
        String tmp = querry;
        return tmp;
    }

}


