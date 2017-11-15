/* 
    Document   : SaldoUtenti
    Created on : Maggio 2016
    Author     : Fabrizio Cara
*/
package PackageTechommerce;

public class SaldoUtenti {
    private double saldo;
        
    public SaldoUtenti (double saldo)
    {
        this.saldo = saldo;
    }    

    //return saldo
    public double getSaldo() {
        return saldo;
    }

    //@param saldo, saldo to set
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
}