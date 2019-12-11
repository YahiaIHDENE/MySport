
#insert annonce et terrain : 

INSERT INTO t_annonce ( id_user, jour_debut, heure_debut, heure_fin, jour_fin) VALUES ( '1', '2019-12-12', '10:00:00', '18:00:00', '2019-12-28');
INSERT INTO t_terrain ( id_annonce, adresse, ville`, `code_postal`, `capacite`, `type_sport`) VALUES ( (LAST_INSERT_ID()), 'm_adresse', 'm_ville', 'm_code_postal', '9', 'foot');



#insert reservation : 


INSERT into t_reserve ( id_annonce,id_user,nombre_reserve,jour_reserve,heure_debut,heure_fin)values(3,1,8,"2019-12-20","11:00:00","14:00:00"); 


# select tableau de annonce et terrain
SELECT * FROM t_annonce INNER JOIN t_terrain WHERE t_terrain.id_annonce = t_annonce.id_annonce 


# recheche de reservation avec date 

SELECT t_reserve.id_reserve,
       t_reserve.id_annonce,
	   t_reserve.id_user,
       t_terrain.adresse,
       t_terrain.ville,
       t_terrain.code_postal,
	t_terrain.capacite,
	t_terrain.type_sport,       
	t_user.user_first_name,
       t_user.user_last_name,
       t_user.user_mail
 FROM t_reserve
 INNER JOIN t_annonce ON t_reserve.id_annonce = t_annonce.id_annonce
 INNER JOIN t_terrain ON t_terrain.id_annonce = t_annonce.id_annonce
 INNER JOIN t_user on t_user.id_user= t_reserve.id_user
 where t_reserve.jour_reserve = "2019-12-18"
 ORDER BY t_reserve.id_reserve



# select recheche de annonce  avec tout date  ville et type de sport
SELECT 
       t_annonce.id_annonce,
	   t_annonce.id_user,
       t_terrain.adresse,
       t_terrain.ville,
       t_terrain.code_postal,
		t_terrain.capacite,
		t_terrain.type_sport,       
		t_user.user_first_name,
       t_user.user_last_name,
       t_user.user_mail
 FROM t_annonce
 INNER JOIN t_terrain ON t_terrain.id_annonce = t_annonce.id_annonce
 INNER JOIN t_user on t_user.id_user= t_annonce.id_user
 where t_annonce.jour_debut <= "2019-12-18" and t_annonce.jour_fin >="2019-12-18" and t_terrain.type_sport = "foot" and t_terrain.ville= "maville" 
 ORDER BY t_annonce.id_annonce;


#update info user

UPDATE t_user
SET 
user_first_name = 'valeur 1', 
user_last_name= 'valeur 2', 
user_password= '1521515',
user_tel= '0611111111'
WHERE t_user.id_user = 1;

#update info terrain ;

UPDATE t_terrain SET t_terrain.adresse = 'valeur 1', t_terrain.ville= 'valeur 2', t_terrain.code_postal= '1521515', t_terrain.type_sport= '0611111111', t_terrain.capacite = 5 WHERE t_terrain.id_terrain = 1 


