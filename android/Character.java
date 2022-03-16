package com.example.a06_sqlite.placeholder;

public class Character {


    private long id;
    private String name_col;
    private String race_col;
    private String class_col;
    private Integer access_count;
    private Integer lvl_col;
    private Integer armour_col;
    private Integer health_col;
    private Integer str_col;
    private Integer dex_col;
    private Integer con_col;
    private Integer int_col;
    private Integer wis_col;
    private Integer cha_col;

    public Character() {
        this.setId(0);
        this.setAccessCount(0);
        this.setName("Testy McTestFace");
        this.setRace("Test-man");
        this.setCharClass("Testomancer");
        this.setLevel(0);
        this.setArmour(0);
        this.setHealth(0);
        this.setStrength(0);
        this.setDexterity(0);
        this.setConstitution(0);
        this.setIntelligence(0);
        this.setWisdom(0);
        this.setCharisma(0);
    }

    public Character(long id, Integer count, String name, Integer lvl, String race, String charClass,
                     Integer hp, Integer ac, Integer str, Integer dex, Integer con, Integer intel,
                     Integer wis, Integer cha) {
        this.setId(id);
        this.setAccessCount(count);
        this.setName(name);
        this.setLevel(lvl);
        this.setRace(race);
        this.setCharClass(charClass);
        this.setHealth(hp);
        this.setArmour(ac);
        this.setStrength(str);
        this.setDexterity(dex);
        this.setConstitution(con);
        this.setIntelligence(intel);
        this.setWisdom(wis);
        this.setCharisma(cha);
    }

    @Override
    public String toString() {
        return "Details about " + name_col + ":\n\n" +
                "Level: " + lvl_col + "\n" +
                "Race: " + race_col + "\n" +
                "Class: " + class_col + "\n" +
                "Health Points: " + health_col + "\n" +
                "Armour Class: " + armour_col + "\n" +
                "Strength: " + str_col + "\n" +
                "Dexterity: " + dex_col + "\n" +
                "Constitution: " + con_col + "\n" +
                "Intelligence: " + int_col + "\n" +
                "Wisdom: " + wis_col + "\n" +
                "Charisma: " + cha_col + "\n\n";
    }

    public String getName() { return name_col; }
    public String getRace() { return race_col; }
    public String getCharClass() { return class_col; }
    public Integer getLevel() { return lvl_col; }
    public Integer getArmour() { return armour_col; }
    public Integer getHealth() { return health_col; }
    public Integer getStrength() { return str_col; }
    public Integer getDexterity() { return dex_col; }
    public Integer getConstitution() { return con_col; }
    public Integer getIntelligence() { return int_col; }
    public Integer getWisdom() { return wis_col; }
    public Integer getCharisma() { return cha_col; }
    public Integer getAccessCount() { return access_count; }
    public long getId() {
        return id;
    }

    public void setName(String name) { this.name_col = name; }
    public void setRace(String race) { this.race_col = race; }
    public void setCharClass(String charClass) { this.class_col = charClass; }
    public void setLevel(Integer level) { this.lvl_col = level; }
    public void setArmour(Integer armour) { this.armour_col = armour; }
    public void setHealth(Integer health) { this.health_col = health; }
    public void setStrength(Integer str) { this.str_col = str; }
    public void setDexterity(Integer dex) { this.dex_col = dex; }
    public void setConstitution(Integer con) { this.con_col = con; }
    public void setIntelligence(Integer intel) { this.int_col = intel; }
    public void setWisdom(Integer wis) { this.wis_col = wis; }
    public void setCharisma(Integer cha) { this.cha_col = cha; }
    public void setAccessCount(Integer count) { this.access_count = count; }
    public void setId(long id) {
        this.id = id;
    }
}
