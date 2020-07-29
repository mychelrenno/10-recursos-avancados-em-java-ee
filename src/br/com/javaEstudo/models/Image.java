package br.com.javaEstudo.models;

public class Image {

	private long id;
	private byte[] imagem;
	private String extensao;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	public String getExtensao() {
		return extensao;
	}
	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}
}
