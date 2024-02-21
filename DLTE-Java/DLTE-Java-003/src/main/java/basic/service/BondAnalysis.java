package basic.service;

public class BondAnalysis {
    public static void main(String[] args) {
        //initialization
        Bond bondArray[] = {
                new Bond(2000,6.0,"Payable","Sinchana",5),
                new Bond(5000,10.0,"Payable","Zuni",3),
                new Bond(7000,8.0,"Not Payable","Ninadha",2),
                new Bond(4000,5.0,"Payable","Sherley",4)
        };
        BondAnalysis bond=new BondAnalysis();
        bond.sort(bondArray);
    }

    public void sort(Bond[] bondArray){
        System.out.println("Amount before sorting");
        for(Bond each: bondArray){
            System.out.println(each.getRateOfInterest());
        }
        for(int select=0;select<bondArray.length;select++){
            for(int next=0;next<bondArray.length-select-1;next++){
                if(bondArray[next].getRateOfInterest().compareTo(bondArray[next+1].getRateOfInterest())<0){
                    Bond backup=bondArray[next];
                    bondArray[next]=bondArray[next+1];
                    bondArray[next+1]=backup;
                }
            }
        }
        System.out.println("After sorting");
        for(Bond each: bondArray){
            System.out.println(each.getRateOfInterest());
        }
    }
}
