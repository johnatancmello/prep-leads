export class Imagem {

  public id: number;
  public tamanho: number;
  public nome: string;
  public tipo: string;
  public dados;

  public static getImagemBase64(imagem: Imagem, alternative: string): string {
    if (imagem && imagem.dados) {
      return `data:${imagem.tipo};base64,${imagem.dados}`;
    } else {
      return alternative;
    }
  }

  public setImagemApi(imagem) {
    this.id = imagem.id;
    this.dados = imagem.dados;
    this.nome = imagem.nome;
    this.tamanho = imagem.tamanho;
    this.tipo = imagem.tipo;
  }
}
