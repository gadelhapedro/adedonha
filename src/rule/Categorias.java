package rule;

public class Categorias {

	private Participante participante;
	private String pessoa;
	private String carro;
	private String cidadeEstadoPais;
	private String fruta;
	private String objeto;
	private String animal;
	private int letraSorteada;
	
	public String getPessoa() {
		return pessoa;
	}
	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}
	public String getCarro() {
		return carro;
	}
	public void setCarro(String carro) {
		this.carro = carro.toUpperCase();
	}
	public String getCidadeEstadoPais() {
		return cidadeEstadoPais;
	}
	public void setCidadeEstadoPais(String cidadeEstadoPais) {
		this.cidadeEstadoPais = cidadeEstadoPais.toUpperCase();
	}
	public String getFruta() {
		return fruta;
	}
	public void setFruta(String fruta) {
		this.fruta = fruta.toUpperCase();
	}
	public String getObjeto() {
		return objeto;
	}
	public void setObjeto(String objeto) {
		this.objeto = objeto.toUpperCase();
	}
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal.toUpperCase();
	}
	public Participante getParticipante() {
		return participante;
	}
	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	public int getLetraSorteada() {
		return letraSorteada;
	}
	public void setLetraSorteada(int letraSorteada){
		this.letraSorteada = letraSorteada;
	}
	
	
}
