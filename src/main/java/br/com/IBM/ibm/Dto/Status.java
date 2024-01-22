package br.com.IBM.ibm.Dto;

public enum Status {
    MANUTENCAO("manutencao"),
    AGUARDANDO_ENVIO("aguardando envio"),
    AGUARDANDO_PECAS("aguardando peças"),
    AGUARDANDO_REENTREGACAO("reentrega"),
    PERDA_TOTAL("perda total"),
    GARANTIA("garantia"),
    ENTREGUE("entregue");


    private String Mudar;

    Status(String s){
        this.Mudar = s;
    }

    public static Status mudaStatus(String obs){
        for(Status status:Status.values()){
            if(status.Mudar.equalsIgnoreCase(obs)){
                return status;
            }
        }
        throw new IllegalArgumentException();
    }


}
