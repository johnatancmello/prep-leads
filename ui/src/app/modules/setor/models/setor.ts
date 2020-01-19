export class Setor {
  public id: number;
  public nome: string;

  public isValid(): boolean {
    return this.isIdValid()
      && this.isNomeValid();
  }

  private isNomeValid(): boolean {
    return this.isStringValid(this.nome);
  }

  private isIdValid(): boolean {
    return this.id !== null;
  }

  private isStringValid(text: string): boolean {
    return (text && text.length !== 0);
  }
}
