package HxCKDMS.gkpk.data;

// A large amount of this code is based off of the old (MC 1.5.2) version of MineChem by Luke "Perky" Perkin

/*

First digit = Internal ID (metadata)

Second digit = color
0 = default
1 = yellow
2 = blue
3 = orange
4 = red

*/
@Deprecated
public enum EnumDrug {

	// DATA BEGINS HERE
	weed(0,1), // 420 blazed it too hard
	t2(1,1), // Wither potions
	lsd(2,0), // Lucy in the sky with diamonds
	psilocybin(3,0), // Magic shrooms
	muscimol(4,0), // A viking's choice
	caff(5,0), // Caffeine, make me go all WEEE!
	ttx(6,0), // Fugu
	atropine(7,0), // Even tarantulas aren't immune from an ambush. This centipede is a predator...
	mhd(8,2), // Will be a fictional molecule called "Mahadeva" - Only can be obtained from the end
	kavalactone(9,0),
	metblue(10,2); // UNIVERSAL ANTIDOTE - CAN ONLY BE PURCHACHED FROM VILLAGES OR FOUND IN CHESTS
    // DATA ENDS HERE
        
	public static EnumDrug[] drugs = values();
	private int id;
	private int color;
	EnumDrug(int ID, int COLOR) {this.id = ID; this.color = COLOR;}
	
	public static EnumDrug getEnumLabel(int id) {
		for(EnumDrug drug : drugs) {
			if(drug.id == id)
				return drug;
		}
		return null;
	}
	
	public int id() {return this.id;}

	public static int getColor (int id){
		for(EnumDrug drug : drugs) {
			if(drug.id == id)
				return drug.color;
		}
		return 0;
	}
}
