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
    private List<String> listaErros = new ArrayList<>();

    public List<Instruction> getInstructionList() {
        return instructionList;
    }

    public List<String> getListaErros() {
        return listaErros;
    }

    // OK
    public void acao1(){
        System.out.println("gerar instrução: (ponteiro, STP, 0)");
        Instruction instruction = new Instruction(Instruction.Mnemonic.STP, new DataFrame(DataType.INTEGER, 0));
        instructionList.add(instruction);
        Instruction.enumerateInstructions(instructionList);
        System.out.println("Lista de Erros: " + listaErros.toString());
        System.out.println("Lista de Instruções:");
        for (Instruction i : instructionList) {
            System.out.println(i.toString());
        }
    }

    // OK
    public void acao2(String identificador){
        System.out.println("inserir na tabela de símbolos a tupla (identificador, 0, -, -)");
        Simbolo simbolo = new Simbolo(identificador, 0);
        tabelaDeSimbolos.add(simbolo);
    }

    // OK
    public void acao3(){
        System.out.println("reconhecimento da palavra reservada not variable");
        this.contexto = "constante";
        this.VIT = 0;
    }

    //OK
    public void acao4(){
        System.out.println("reconhecimento do término da declaração de constantes ou variáveis de um determinado tipo");
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
    }

    // OK
    public void acao5(String valor){
        System.out.println("reconhecimento de valor na declaração de constante");
        switch (this.tipo){
            case 5: {
                Instruction instruction = new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, Integer.parseInt(valor)));
                instructionList.add(instruction);
                break;
            }
            case 6: {
                Instruction instruction = new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, Float.parseFloat(valor)));
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
    }

    //OK
    public void acao6(){
        System.out.println("reconhecimento da palavra reservada variable");
        this.contexto = "variável";
    }

    //OK
    public void acao7(){
        System.out.println(": reconhecimento da palavra reservada natural");
        if(this.contexto.equals("variável")){
            this.tipo = 1;
        }else{
            this.tipo = 5;
        }
    }

    //OK
    public void acao8(){
        System.out.println("reconhecimento da palavra reservada real");
        if(this.contexto.equals("variável")){
            this.tipo = 2;
        }else{
            this.tipo = 6;
        }
    }

    //OK
    public void acao9(){
        System.out.println(": reconhecimento da palavra reservada char");
        if(this.contexto.equals("variável")){
            this.tipo = 3;
        }else{
            this.tipo = 7;
        }
    }

    //OK
    public void acao10(){
        System.out.println(" reconhecimento da palavra reservada boolean");
        if(this.contexto.equals("variável")){
            this.tipo = 4;
        }else{
            //Verificar posteriormente se precisa adicionar em um array de erros para prosseguir ou não;
            this.listaErros.add("10 - Tipo inválido para constante");
        }
    }

    //OK
    public void acao11(String identificador){
        System.out.println("reconhecimento de identificador de constante");
        Simbolo exist = tabelaDeSimbolos.stream().filter(simb -> identificador.equals(simb.getIdentificador())).findAny().orElse(null);
        if(!(exist == null)){
            this.listaErros.add("11 - identificador já declarado: '" + identificador + "' ");
        }else{
            this.VT = this.VT + 1;
            this.VP = this.VP + 1;
            Simbolo simbolo = new Simbolo(identificador, this.tipo, this.VT);
            tabelaDeSimbolos.add(simbolo);
        }
    }

    //OK
    public void acao12(String identificador){
        System.out.println("reconhecimento de identificador de variável");
        if(this.contexto.equals("variável")){
            Simbolo exist = tabelaDeSimbolos.stream().filter(simb -> identificador.equals(simb.getIdentificador())).findAny().orElse(null);
            if(!(exist == null)){
                this.listaErros.add("12 - identificador já declarado: '" + identificador + "' ");
            }else{
                this.variavelIndexada = false;
                this.identificadorReconhecido = identificador;
            }
        }else{
            this.tipo = 7;
            this.identificadorReconhecido = identificador;
        }
    }

    //OK
    public void acao13(){
        System.out.println("reconhecimento de identificador de variável e tamanho da variável indexada");
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
                if(!(exist == null) && (exist.getCategoria() == 1 || exist.getCategoria() == 2 || exist.getCategoria() == 3 || exist.getCategoria() == 4)) {
                    if(exist.getAtributo2() == 0){
                        if(!this.variavelIndexada){
                            this.listaAtributos.add(exist.getAtributo1());
                        }else{
                            this.listaErros.add("13 - identificador de variável não indexada: '" + this.identificadorReconhecido + "' ");
                        }
                    } else{
                        if(this.variavelIndexada){
                            this.listaAtributos.add(exist.getAtributo1() + this.constanteInteira -1);
                        }else{
                            this.listaErros.add("13 - identificador de variável indexada exige índice");
                        }
                    }
                }else{
                    this.listaErros.add("13 - identificador não declarado ou de constante");
                }
                break;
            }
            case "entrada dados": {
                Simbolo exist = this.tabelaDeSimbolos.stream().filter(simb -> this.identificadorReconhecido.equals(simb.getIdentificador())).findAny().orElse(null);
                if(!(exist == null) && (exist.getCategoria() == 1 || exist.getCategoria() == 2 || exist.getCategoria() == 3 || exist.getCategoria() == 4)) {
                    if(exist.getAtributo2() == 0){
                        if(!this.variavelIndexada){
                            instructionList.add(new Instruction(Instruction.Mnemonic.REA, new DataFrame(DataType.get(exist.getCategoria()), exist.getCategoria())));
                            this.ponteiro = this.ponteiro + 1;
                            instructionList.add(new Instruction(Instruction.Mnemonic.STR, new DataFrame(DataType.ADDRESS, exist.getAtributo1())));
                            this.ponteiro = this.ponteiro + 1;
                        }else{
                            this.listaErros.add("13 - identificador de variável não indexada");
                        }
                    } else{
                        if(this.variavelIndexada){
                            instructionList.add(new Instruction(Instruction.Mnemonic.REA, new DataFrame(DataType.get(exist.getCategoria()), exist.getCategoria())));
                            this.ponteiro = this.ponteiro + 1;
                            instructionList.add(new Instruction(Instruction.Mnemonic.STR, new DataFrame(DataType.ADDRESS, exist.getAtributo1() + this.constanteInteira -1)));
                            this.ponteiro = this.ponteiro + 1;
                        }else{
                            this.listaErros.add("13 - identificador de variável indexada exige índice");
                        }
                    }
                }else{
                    this.listaErros.add("13 - identificador não declarado ou de constante: '"+this.identificadorReconhecido+"' ");
                }
                break;
            }
        }
    }

    //OK
    public void acao14(int valor){
        System.out.println(" reconhecimento de constante inteira como tamanho da variável indexada ou como índice");
        this.constanteInteira = valor;
        this.variavelIndexada = true;
    }

    //OK
    public void acao15(){
        System.out.println("reconhecimento do início do comando de atribuição");
        this.contexto = "atribuição";
    }

    //OK
    public void acao16(){
        System.out.println(": reconhecimento do fim do comando de atribuição");
        for(int i=0; i< this.listaAtributos.size(); i++){
            instructionList.add(new Instruction(Instruction.Mnemonic.STR, new DataFrame(DataType.ADDRESS, this.listaAtributos.get(i))));
            this.ponteiro = this.ponteiro + 1;
        }
    }

    //OK
    public void acao17(){
        System.out.println("reconhecimento do comando de entrada de dados");
        this.contexto = "entrada dados";
    }

    //OK
    public void acao18(){
        System.out.println("reconhecimento de mensagem em comando de saída de dados");
        instructionList.add(new Instruction(Instruction.Mnemonic.WRT, new DataFrame(DataType.INTEGER, 0)));
        this.ponteiro = this.ponteiro + 1;
    }

    //OK
    public void acao19(String identificador){
        System.out.println("reconhecimento de identificador em comando de saída ou em expressão");
        Simbolo exist = tabelaDeSimbolos.stream().filter(simb -> identificador.equals(simb.getIdentificador())).findAny().orElse(null);
        if(!(exist == null)){
            this.variavelIndexada = false;
            this.identificadorReconhecido = identificador;
        }else{
            this.listaErros.add("19 - identificador não declarado: '"+identificador+"' ");
        }
    }

    //OK
    public void acao20(){
        System.out.println(" reconhecimento de índice de variável indexada em comando de saída");
        Simbolo exist = tabelaDeSimbolos.stream().filter(simb -> this.identificadorReconhecido.equals(simb.getIdentificador())).findAny().orElse(null);
        if(!this.variavelIndexada){
            if(exist.getAtributo2() == 0){
                instructionList.add(new Instruction(Instruction.Mnemonic.LDV, new DataFrame(DataType.ADDRESS, exist.getAtributo1())));
                this.ponteiro = this.ponteiro + 1;
            }else{
                this.listaErros.add("20 - identificador de variável indexada exige índice: '"+this.identificadorReconhecido+"' ");
            }
        }else{
            if(exist.getAtributo2() != 0){
                instructionList.add(new Instruction(Instruction.Mnemonic.LDV, new DataFrame(DataType.ADDRESS, exist.getAtributo1() + this.constanteInteira -1)));
                this.ponteiro = this.ponteiro + 1;
            }else{
                this.listaErros.add("20 - identificador de constante ou de variável não indexada: '"+this.identificadorReconhecido+"' ");
            }
        }
    }

    //OK
    public void acao21(Integer constInt){
        System.out.println("reconhecimento de constante inteira em comando de saída ou em expressão");
        instructionList.add(new Instruction(Instruction.Mnemonic.LDI, new DataFrame(DataType.INTEGER, constInt)));
        this.ponteiro = this.ponteiro + 1;
    }

    //OK
    public void acao22(Float constReal){
        System.out.println("reconhecimento de constante real em comando de saída ou em expressão");
        instructionList.add(new Instruction(Instruction.Mnemonic.LDR, new DataFrame(DataType.FLOAT, constReal)));
        this.ponteiro = this.ponteiro + 1;
    }

    //OK
    public void acao23(String constLiteral){
        System.out.println("reconhecimento de constante literal em comando de saída ou em expressão");
        instructionList.add(new Instruction(Instruction.Mnemonic.LDS, new DataFrame(DataType.LITERAL, constLiteral)));
        this.ponteiro = this.ponteiro + 1;
    }

    //OK
    public void acao24(){
        //ACHO que é isso
        System.out.println("reconhecimento de fim de comando de seleção");
        this.pilhaDeDesvios.set(this.pilhaDeDesvios.size()-1, ponteiro);
    }

    //OK
    public void acao25(){
        System.out.println(" reconhecimento da palavra reservada true");
        instructionList.add(new Instruction(Instruction.Mnemonic.JMF, new DataFrame(DataType.NONE, '?')));
        this.ponteiro = this.ponteiro + 1;
        this.pilhaDeDesvios.add(this.ponteiro -1);
    }

    //OK
    public void acao26(){
        System.out.println(" reconhecimento da palavra reservada false");
        instructionList.add(new Instruction(Instruction.Mnemonic.JMT, new DataFrame(DataType.ADDRESS, '?')));
        this.ponteiro = this.ponteiro + 1;
        this.pilhaDeDesvios.add(this.ponteiro -1);
    }

    //OK
    public void acao27(){
        System.out.println("reconhecimento da palavra reservada false (ou true)");
        this.pilhaDeDesvios.set(this.pilhaDeDesvios.size()-1, ponteiro+1);
        instructionList.add(new Instruction(Instruction.Mnemonic.JMP, new DataFrame(DataType.NONE, '?')));
        this.ponteiro = this.ponteiro + 1;
        this.pilhaDeDesvios.add(this.ponteiro -1);
    }

    //OK
    public void acao28(){
        System.out.println("reconhecimento do comando de repetição");
        this.pilhaDeDesvios.add(this.ponteiro);
    }

    //OK
    public void acao29(){
        System.out.println("reconhecimento do fim do comando de repetição");
        Integer p = this.pilhaDeDesvios.get(this.pilhaDeDesvios.size()-1);
        this.pilhaDeDesvios.remove(this.pilhaDeDesvios.size()-1);
        instructionList.add(new Instruction(Instruction.Mnemonic.JMT, new DataFrame(DataType.ADDRESS, p)));
        this.ponteiro = this.ponteiro + 1;
    }

    //OK
    public void acao30(){
        System.out.println("reconhecimento do início de expressão em comando de repetição");
        this.pilhaDeDesvios.add(this.ponteiro);
    }

    //OK
    public void acao31(){
        System.out.println("reconhecimento de expressão em comando de repetição");
        instructionList.add(new Instruction(Instruction.Mnemonic.JMF, new DataFrame(DataType.INTEGER, '?')));
        this.ponteiro = this.ponteiro + 1;
        this.pilhaDeDesvios.add(this.ponteiro-1);
    }

    //rever - Não entendi bem o que é para fazer nessa função;
    public void acao32(){
        System.out.println("reconhecimento do fim do comando de repetição");
        Integer p = this.pilhaDeDesvios.get(this.pilhaDeDesvios.size()-1);
        this.pilhaDeDesvios.remove(this.pilhaDeDesvios.size()-1);
        p = ponteiro + 1;
        this.pilhaDeDesvios.remove(this.pilhaDeDesvios.size()-1);
        instructionList.add(new Instruction(Instruction.Mnemonic.JMP, new DataFrame(DataType.INTEGER, p)));
    }

    //OK
    public void acao33(){
        System.out.println("reconhecimento de operação relacional igual");
        instructionList.add(new Instruction(Instruction.Mnemonic.EQL, new DataFrame(DataType.INTEGER, 0)));
        this.ponteiro = this.ponteiro + 1;
    }

    //OK
    public void acao34(){
        System.out.println("reconhecimento de operação relacional diferente");
        instructionList.add(new Instruction(Instruction.Mnemonic.DIF, new DataFrame(DataType.INTEGER, 0)));
        this.ponteiro = this.ponteiro + 1;
    }

    //OK
    public void acao35(){
        System.out.println("reconhecimento de operação relacional menor");
        instructionList.add(new Instruction(Instruction.Mnemonic.SMR, new DataFrame(DataType.INTEGER, 0)));
        this.ponteiro = this.ponteiro + 1;
    }

    //OK
    public void acao36(){
        System.out.println("reconhecimento de operação relacional maior");
        instructionList.add(new Instruction(Instruction.Mnemonic.BGR, new DataFrame(DataType.INTEGER, 0)));
        this.ponteiro = this.ponteiro + 1;
    }

    //OK
    public void acao37(){
        System.out.println("reconhecimento de operação relacional menor igual");
        instructionList.add(new Instruction(Instruction.Mnemonic.SME, new DataFrame(DataType.INTEGER, 0)));
        this.ponteiro = this.ponteiro + 1;
    }

    //OK
    public void acao38(){
        System.out.println("reconhecimento de operação relacional maior igual");
        instructionList.add(new Instruction(Instruction.Mnemonic.BGE, new DataFrame(DataType.INTEGER, 0)));
        this.ponteiro = this.ponteiro + 1;
    }

    //OK
    public void acao39(){
        System.out.println("reconhecimento de operação aritmética adição");
        instructionList.add(new Instruction(Instruction.Mnemonic.ADD, new DataFrame(DataType.INTEGER, 0)));
        this.ponteiro = this.ponteiro + 1;
    }

    //OK
    public void acao40(){
        System.out.println("reconhecimento de operação aritmética subtração");
        instructionList.add(new Instruction(Instruction.Mnemonic.SUB, new DataFrame(DataType.INTEGER, 0)));
        this.ponteiro = this.ponteiro + 1;
    }

    //OK
    public void acao41(){
        System.out.println("reconhecimento de operação lógica OU ( | )");
        instructionList.add(new Instruction(Instruction.Mnemonic.OR, new DataFrame(DataType.INTEGER, 0)));
        this.ponteiro = this.ponteiro + 1;
    }

    //OK
    public void acao42(){
        System.out.println("reconhecimento de operação aritmética multiplicação");
        instructionList.add(new Instruction(Instruction.Mnemonic.MUL, new DataFrame(DataType.INTEGER, 0)));
        this.ponteiro = this.ponteiro + 1;
    }

    //OK
    public void acao43(){
        System.out.println("reconhecimento de operação aritmética divisão real");
        instructionList.add(new Instruction(Instruction.Mnemonic.DIV, new DataFrame(DataType.INTEGER, 0)));
        this.ponteiro = this.ponteiro + 1;
    }

    public void acao44(){
        System.out.println("reconhecimento de operação aritmética divisão inteira-especificar");
    }

    public void acao45(){
        System.out.println("reconhecimento de operação aritmética resto da divisão inteira-especificar");
    }

    //OK
    public void acao46(){
        System.out.println("reconhecimento de operação lógica E (&)\n");
        instructionList.add(new Instruction(Instruction.Mnemonic.AND, new DataFrame(DataType.INTEGER, 0)));
        this.ponteiro = this.ponteiro + 1;
    }

    public void acao47(){
        System.out.println(" reconhecimento de operação aritmética potenciação-especificar");
    }

    //OK
    public void acao48(){
        System.out.println("reconhecimento de constante lógica true");
        instructionList.add(new Instruction(Instruction.Mnemonic.LDB, new DataFrame(DataType.BOOLEAN, true)));
        this.ponteiro = this.ponteiro + 1;
    }

    //OK
    public void acao49(){
        System.out.println("reconhecimento de constante lógica false");
        instructionList.add(new Instruction(Instruction.Mnemonic.LDB, new DataFrame(DataType.BOOLEAN, false)));
        this.ponteiro = this.ponteiro + 1;
    }

    //OK
    public void acao50(){
        System.out.println("reconhecimento de operação lógica NÃO ( ! )");
        instructionList.add(new Instruction(Instruction.Mnemonic.NOT, new DataFrame(DataType.INTEGER, 0)));
        this.ponteiro = this.ponteiro + 1;
    }
};


