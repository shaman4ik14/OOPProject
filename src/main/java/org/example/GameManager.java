package org.example;

import java.util.Random;

public class GameManager {
    public static void main(String[] args) {
        GameManager current_game = new GameManager();
        CharacterFactory random_c = new CharacterFactory();
        Character c1 = random_c.createCharacter();
        Character c2 = random_c.createCharacter();
        current_game.fight(c1, c2);

    }
    public void fight(Character c1, Character c2){
        if (c1.getClass().getName() == "org.example.Hobbit" && c2.getClass().getName() == "org.example.Hobbit") {
            System.out.println("Both heroes are hobbits, so their battle will be eternal, so friendship won");
        } else if (c1.getClass().getName() == "org.example.Elf" && c2.getClass().getName() == "org.example.Elf") {
            System.out.println("Both heroes are Elfs, so their battle will be eternal, so friendship won");
        } else{
            while (c1.isAlive() && c2.isAlive()) {
                System.out.println("First Hero -> " + c1);
                System.out.println("Second Hero -> " + c2);
                c1.kick(c2);
                if (!c2.isAlive()) {
                    System.out.println("First Hero " + c1.getClass().getSimpleName() + " is a winner");
                    break;
                }
                c2.kick(c1);
                if (!c1.isAlive()) {
                    System.out.println("Second Hero " + c2.getClass().getSimpleName() + " is a winner");
                    break;
                }
            }
        }
    }
}

class Character{
    int power;
    int hp;
    Character(int power, int hp){
        this.power = power;
        this.hp = hp;
    }
    public void kick(Character c){
        c.hp -= this.power;
        System.out.println("Hero " + this.getClass().getSimpleName()+  " took " + this.power + " health units from Hero " + c.getClass().getSimpleName());
    }
    public boolean isAlive(){
        return this.hp > 0;
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName() + "{hp=" + this.hp + ", power=" + this.power+"}";
    }

    public void setHp(int i) {
        if (i > 0){
            this.hp = i;
        }
        else{
            this.hp = 0;
        }
    }

    public int getHp() {
        return this.hp;
    }


    public int getPower() {
        return this.power;
    }
}

class Hobbit extends Character{
    Hobbit(){
        super(0, 3);
    }
    public void kick(Character c){
        toCry();
    }
    public void toCry(){
        System.out.println("Hobbit crying!");
    }
    public int getHp(){
        return this.hp;
    }
}
class Elf extends Character{
    Elf(){
        super(10, 10);
    }
    @Override
    public void kick(Character c){
        if (c.hp < this.hp){
            c.hp = 0;
            System.out.println("Hero " + this.getClass().getSimpleName()+  " took all health units from Hero " + c.getClass().getSimpleName());
        }
        else{
            c.power -= 1;
            System.out.println("Hero " + this.getClass().getSimpleName()+  " took 1 health units from Hero " + c.getClass().getSimpleName());
        }
    }

    public boolean isAlive(){
        return super.isAlive();
    }
}
class King extends Character{
    King(){
        super(new Random().nextInt(15 - 5) + 5 + 1, new Random().nextInt(15 - 5) + 5 + 1);
    }
    public void kick(Character c){
        Random r = new Random();
        int dmg = r.nextInt(15 - 5) + 5 + 1;
        c.hp -= dmg;
        System.out.println("Hero " + this.getClass().getSimpleName()+  " took " + dmg + " health units from Hero " + c.getClass().getSimpleName());
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName() + ": HAS HP - " + hp + " and HAS POWER - from range(5, 15)";
    }
}

class Knight extends King{
    int power;
    int hp;
    Knight(){
        Random r = new Random();
        int min = 2;
        int max = 12;
        this.hp = r.nextInt(max - min) + min + 1;
        this.power = r.nextInt(max - min) + min + 1;
    }

    public void kick(Character c){
        Random r = new Random();
        int dmg = r.nextInt(12 - 2) + 2 + 1;
        c.hp -= dmg;
        System.out.println("Hero " + this.getClass().getSimpleName()+  " took " + dmg + " health units from Hero " + c.getClass().getSimpleName());

    }
    @Override
    public String toString(){
        return this.getClass().getSimpleName() + ": HAS HP - " + this.hp + " and HAS POWER - from range(2, 12)";
    }
}

class CharacterFactory{
    public Character createCharacter(){
        Random r = new Random();
        int rand = r.nextInt(4);
        if (rand == 0){
            return new Hobbit();
        } else if (rand == 1) {
            return new Elf();
        } else if (rand == 2) {
            return new King();
        }
        else{
            return new Knight();
        }
    }
}