import java.util.Scanner;
import java.util.Random;

public class Update {
     float gdp;
     float taxRate;
     float budget;
     float popGrowth;
     float population;
     float capita;
     Random randOj = new Random();
     Scanner prompt = new Scanner(System.in);
     public Update(float gdp, float taxRate, float population) {
          this.gdp = gdp;
          this.taxRate = taxRate;
          this.population = population;
     }

     public float nextYearGdP() {
         float growthRate = randOj.nextFloat() * 20 - 20;
         gdp = gdp - budget;
         gdp = gdp + gdp*(growthRate/100);
         return gdp;
     }

     public void changeTax() {
         System.out.println("What is your new annual tax rate?");
         float taxRating = prompt.nextFloat();
         taxRate = taxRating;
     }

     public float currentTax(){
         return taxRate;
     }

     public float nextBudget() {
         budget = gdp*taxRate;
         return budget;
     }

     public float nextYearPop() {
         float popGrowth = randOj.nextFloat() * 10 - 5;
         float popRate = popGrowth/100;
         population = population + population*popRate;
         return population;
     }

     public float getCapita() {
         capita = gdp/population;
         return capita;
     }

}
