/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.ntxuva;

/**
 *
 * @author felermino
 */
public class Sucessor {

    Ntxuva tabuleiro;
    int utilidade;
    Position position;
    

    public Sucessor(Ntxuva ntxuva, Position position) {
        this.tabuleiro = new Ntxuva(ntxuva);
        this.position = position;
    }
    
    @Override
    public String toString(){
        return "\n"+tabuleiro.toString()+"position"+position.toString()+"\nutilidade"+utilidade+"\n";
    }

}
