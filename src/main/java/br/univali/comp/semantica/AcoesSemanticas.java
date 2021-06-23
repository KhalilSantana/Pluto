package br.univali.comp.semantica;

import br.univali.comp.virtualmachine.DataFrame;
import br.univali.comp.virtualmachine.DataType;
import br.univali.comp.virtualmachine.Instruction;

import java.util.ArrayList;
import java.util.List;

public class AcoesSemanticas {
    private String contexto = "";
    private int VT = 0; // contador para número total de constantes e de variáveis
    private int VP = 0; // contador para número de constantes ou variáveis de um determinado tipo;
    private int VIT = 0; // contador para o tamanho das variáveis indexadas de um determinado tipo;
    private int tipo; // 1 variável natural, 2 variável real, 3 variável char, 4 variável boolean, 5 constante natural, 6 constante real, 7 constante char;
    private int ponteiro = 1;  // indicador da posição onde será gerada a próxima instrução na área de instruções;
    boolean variavelIndexada = false;
    private List<Integer> pilhaDeDesvios = new ArrayList<>(); //Lembrar de controlar para trabalhar como LIFO
    private List<Simbolo> tabelaDeSimbolos = new ArrayList<>();
    private List<Instruction> instructionList = new ArrayList<>();
    private String identificadorReconhecido;
    private int constanteInteira;
    private List<Object> listaAtributos = new ArrayList<>();

    // OK
    public void acao1(){
        Instruction instruction = new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.INTEGER, 0));
        instructionList.add(instruction);
        System.out.println("gerar instrução: (ponteiro, STP, 0)");
    }

    // OK
    public void acao2(String identificador){
        Simbolo simbolo = new Simbolo(identificador, 0);
        tabelaDeSimbolos.add(simbolo);
        System.out.println("inserir na tabela de símbolos a tupla (identificador, 0, -, -)");
    }

    // OK
    public void acao3(){
        this.contexto = "constante";
        this.VIT = 0;
        System.out.println("reconhecimento da palavra reservada not variable");
    }

    //OK
    public void acao4(){
        this.VP = this.VP + this.VIT;
        switch (this.tipo){
            case 1: case 5: {
                Instruction instruction = new Instruction(Instruction.Mnemonic.ALI, new DataFrame(DataType.INTEGER, this.VP));
                instructionList.add(instruction);
                break;
            }
            case 2:
            case 6:
            {
                Instruction instruction = new Instruction(Instruction.Mnemonic.ALR, new DataFrame(DataType.INTEGER, this.VP));
                instructionList.add(instruction);
                break;
            }
            case 3: case 7: {
                Instruction instruction = new Instruction(Instruction.Mnemonic.ALS, new DataFrame(DataType.INTEGER, this.VP));
                instructionList.add(instruction);
                break;
            }
            case 4:{
                Instruction instruction = new Instruction(Instruction.Mnemonic.ALB, new DataFrame(DataType.INTEGER, this.VP));
                instructionList.add(instruction);
                break;
            }
        }
        if(this.tipo == 1 || this.tipo == 2 || this.tipo == 3 || this.tipo == 4){
            this.VP = 0;
            this.VIT = 0;
        }
        System.out.println("reconhecimento do término da declaração de constantes ou variáveis de um determinado tipo");
    }

    // OK
    public void acao5(String valor){
        switch (this.tipo){
            case 5: {
                Instruction instruction = new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, valor));
                instructionList.add(instruction);
                break;
            }
            case 6: {
                Instruction instruction = new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, valor));
                instructionList.add(instruction);
                break;
            }
            case 7: {
                Instruction instruction = new Instruction(Instruction.Mnemonic.LDS, new DataFrame(DataType.LITERAL, valor));
                instructionList.add(instruction);
                break;
            }
        }
        Instruction instruction = new Instruction(Instruction.Mnemonic.STC, new DataFrame(DataType.INTEGER, this.VP));
        instructionList.add(instruction);
        this.ponteiro = this.ponteiro + 1;
        this.VP = 0;
        System.out.println("reconhecimento de valor na declaração de constante");
    }

    //OK
    public void acao6(){
        this.contexto = "variável";
        System.out.println("reconhecimento da palavra reservada variable");
    }

    //OK
    public void acao7(){
        if(this.contexto.equals("variável")){
            this.tipo = 1;
        }else{
            this.tipo = 5;
        }
        System.out.println(": reconhecimento da palavra reservada natural");
    }

    //OK
    public void acao8(){
        if(this.contexto.equals("variável")){
            this.tipo = 2;
        }else{
            this.tipo = 6;
        }
        System.out.println("reconhecimento da palavra reservada real");
    }

    //OK
    public void acao9(){
        if(this.contexto.equals("variável")){
            this.tipo = 3;
        }else{
            this.tipo = 7;
        }
        System.out.println(": reconhecimento da palavra reservada char");
    }

    //OK
    public void acao10(){
        if(this.contexto.equals("variável")){
            this.tipo = 4;
        }else{
            //Verificar posteriormente se precisa adicionar em um array de erros para prosseguir ou não;
            throw new Error("Tipo inválido para constante”");
        }
        System.out.println(" reconhecimento da palavra reservada boolean");
    }

    //OK
    public void acao11(String identificador){
        Simbolo exist = tabelaDeSimbolos.stream().filter(simb -> identificador.equals(simb.getIdentificador())).findAny().orElse(null);
        if(!exist.equals(null)){
            throw new Error("“identificador já declarado”");
        }else{
            this.VT = this.VT + 1;
            this.VP = this.VP + 1;
            Simbolo simbolo = new Simbolo(identificador, this.tipo, this.VT);
            tabelaDeSimbolos.add(simbolo);
        }
        System.out.println("reconhecimento de identificador de constante");
    }

    //OK
    public void acao12(String identificador){
        if(this.contexto.equals("variável")){
            Simbolo exist = tabelaDeSimbolos.stream().filter(simb -> identificador.equals(simb.getIdentificador())).findAny().orElse(null);
            if(!exist.equals(null)){
                throw new Error("“identificador já declarado”");
            }else{
                this.variavelIndexada = false;
                this.identificadorReconhecido = identificador;
            }
        }else{
            this.tipo = 7;
            this.identificadorReconhecido = identificador;
        }
        System.out.println("reconhecimento de identificador de variável");
    }

    //OK
    public void acao13(){
        switch (this.contexto){
            case "variável": {
                if(!this.variavelIndexada){
                    this.VT= this.VT + 1;
                    this.VP= this.VP + 1;
                    Simbolo simbolo = new Simbolo(this.identificadorReconhecido, this.tipo, this.VT);
                    tabelaDeSimbolos.add(simbolo);
                }else{
                    this.VIT = this.VIT + this.constanteInteira;
                    Simbolo simbolo = new Simbolo(this.identificadorReconhecido, this.tipo, this.VT+1, this.constanteInteira);
                    tabelaDeSimbolos.add(simbolo);
                    this.VT = this.VT + this.constanteInteira;
                }
                break;
            }
            case "atribuição": {
                Simbolo exist = tabelaDeSimbolos.stream().filter(simb -> this.identificadorReconhecido.equals(simb.getIdentificador())).findAny().orElse(null);
                if(!exist.equals(null) && (exist.getCategoria() == 1 || exist.getCategoria() == 2 || exist.getCategoria() == 3 || exist.getCategoria() == 4)) {
                    if(exist.getAtributo2() == 0){
                        if(!this.variavelIndexada){
                            this.listaAtributos.add(exist.getAtributo1());
                        }else{
                            throw new Error("identificador de variável não indexada");
                        }
                    } else{
                        if(this.variavelIndexada){
                            this.listaAtributos.add(exist.getAtributo1() + this.constanteInteira -1);
                        }else{
                            throw new Error("identificador de variável indexada exige índice");
                        }
                    }
                }else{
                    throw new Error("identificador não declarado ou de constante");
                }
                break;
            }
            case "entrada dados": {
                Simbolo exist = tabelaDeSimbolos.stream().filter(simb -> this.identificadorReconhecido.equals(simb.getIdentificador())).findAny().orElse(null);
                if(!exist.equals(null) && (exist.getCategoria() == 1 || exist.getCategoria() == 2 || exist.getCategoria() == 3 || exist.getCategoria() == 4)) {
                    if(exist.getAtributo2() == 0){
                        if(!this.variavelIndexada){
                            instructionList.add(new Instruction(Instruction.Mnemonic.REA, new DataFrame(DataType.LITERAL, exist.getCategoria())));
                            this.ponteiro = this.ponteiro + 1;
                            instructionList.add(new Instruction(Instruction.Mnemonic.STR, new DataFrame(DataType.INTEGER, exist.getAtributo1())));
                            this.ponteiro = this.ponteiro + 1;
                        }else{
                            throw new Error("identificador de variável não indexada");
                        }
                    } else{
                        if(this.variavelIndexada){
                            instructionList.add(new Instruction(Instruction.Mnemonic.REA, new DataFrame(DataType.LITERAL, exist.getCategoria())));
                            this.ponteiro = this.ponteiro + 1;
                            instructionList.add(new Instruction(Instruction.Mnemonic.STR, new DataFrame(DataType.INTEGER, exist.getAtributo1() + this.constanteInteira -1)));
                            this.ponteiro = this.ponteiro + 1;
                        }else{
                            throw new Error("identificador de variável indexada exige índice");
                        }
                    }
                }else{
                    throw new Error("identificador não declarado ou de constante");
                }
                break;
            }
        }
        System.out.println("reconhecimento de identificador de variável e tamanho da variável indexada");
    }

    //OK
    public void acao14(int valor){
        this.constanteInteira = valor;
        this.variavelIndexada = true;
        System.out.println(" reconhecimento de constante inteira como tamanho da variável indexada ou como índice");
    }

    //OK
    public void acao15(){
        this.contexto = "atribuição";
        System.out.println("reconhecimento do início do comando de atribuição");
    }
    
    //NEXT
    public void acao16(){

        //TODO for in this.listaAtributos - Add instrução com ponteiro, STR, atributo
        //instructionList.add(new Instruction(Instruction.Mnemonic.STR, new DataFrame(DataType.LITERAL, exist.getCategoria())));
        System.out.println(": reconhecimento do fim do comando de atribuição");
    }

    public void acao17(){

        System.out.println("reconhecimento da palavra reservada not variable");
    }

    public void acao18(){

        System.out.println("reconhecimento da palavra reservada not variable");
    }

    public void acao19(){

        System.out.println("reconhecimento da palavra reservada not variable");
    }

    public void acao20(){

        System.out.println("reconhecimento da palavra reservada not variable");
    }

    public void acao21(){

        System.out.println("reconhecimento da palavra reservada not variable");
    }

    public void acao22(){

        System.out.println("reconhecimento da palavra reservada not variable");
    }

    public void acao23(){

        System.out.println("reconhecimento da palavra reservada not variable");
    }

};


